package com.web.kdebate.common.common.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.kdebate.common.common.domain.SmsInfoVO;

@Mapper
public interface CommonMapper {

	int getQueryTotalCnt();

	List<HashMap<String, Object>> getCodeList(HashMap<String, String> hashmapParam);

	Integer chkAuthRetrieve(HashMap<String, Object> authMap);

	List<HashMap<String, Object>> getTotalCodelist(HashMap<String, String> hashmapParam);

	List<HashMap<String, Object>> centerListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getListRetrieve(String queryId, HashMap<String, Object> hashmapParam);

	ArrayList<HashMap<String, Object>> commStdListRetrieve(HashMap<String, Object> hashmapParam);

	String pacaCheck(HashMap<String, Object> hashmapParam);

	void setSmsListInsert(SmsInfoVO sms);

	String getAcaTelnoRetrieve(String aca_id);

	int getAchievementTestTotalCnt(HashMap<String, Object> hashmapParam);

	int getAchievementTestRankCnt(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getAchievementTestInfoRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getAchievementTestListRetrieve(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getStudyReportCurriInfoRetrieve(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getStudyReportFreeInfoRetrieve(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getStudyReportHomeworkInfoRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getCurriWordInfoRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getFreeWordInfoRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getHomeworkWordInfoRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getCurriSentenceInfoRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getFreeSentenceInfoRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getHomeworkSentenceInfoRetrieve(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getCurriDubbingInfoRetrieve(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getFreeDubbingInfoRetrieve(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getHomeworkDubbingInfoRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getCurriQnaInfoRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getFreeQnaInfoRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getHomeworkQnaInfoRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getWdInfoListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStInfoListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getDuInfoListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getQaInfoListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getAsInfoListRetrieve(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getStudentInfo(String rpt_seq_enc);

	List<HashMap<String, Object>> getStudentAcaInfoList(String rpt_seq_enc);

	HashMap<String, Object> getStudentTotTime(String rpt_seq_enc);

	String getSelectAcaId(String rpt_seq_enc);

	HashMap<String, Object> getStudentAtdAvgDt(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getStudentAtdTopAvg(String rpt_seq_enc);

	HashMap<String, Object> getStudentAtdTopTotAvg(String rpt_seq_enc);

	List<HashMap<String, Object>> getStudentChartDt(String rpt_seq_enc);

	List<HashMap<String, Object>> getStudentChartDayStd(String rpt_seq_enc);

	List<HashMap<String, Object>> getStudentChartDayAca(String rpt_seq_enc);

	List<HashMap<String, Object>> getStudentChartDayTop(String rpt_seq_enc);

	List<HashMap<String, Object>> getStudentChartDayAvgAca(String rpt_seq_enc);

	List<HashMap<String, Object>> getStudentChartDayAvgHom(String rpt_seq_enc);

	List<HashMap<String, Object>> getStudentChartDayAvgOwn(String rpt_seq_enc);

	List<HashMap<String, Object>> getStudentDayAvgAcaList(String rpt_seq_enc);

	List<HashMap<String, Object>> getStudentDayAvgAcaSubList(String rpt_seq_enc);

	List<HashMap<String, Object>> getStudentDayAvgHomList(String rpt_seq_enc);

	List<HashMap<String, Object>> getStudentDayAvgHomSubList(String rpt_seq_enc);

	List<HashMap<String, Object>> getStudentDayAvgOwnList(String rpt_seq_enc);

	List<HashMap<String, Object>> getStudentDailyTimeList(String rpt_seq_enc);

	List<HashMap<String, Object>> getStudentDayTimeAvgList(String rpt_seq_enc);

	List<HashMap<String, Object>> getStudentTotBarPlan(String rpt_seq_enc);

	List<HashMap<String, Object>> getStudentTotBarImp(String rpt_seq_enc);

	List<HashMap<String, Object>> getStudentTotImpPer(String rpt_seq_enc);

	List<HashMap<String, Object>> getStudentTotStat(String rpt_seq_enc);

	List<HashMap<String, Object>> getSeatStatusInfoRetrieve(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getStudyPlanStdInfo(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStudySchInfo(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getDailyPlanInfo(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getDailyAcaTotalTime(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getDailyAcaHomeworkTime(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getDailyStdOutTime(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getDailyStudyPlan(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getDailyStudyBookInfo(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getEmpList(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getTeacherList(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getPayCertificateInfoRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getPayCertificateListRetrieve(HashMap<String, Object> hashmapParam);

	// 셔틀버스명 조회
	List<HashMap<String, String>> selectShuttleBusName(HashMap<String, Object> hashmapParam);

	// 셔틀버스 기사 조회
	List<HashMap<String, String>> selectDriverList(HashMap<String, Object> hashmapParam);

	// 인솔교사 조회
	List<HashMap<String, String>> selectHelperList(HashMap<String, Object> hashmapParam);

	// 셔틀버스 노선조회 select
	List<HashMap<String, Object>> getSearchShuttleMst(HashMap<String, Object> hashmapParam);

	// 셔틀버스 정류장조회 select
	List<HashMap<String, Object>> getSearchShuttleSta(HashMap<String, Object> hashmapParam);

	// 셔틀버스 도착시간 select
	List<HashMap<String, Object>> getSearchShuttleArrTm(HashMap<String, Object> hashmapParam);

	// 학습레포트 생성
	int getStdReportCreate(HashMap<String, Object> hashmapParam);

	int rptSchdAcaCreate(HashMap<String, Object> hashmapParam);

	int rptSchdSchCreate(HashMap<String, Object> hashmapParam);

	int rptSchdEtcCreate(HashMap<String, Object> hashmapParam);

	String getRptParentId(HashMap<String, Object> hashmapParam);

	String getRptSeqEnc(HashMap<String, Object> hashmapParam);

	int rptSmsInfoCreate(HashMap<String, Object> hashmapParam);

	String setBaseInfoClassEdt(HashMap<String, Object> hashmapParam);

	String getInvoiceClassNm(HashMap<String, Object> hashmap);

	List<HashMap<String, Object>> selectInvoiceUnpayStdList(HashMap<String, Object> hashmap);

	HashMap<String, Object> getStdinfo(String stdId);

	List<HashMap<String, Object>> getSelectStdTrialExamCharts(String stdId);

	List<HashMap<String, Object>> getSelectStdTrialExamList(String stdId);

	HashMap<String, Object> getStdReportInfoRetrieve(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getTestStdReportInfo(String unique_code);

	String getSelectAgeOfDate(HashMap<String, Object> hashmap);

}
