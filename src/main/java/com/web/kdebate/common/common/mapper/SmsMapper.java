package com.web.kdebate.common.common.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.kdebate.common.common.domain.SmsInfoVO;
import com.web.kdebate.common.common.domain.SmsStatusVO;


@Mapper
public interface SmsMapper {

	List<SmsInfoVO> getMsgSendRetrieve();

	void setSetMsgStatus(SmsInfoVO smsInfoVO);

	void setMsgFinalStatus(SmsStatusVO smsStatusVO);

	List<HashMap<String, Object>> getAcaSmsUidList();
}
