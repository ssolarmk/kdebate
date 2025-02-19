package com.web.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

/**
 * <pre>
 * Subject : ��⑤벏�꽰 嚥≪뮄�젃占쎌뵥 筌ｌ꼶�봺
 * Comment : 占쎄쉭占쎈�→쾮�슦釉놂㎗�똾寃� 獄쏉옙 嚥≪뮄�젃占쎌뵥 占쎌��占쏙옙占� �꽴占썹뵳占�.
 * </pre>
 * 
 * @author min
 * @since 2011-02-22
 * @version 1.1
 */
@SuppressWarnings("unchecked")
public class LoginManager implements HttpSessionBindingListener, Serializable {

	private static final long serialVersionUID = -6922827609832482843L;

	private static LoginManager loginManager = null;

	// 嚥≪뮄�젃占쎌뵥占쎈립 占쎌젔占쎈꺗占쎌쁽�몴占� 占쎈뼖疫꿸퀣�맄占쎈립 占쎈퉸占쎈뻻占쎈�믭옙�뵠�뇡占�
	@SuppressWarnings("rawtypes")
	private static Hashtable log_user_table = new Hashtable();

	/*
	 * 占쎈뼓疫뀐옙占쎈꽑 占쎈솭占쎄쉘 占쎄텢占쎌뒠
	 */
	public static synchronized LoginManager getInstance() {
		if (loginManager == null) {
			loginManager = new LoginManager();
		}
		return loginManager;
	}

	/**
	 * 占쎌뵠 筌롫뗄�꺖占쎈굡占쎈뮉 占쎄쉭占쎈�∽옙�뵠 占쎈염野껉퀡由븝옙�뱽占쎈르 占쎌깈�빊�뮆留귨옙�뼄.
	 * - session.setAttribute("login", this)
	 * - Hashtable 占쎈퓠 占쎄쉭占쎈�→�⑨옙 占쎌젔占쎈꺗占쎌쁽 占쎈툡占쎌뵠占쎈탵�몴占� 占쏙옙占쏙옙�삢占쎈립占쎈뼄.
	 */
	public void valueBound(HttpSessionBindingEvent event) {
		// session揶쏅�れ뱽 put占쎈립占쎈뼄.
		log_user_table.put(event.getSession(), event.getName());

		System.out.println("CURRENT USERS : (" + getUserCount() + ")" + "[" + event.getName() + "] is session MADED.");
	}

	/**
	 * 占쎌뵠 筌롫뗄�꺖占쎈굡占쎈뮉 占쎄쉭占쎈�∽옙�뵠 占쎄괌野껋눘�뱽占쎈르 占쎌깈�빊�뮆留귨옙�뼄.
	 * - Hashtable 占쎈퓠 占쏙옙占쏙옙�삢占쎈쭆 嚥≪뮄�젃占쎌뵥占쎈립 占쎌젟癰귣��占쏙옙 占쎌젫椰꾧퀬鍮� 餓ο옙占쎈뼄.
	 */
	public void valueUnbound(HttpSessionBindingEvent event) {
		// session揶쏅�れ뱽 筌≪뼚釉섓옙苑� 占쎈씨占쎈막餓ο옙占쎈뼄.
		log_user_table.remove(event.getSession());

		System.out.println("CURRENT USERS : (" + getUserCount() + ")" + "[" + event.getName() + "] is session EXPIRE.");
	}

	/**
	 * 占쎌뿯占쎌젾獄쏆룇占쏙옙 占쎈툡占쎌뵠占쎈탵�몴占� 占쎈퉸占쎈뻻占쎈�믭옙�뵠�뇡遺용퓠占쎄퐣 占쎄텣占쎌젫.
	 * 
	 * @param userID 占쎄텢占쎌뒠占쎌쁽 占쎈툡占쎌뵠占쎈탵
	 * 
	 * @return void
	 */
	@SuppressWarnings("rawtypes")
	public void removeSession(String userId) {
		Enumeration e = log_user_table.keys();
		HttpSession session = null;

		while (e.hasMoreElements()) {
			session = (HttpSession) e.nextElement();

			System.out.println("log_user_table.get(session) : " + log_user_table.get(session));

			if (log_user_table.get(session).equals(userId)) {
				// 占쎄쉭占쎈�∽옙�뵠 invalidate占쎈쭍占쎈르 HttpSessionBindingListener�몴占� �뤃�뗭겱占쎈릭占쎈뮉
				// 占쎄깻占쎌쟿占쎈뮞占쎌벥
				// valueUnbound()占쎈맙占쎈땾揶쏉옙 占쎌깈�빊�뮆留귨옙�뼄.
				session.invalidate();
			}
		}
	}

