package com.web.kdebate.center.user.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.common.util.ValidateUtil;
import com.web.kdebate.center.user.domain.UserMngVO;
import com.web.kdebate.center.user.service.UserService;
import com.web.kdebate.common.common.domain.PageingVO;
import com.web.kdebate.common.common.domain.ReturnDataVO;
import com.web.kdebate.common.common.domain.SessionVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/center/user/userMng")
public class UserController implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -7083478000856794149L;


	@Autowired
	private UserService mapper;

	@RequestMapping(value="/view")
	public String view() {
		return "pages/center/user/userView";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/userListRetrieve", method= RequestMethod.POST)
    public @ResponseBody HashMap<String, Object> userListRetrieve(@RequestBody HashMap<String, Object> hashmapParam
    		, HttpSession session){

		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		hashmapParam.put("user_id", member.getUser_id());

		try {
			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);

			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

			resultList =  mapper.getUserListRetrieve(hashmapParam);
			int records =  mapper.getQueryTotalCnt();

			pageing.setRecords(records);
            pageing.setTotal((int) Math.ceil((double) records / (double) pageing.getLength()));

            hashmapResult.put("draw", pageing.getDraw());
            hashmapResult.put("recordsTotal", pageing.getRecords());
            hashmapResult.put("recordsFiltered", pageing.getRecords());
            hashmapResult.put("data", resultList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashmapResult;

	}

	@RequestMapping(value="/userAuthGrpCdRetrieve", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO userAuthGrpCdRetrieve(@RequestParam HashMap<String, Object> hashmapParam){
    	ReturnDataVO result = new ReturnDataVO();
    	try {
    		result.setData(mapper.getAuthGrpCdRetrieve(hashmapParam));
    		result.setResultCode("S000");

    	} catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
    		result.setResultCode("S999");
		}
		return result;
    }
	@RequestMapping(value="userCnctLogListRetrieve", method= RequestMethod.POST)
    public @ResponseBody HashMap<String, Object> userCnctLogListRetrieve(@RequestBody HashMap<String, Object> hashmapParam){
    	List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
    	HashMap<String, Object> hashmapResult = new HashMap<String, Object>();
		try {
			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);

			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

			resultList =  mapper.getUserCnctLogRetrieve(hashmapParam);
			int records =  mapper.getQueryTotalCnt();

			pageing.setRecords(records);
            pageing.setTotal((int) Math.ceil((double) records / (double) pageing.getLength()));

            hashmapResult.put("draw", pageing.getDraw());
            hashmapResult.put("recordsTotal", pageing.getRecords());
            hashmapResult.put("recordsFiltered", pageing.getRecords());
            hashmapResult.put("data", resultList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashmapResult;
    }
	@RequestMapping(value="checkUserId", method= RequestMethod.POST)
	public @ResponseBody ReturnDataVO checkUserId(@RequestParam HashMap<String, Object> hashmapParam) throws Exception {
		ReturnDataVO result = new ReturnDataVO();
		int chk = 0;
		try {

			chk = mapper.chkUserId(hashmapParam);

			if(chk == 0){
				result.setResultCode("S000");
				result.setData(chk);
				result.setResultMsg("사용가능한 아이디 입니다.");
			} else if(chk == 1){
				result.setResultCode("S000");
				result.setData(chk);
				result.setResultMsg("중복된 아이디 입니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("에러 발생..");
		}

		return result ;
	}
	@Transactional
	@RequestMapping(value="userCreate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO userCreate(@ModelAttribute("UserMngCreateVo") @Valid UserMngVO userMngVO, BindingResult bindResult, HttpSession session
    		, @RequestParam(value="auth_grp_cd", required=false) List<String> authGrpList
    		, HttpServletRequest request){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	userMngVO.setEnt_user_id(member.getUser_id());

    	if(member.getP_aca_id() != null) {
    		userMngVO.setAca_id(member.getP_aca_id());
    	}else {
    		userMngVO.setAca_id(member.getAca_id());
    	}

		try {
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(authGrpList != null) {

				for(int i=0; i < authGrpList.size(); i++){
					userMngVO.setAuth_grp_cd(authGrpList.get(i));

					if(mapper.userAuthGrpCreate(userMngVO) == 1){
						result.setResultCode("S000");
					}
				}
			}
			result.setResultCode("S000");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setResultCode("S999");
			result.setResultMsg(null);
		}
    	return result;
    }
    @Transactional
	@RequestMapping(value="userUpdate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO userUpdate(@ModelAttribute("UserMngCreateVo") @Valid UserMngVO userMngVO, BindingResult bindResult, HttpSession session
    		, @RequestParam(value="auth_grp_cd", required=false) List<String> authGrpList){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	userMngVO.setUpt_user_id(member.getUser_id());

    	System.out.println("############################### ==> " + userMngVO);
		try {
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.userAuthGrpDelete(userMngVO) == 1){
				result.setResultCode("S000");
			}

			if(authGrpList != null) {

				for(int i=0; i < authGrpList.size(); i++){
					userMngVO.setAuth_grp_cd(authGrpList.get(i));
					if(mapper.userAuthGrpCreate(userMngVO) == 1){
						result.setResultCode("S000");
					}
				}
			}
			result.setResultCode("S000");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg(null);
		}
    	return result;
    }
    @Transactional
	@RequestMapping(value="userpwdUpdate")
    public @ResponseBody ReturnDataVO userpwdUpdate(){
    	ReturnDataVO result = new ReturnDataVO();

		try {
			List<HashMap<String, Object>> users = new ArrayList<>();
			users = mapper.getUserAllListRetrieve();

			for (HashMap<String, Object> temp : users) {
				if(null != temp.get("HP_NO")) {
					temp.put("HP_NO_ENC", temp.get("HP_NO").toString());
					mapper.userpwdUpdate(temp);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg(null);
		}
    	return result;
    }

}
