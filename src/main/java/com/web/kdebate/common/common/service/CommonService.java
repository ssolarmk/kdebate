package com.web.kdebate.common.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.kdebate.common.common.domain.SmsInfoVO;
import com.web.kdebate.common.common.mapper.CommonMapper;

import ch.qos.logback.classic.Logger;

@Service
public class CommonService {

	@Autowired
	private CommonMapper mapper;

	static final Logger logger = (Logger) LoggerFactory.getLogger(CommonService.class);

	@Autowired
	private SqlSessionTemplate sqlSession;

	public int getQueryTotalCnt() {
		return mapper.getQueryTotalCnt();
	}

	public List<HashMap<String, Object>> getCodeList(HashMap<String, String> hashmapParam) {
		return mapper.getCodeList(hashmapParam);
	}

	public Integer chkAuthRetrieve(HashMap<String, Object> authMap) {
		return mapper.chkAuthRetrieve(authMap);
	}

	public List<HashMap<String, Object>> getTotalCodelist(HashMap<String, String> hashmapParam) {
		return mapper.getTotalCodelist(hashmapParam);
	}

	public List<HashMap<String, Object>> centerListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.centerListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getListRetrieve(String queryId, HashMap<String, Object> hashmapParam) {
		return sqlSession.selectList(queryId, hashmapParam);
	}

