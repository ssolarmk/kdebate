package com.web.kdebate.common.common.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.common.LoginManager;
import com.web.kdebate.common.common.domain.ReturnDataVO;
import com.web.kdebate.common.common.domain.SessionVO;
import com.web.kdebate.common.common.service.LoginService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1665314029625894394L;
	@Value("${spring.profiles.active}")
	String profiles;
	@Autowired
	private LoginService mapper;

	@RequestMapping(value="/login")
	public String loginPage() {
		return "pages/login";
	}

	@RequestMapping(value="checkLoginUser", method=RequestMethod.POST)
	public @ResponseBody ReturnDataVO checkLoginUser(@RequestParam HashMap<String, String> param, HttpServletRequest request, HttpServletResponse response){
		ReturnDataVO result = new ReturnDataVO();
		HttpSession session = request.getSession(true);
		SessionVO loginUserVo = new SessionVO();
		LoginManager loginManager = LoginManager.getInstance();
		param.put("cnct_ip", request.getRemoteAddr());
		loginUserVo = mapper.getUserRetrieve(param);
		System.out.println(loginUserVo);
		List<String> authGrp = new ArrayList<String>();
		try {
			if(loginUserVo != null ){
				authGrp = (List<String>) mapper.getUserGrpRetrieve(param);
				if(authGrp.size() < 1){
					HashMap<String, Object> loginHist = new HashMap<String, Object>();
					loginHist.put("user_id", param.get("user_id"));
					loginHist.put("IP", request.getRemoteAddr());
					loginHist.put("cnct_scs_yn", "N");
					mapper.createloginHist(loginHist);
					result.setResultCode("S999");
					result.setResultMsg("로그인 권한이 없습니다.");
					return result;
				}
				loginUserVo.setUser_group(authGrp);

				List<HashMap<String, Object>> linkList = new ArrayList<HashMap<String,Object>>();
				linkList = mapper.getLinkList();

				for(int i=0; i<linkList.size(); i++) {
					if(linkList.get(i).get("link_code").equals("home")) {
						loginUserVo.setHome_url(linkList.get(i).get("link_code_nm").toString());
					}else if(linkList.get(i).get("link_code").equals("LAB")){
						loginUserVo.setLab_url(linkList.get(i).get("link_code_nm").toString());
					}
				}
				

				result.setResultCode("S000");
				result.setResultMsg("success");
				List<HashMap<String, Object>> menuList = new ArrayList<HashMap<String,Object>>();
				menuList = mapper.getMenuRetrieve(loginUserVo);

				List<HashMap<String, Object>> roomList = new ArrayList<HashMap<String,Object>>();
				roomList = mapper.getRoomRetrieve(loginUserVo);
				
				int allianceCnt = mapper.getAcaAllianceCnt(loginUserVo);				
				if(allianceCnt > 0) {
					List<HashMap<String, Object>> allianceList = new ArrayList<HashMap<String, Object>>();
					allianceList = mapper.getAcaAllianceList(loginUserVo);
					
					session.setAttribute("S_ALLIANCE", allianceList);
				}
				loginUserVo.setProfiles(profiles);
				loginUserVo.setMenu(menuList);
				loginUserVo.setRoom(roomList);
				session.setAttribute("S_USER", loginUserVo);
				session.setAttribute("S_LOGIN_YN"	, "Y");
				session.setAttribute("theme", "default");
				//최근로그인insert
				mapper.lastLoginUpdate(loginUserVo);
				HashMap<String, Object> loginHist = new HashMap<String, Object>();
				loginHist.put("user_id", loginUserVo.getUser_id());
				loginHist.put("ip", request.getRemoteAddr());
				loginHist.put("cnct_scs_yn", "Y");
				mapper.createloginHist(loginHist);
				loginManager.setSession(session, (String) loginUserVo.getUser_id());

				List<HashMap<String, Object>> topMenuList = new ArrayList<HashMap<String,Object>>();
				String prev_menu_id = "";
				for(HashMap<String, Object> menu : menuList) {
					if(!prev_menu_id.equals(menu.get("top_menu_id").toString())) {
						topMenuList.add(menu);
						prev_menu_id = menu.get("top_menu_id").toString();
					}
				}
				loginUserVo.setTopMenu(topMenuList);
			} else {
				HashMap<String, Object> loginHist = new HashMap<String, Object>();
				loginHist.put("user_id", param.get("user_id"));
				loginHist.put("ip", request.getRemoteAddr());
				loginHist.put("cnct_scs_yn", "N");
				mapper.createloginHist(loginHist);
				result.setResultCode("S999");
				result.setResultMsg("아이디/패스워드/IP가 등록되어 있지 않습니다.");
			}

			
		} catch (Exception e) {
			HashMap<String, Object> loginHist = new HashMap<String, Object>();
			loginHist.put("user_id", param.get("user_id"));
			loginHist.put("ip", request.getRemoteAddr());
			loginHist.put("cnct_scs_yn", "N");
			mapper.createloginHist(loginHist);
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("에러 발생..");
		}
		return result ;
	}
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		HttpSession session = request.getSession(true);
		try
		{
			if(session == null)
			{
				return "redirect:/login";
			}

			String isLogon 	= (String) session.getAttribute("S_LOGIN_YN");
			SessionVO loginUserVo =  (SessionVO) session.getAttribute("S_USER");

			if(isLogon != null && isLogon.equals("Y") && loginUserVo != null && !("").equals(loginUserVo.getUser_id()))
			{
				session.setAttribute("S_USER", 	null);
				session.setAttribute("S_LOGIN_YN", 	null);

				session.removeAttribute("S_USER");
				session.removeAttribute("S_LOGIN_YN");

				//쿠키 제거
				for(Cookie cookie : request.getCookies())
				{
					if(cookie.getName().startsWith("kdebate_c_"))
					{
						cookie.setValue(null);
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}

				session.invalidate();

			}

		}catch(IllegalStateException ise) {
		} catch(Exception e) {
			e.printStackTrace();
		}


		redirectAttributes.addFlashAttribute("logoutMag", "로그아웃 되었습니다.");
		return "redirect:/login" ;
	}
}