	/**
	 * 占쎈퉸占쎈뼣 占쎈툡占쎌뵠占쎈탵占쎌벥 占쎈짗占쎈뻻 占쎄텢占쎌뒠占쎌뱽 筌띾맦由곤옙�맄占쎈퉸占쎄퐣 占쎌뵠沃섓옙 占쎄텢占쎌뒠餓λ쵐�뵥
	 * 占쎈툡占쎌뵠占쎈탵占쎌뵥筌욑옙�몴占� 占�
	 * 
	 * 占쎌뵥占쎈립占쎈뼄.
	 * 
	 * @param userID 占쎄텢占쎌뒠占쎌쁽 占쎈툡占쎌뵠占쎈탵
	 * @return boolean 占쎌뵠沃섓옙 占쎄텢占쎌뒠 餓λ쵐�뵥 野껋럩�뒭 true, 占쎄텢占쎌뒠餓λ쵐�뵠 占쎈툡占쎈빍筌롳옙 false
	 */
	public boolean isUsing(String userID) {
		return log_user_table.containsValue(userID);
	}

	/**
	 * 嚥≪뮄�젃占쎌뵥占쎌뱽 占쎌끏�뙴�슦釉� 占쎄텢占쎌뒠占쎌쁽占쎌벥 占쎈툡占쎌뵠占쎈탵�몴占� 占쎄쉭占쎈�∽옙肉� 占쏙옙占쏙옙�삢占쎈릭占쎈뮉
	 * 筌롫뗄�꺖占쎈굡
	 * 
	 * @param session 占쎄쉭占쎈�� 揶쏆빘猿�
	 * @param userID  占쎄텢占쎌뒠占쎌쁽 占쎈툡占쎌뵠占쎈탵
	 */
	public void setSession(HttpSession session, String userId) {
		// 占쎌뵠占쎈떄揶쏄쑴肉� Session Binding占쎌뵠甕겹끋�뱜揶쏉옙 占쎌뵬占쎈선占쎄돌占쎈뮉 占쎈뻻占쎌젎
		// name揶쏅�れ몵嚥∽옙 userId, value揶쏅�れ몵嚥∽옙 占쎌쁽疫꿸퀣�쁽占쎈뻿(HttpSessionBindingListener�몴占�
		// �뤃�뗭겱占쎈릭占쎈뮉
		// Object)
		session.setAttribute(userId, this);// login占쎈퓠 占쎌쁽疫꿸퀣�쁽占쎈뻿占쎌뱽 筌욌쵐堉깍옙苑뷂옙�뮉占쎈뼄.
	}

	/**
	 * 占쎌뿯占쎌젾獄쏆룇占쏙옙 占쎄쉭占쎈�좴bject嚥∽옙 占쎈툡占쎌뵠占쎈탵�몴占� �뵳�뗪쉘占쎈립占쎈뼄.
	 * 
	 * @param session : 占쎌젔占쎈꺗占쎈립 占쎄텢占쎌뒠占쎌쁽占쎌벥 session Object
	 * @return String : 占쎌젔占쎈꺗占쎌쁽 占쎈툡占쎌뵠占쎈탵
	 */
	public String getUserID(HttpSession session) {
		return (String) log_user_table.get(session);
	}

	/**
	 * 占쎌겱占쎌삺 占쎌젔占쎈꺗占쎈립 �룯占� 占쎄텢占쎌뒠占쎌쁽 占쎈땾
	 * 
	 * @return int 占쎌겱占쎌삺 占쎌젔占쎈꺗占쎌쁽 占쎈땾
	 */
	public int getUserCount() {
		return log_user_table.size();
	}

	/**
	 * 占쎌겱占쎌삺 占쎌젔占쎈꺗餓λ쵐�뵥 筌뤴뫀諭� 占쎄텢占쎌뒠占쎌쁽 占쎈툡占쎌뵠占쎈탵�몴占� �빊�뮆�젾
	 * 
	 * @return void
	 */
	@SuppressWarnings("rawtypes")
	public void printloginUsers() {
		Enumeration e = log_user_table.keys();

		HttpSession session = null;
		System.out.println("===========================================");
		int i = 0;

		while (e.hasMoreElements()) {
			session = (HttpSession) e.nextElement();
			System.out.println((++i) + ". 占쎌젔占쎈꺗占쎌쁽 : " + log_user_table.get(session));
		}
		System.out.println("===========================================");
	}

	/**
	 * 占쎌겱占쎌삺 占쎌젔占쎈꺗餓λ쵐�뵥 筌뤴뫀諭� 占쎄텢占쎌뒠占쎌쁽�뵳�딅뮞占쎈뱜�몴占� �뵳�뗪쉘
	 * 
	 * @return list
	 */
	@SuppressWarnings("rawtypes")
	public Collection getUsers() {
		Collection collection = log_user_table.values();
		return collection;
	}
}