	public ArrayList<HashMap<String, Object>> commStdListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.commStdListRetrieve(hashmapParam);
	}

	public String pacaCheck(HashMap<String, Object> hashmapParam) {
		return mapper.pacaCheck(hashmapParam);
	}

	public void setSmsListInsert(SmsInfoVO sms) {
		mapper.setSmsListInsert(sms);
	}

	public String getAcaTelnoRetrieve(String aca_id) {
		return mapper.getAcaTelnoRetrieve(aca_id);
	}

	public int getAchievementTestTotalCnt(HashMap<String, Object> hashmapParam) {
		return mapper.getAchievementTestTotalCnt(hashmapParam);
	}

	public int getAchievementTestRankCnt(HashMap<String, Object> hashmapParam) {
		return mapper.getAchievementTestRankCnt(hashmapParam);
	}

	public HashMap<String, Object> getAchievementTestInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getAchievementTestInfoRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getAchievementTestListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getAchievementTestListRetrieve(hashmapParam);
	}

	public HashMap<String, Object> getStudyReportCurriInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStudyReportCurriInfoRetrieve(hashmapParam);
	}

	public HashMap<String, Object> getStudyReportFreeInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStudyReportFreeInfoRetrieve(hashmapParam);
	}

	public HashMap<String, Object> getStudyReportHomeworkInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStudyReportHomeworkInfoRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getCurriWordInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getCurriWordInfoRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getFreeWordInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getFreeWordInfoRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getHomeworkWordInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getHomeworkWordInfoRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getCurriSentenceInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getCurriSentenceInfoRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getFreeSentenceInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getFreeSentenceInfoRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getHomeworkSentenceInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getHomeworkSentenceInfoRetrieve(hashmapParam);
	}

	public HashMap<String, Object> getCurriDubbingInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getCurriDubbingInfoRetrieve(hashmapParam);
	}

	public HashMap<String, Object> getFreeDubbingInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getFreeDubbingInfoRetrieve(hashmapParam);
	}

	public HashMap<String, Object> getHomeworkDubbingInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getHomeworkDubbingInfoRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getCurriQnaInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getCurriQnaInfoRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getFreeQnaInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getFreeQnaInfoRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getHomeworkQnaInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getHomeworkQnaInfoRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getWdInfoListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getWdInfoListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getStInfoListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStInfoListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getDuInfoListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getDuInfoListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getQaInfoListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getQaInfoListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getAsInfoListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getAsInfoListRetrieve(hashmapParam);
	}

	public HashMap<String, Object> getStudentInfo(String rpt_seq_enc) {
		return mapper.getStudentInfo(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getStudentAcaInfoList(String rpt_seq_enc) {
		return mapper.getStudentAcaInfoList(rpt_seq_enc);
	}

	public HashMap<String, Object> getStudentTotTime(String rpt_seq_enc) {
		return mapper.getStudentTotTime(rpt_seq_enc);
	}

	public String getSelectAcaId(String rpt_seq_enc) {
		return mapper.getSelectAcaId(rpt_seq_enc);
	}

	public HashMap<String, Object> getStudentAtdAvgDt(HashMap<String, Object> hashmapParam) {
		return mapper.getStudentAtdAvgDt(hashmapParam);
	}

	public HashMap<String, Object> getStudentAtdTopAvg(String rpt_seq_enc) {
		return mapper.getStudentAtdTopAvg(rpt_seq_enc);
	}

	public HashMap<String, Object> getStudentAtdTopTotAvg(String rpt_seq_enc) {
		return mapper.getStudentAtdTopTotAvg(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getStudentChartDt(String rpt_seq_enc) {
		return mapper.getStudentChartDt(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getStudentChartDayStd(String rpt_seq_enc) {
		return mapper.getStudentChartDayStd(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getStudentChartDayAca(String rpt_seq_enc) {
		return mapper.getStudentChartDayAca(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getStudentChartDayTop(String rpt_seq_enc) {
		return mapper.getStudentChartDayTop(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getStudentChartDayAvgAca(String rpt_seq_enc) {
		return mapper.getStudentChartDayAvgAca(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getStudentChartDayAvgHom(String rpt_seq_enc) {
		return mapper.getStudentChartDayAvgHom(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getStudentChartDayAvgOwn(String rpt_seq_enc) {
		return mapper.getStudentChartDayAvgOwn(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getStudentDailyTimeList(String rpt_seq_enc) {
		return mapper.getStudentDailyTimeList(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getStudentDayAvgAcaList(String rpt_seq_enc) {
		return mapper.getStudentDayAvgAcaList(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getStudentDayAvgAcaSubList(String rpt_seq_enc) {
		return mapper.getStudentDayAvgAcaSubList(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getStudentDayAvgHomList(String rpt_seq_enc) {
		return mapper.getStudentDayAvgHomList(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getStudentDayAvgHomSubList(String rpt_seq_enc) {
		return mapper.getStudentDayAvgHomSubList(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getStudentDayAvgOwnList(String rpt_seq_enc) {
		return mapper.getStudentDayAvgOwnList(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getStudentDayTimeAvgList(String rpt_seq_enc) {
		return mapper.getStudentDayTimeAvgList(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getStudentTotBarPlan(String rpt_seq_enc) {
		return mapper.getStudentTotBarPlan(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getStudentTotBarImp(String rpt_seq_enc) {
		return mapper.getStudentTotBarImp(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getStudentTotImpPer(String rpt_seq_enc) {
		return mapper.getStudentTotImpPer(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getStudentTotStat(String rpt_seq_enc) {
		return mapper.getStudentTotStat(rpt_seq_enc);
	}

	public List<HashMap<String, Object>> getSeatStatusInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getSeatStatusInfoRetrieve(hashmapParam);
	}

	public HashMap<String, Object> getStudyPlanStdInfo(HashMap<String, Object> hashmapParam) {
		return mapper.getStudyPlanStdInfo(hashmapParam);
	}

	public List<HashMap<String, Object>> getStudySchInfo(HashMap<String, Object> hashmapParam) {
		return mapper.getStudySchInfo(hashmapParam);
	}

	public HashMap<String, Object> getDailyPlanInfo(HashMap<String, Object> hashmapParam) {
		return mapper.getDailyPlanInfo(hashmapParam);
	}

	public HashMap<String, Object> getDailyAcaTotalTime(HashMap<String, Object> hashmapParam) {
		return mapper.getDailyAcaTotalTime(hashmapParam);
	}

	public HashMap<String, Object> getDailyAcaHomeworkTime(HashMap<String, Object> hashmapParam) {
		return mapper.getDailyAcaHomeworkTime(hashmapParam);
	}

	public List<HashMap<String, Object>> getDailyStdOutTime(HashMap<String, Object> hashmapParam) {
		return mapper.getDailyStdOutTime(hashmapParam);
	}

	public List<HashMap<String, Object>> getDailyStudyPlan(HashMap<String, Object> hashmapParam) {
		return mapper.getDailyStudyPlan(hashmapParam);
	}

	public List<HashMap<String, Object>> getDailyStudyBookInfo(HashMap<String, Object> hashmapParam) {
		return mapper.getDailyStudyBookInfo(hashmapParam);
	}

	public List<HashMap<String, Object>> getEmpList(HashMap<String, Object> hashmapParam) {
		return mapper.getEmpList(hashmapParam);
	}

	public List<HashMap<String, Object>> getTeacherList(HashMap<String, Object> hashmapParam) {
		return mapper.getTeacherList(hashmapParam);
	}

	public HashMap<String, Object> getPayCertificateInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getPayCertificateInfoRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getPayCertificateListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getPayCertificateListRetrieve(hashmapParam);
	}

	// 셔틀버스명 조회
	public List<HashMap<String, String>> selectShuttleBusName(HashMap<String, Object> hashmapParam) {
		return mapper.selectShuttleBusName(hashmapParam);
	}

	// 셔틀버스 기사조회
	public List<HashMap<String, String>> selectDriverList(HashMap<String, Object> hashmapParam) {
		return mapper.selectDriverList(hashmapParam);
	}

	// 인솔교사 조회
	public List<HashMap<String, String>> selectHelperList(HashMap<String, Object> hashmapParam) {
		return mapper.selectHelperList(hashmapParam);
	}

	// 셔틀버스 노선조회 select
	public List<HashMap<String, Object>> getSearchShuttleMst(HashMap<String, Object> hashmapParam) {
		return mapper.getSearchShuttleMst(hashmapParam);
	}

	// 셔틀버스 정류장조회 select
	public List<HashMap<String, Object>> getSearchShuttleSta(HashMap<String, Object> hashmapParam) {
		return mapper.getSearchShuttleSta(hashmapParam);
	}

	// 셔틀버스 도착시간 select
	public List<HashMap<String, Object>> getSearchShuttleArrTm(HashMap<String, Object> hashmapParam) {
		return mapper.getSearchShuttleArrTm(hashmapParam);
	}

	// 학습레포트 생성
	public int getStdReportCreate(HashMap<String, Object> hashmapParam) {
		return mapper.getStdReportCreate(hashmapParam);
	}

	public int rptSchdAcaCreate(HashMap<String, Object> hashmapParam) {
		return mapper.rptSchdAcaCreate(hashmapParam);
	}

	public int rptSchdSchCreate(HashMap<String, Object> hashmapParam) {
		return mapper.rptSchdSchCreate(hashmapParam);
	}

	public int rptSchdEtcCreate(HashMap<String, Object> hashmapParam) {
		return mapper.rptSchdEtcCreate(hashmapParam);
	}

	public String getRptParentId(HashMap<String, Object> hashmapParam) {
		return mapper.getRptParentId(hashmapParam);
	}

	public String getRptSeqEnc(HashMap<String, Object> hashmapParam) {
		return mapper.getRptSeqEnc(hashmapParam);
	}

	public int rptSmsInfoCreate(HashMap<String, Object> hashmapParam) {
		return mapper.rptSmsInfoCreate(hashmapParam);
	}

	public String setBaseInfoClassEdt(HashMap<String, Object> hashmapParam) {
		return mapper.setBaseInfoClassEdt(hashmapParam);
	}

	public String getInvoiceClassNm(HashMap<String, Object> hashmap) {
		return mapper.getInvoiceClassNm(hashmap);
	}

	public List<HashMap<String, Object>> selectInvoiceUnpayStdList(HashMap<String, Object> hashmap) {
		return mapper.selectInvoiceUnpayStdList(hashmap);
	}

	public HashMap<String, Object> getStdinfo(String stdId) {
		return mapper.getStdinfo(stdId);
	}

	public List<HashMap<String, Object>> getSelectStdTrialExamCharts(String stdId) {
		return mapper.getSelectStdTrialExamCharts(stdId);
	}

	public List<HashMap<String, Object>> getSelectStdTrialExamList(String stdId) {
		return mapper.getSelectStdTrialExamList(stdId);
	}

	public HashMap<String, Object> getStdReportInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStdReportInfoRetrieve(hashmapParam);
	}

	public HashMap<String, Object> getTestStdReportInfo(String unique_code) {
		return mapper.getTestStdReportInfo(unique_code);
	}

	public String getSelectAgeOfDate(HashMap<String, Object> hashmap) {
		return mapper.getSelectAgeOfDate(hashmap);
	}
}
