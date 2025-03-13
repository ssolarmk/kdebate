package com.web.kdebate.common.common.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.kdebate.common.common.domain.SmsInfoVO;
import com.web.kdebate.common.common.domain.SmsStatusVO;
import com.web.kdebate.common.common.mapper.SmsMapper;



@Service("SmsService")
public class SmsService {
	@Autowired
	private SmsMapper mapper;

	public List<SmsInfoVO> getMsgSendRetrieve() {
		// TODO Auto-generated method stub
		return mapper.getMsgSendRetrieve();
	}

	public void setSetMsgStatus(SmsInfoVO smsInfoVO) {
		mapper.setSetMsgStatus(smsInfoVO);

	}

	public void setMsgFinalStatus(SmsStatusVO smsStatusVO) {
		mapper.setMsgFinalStatus(smsStatusVO);

	}

	public List<HashMap<String, Object>> getAcaSmsUidList(){
		return mapper.getAcaSmsUidList();
	}
}
