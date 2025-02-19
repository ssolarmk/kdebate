package com.web.kdebate.student.students.mapper;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.web.kdebate.student.students.domain.CreateStudentInfoVO;
import com.web.kdebate.student.students.domain.StudentInfoMngVO;

@Mapper
public interface StudentInfoMngMapper {

	int getQueryTotalCnt();

	HashMap<String, Object> getStudentDtlRetrieve(String std_id);

	List<HashMap<String, Object>> getSchdYearList();

	List<HashMap<String, Object>> getMngClassList(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getCorpListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getGoodsListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getCnslUserListRetrieve(HashMap<String, Object> hashmapParam);
	
	int userInfoUpdate(HashMap<String, Object> hashmapParam);

	int studentInfoUpdate(HashMap<String, Object> hashmapParam);

	int studyLinckInfoUpdate(HashMap<String, Object> hashmapParam);

	int stdMemoUpdate(HashMap<String, Object> hashmapParam);
	
	int parentUserInfoUpdate(HashMap<String, Object> hashmapParam);

	int stdParentInfoCreate(HashMap<String, Object> hashmapParam);

	int stdParentCreate(HashMap<String, Object> hashmapParam);
	
	int cParentCreate(StudentInfoMngVO studentMngVO);

	int cParentDelete(StudentInfoMngVO studentMngVO);

	int stdInfoSmsCreate(HashMap<String, Object> hashmapParam);

	int stdClassChk(HashMap<String, Object> hashmapParam);

	int stdClassDelete(HashMap<String, Object> hashmapParam);

	int stdClassCreate(HashMap<String, Object> hashmapParam);
	
	int getParentExistConfirm(HashMap<String, Object> hashmapParam);

	int getParentHpNoConfirm(HashMap<String, Object> hashmapParam);
	
	List<HashMap<String, Object>> getParentInfo(HashMap<String, Object> hashmapParam);

	int getStdAtdCdConfirm(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStdCnslListRetrieve(HashMap<String, Object> hashmapParam);

	int cnslInfoCreate(StudentInfoMngVO studentMngVO);

	int cnslInfoUpdate(StudentInfoMngVO studentMngVO);

	List<HashMap<String, Object>> getStdInvoiceListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStdInvoiceDtlListRetrieve(HashMap<String, Object> hashmapParam);

	String getInvNo();

	int invoiceInfoCreate(HashMap<String, Object> hashmapParam);

	int invoiceDtlInfoCreate(HashMap<String, Object> hashmapParam);

	String getParentId(HashMap<String, Object> hashmapParam);

	String getNotiSeq();

	int notiInfoCreate(HashMap<String, Object> hashmapParam);

	int notiReadCreate(HashMap<String, Object> hashmapParam);

	int smsInfoCreate(HashMap<String, Object> hashmapParam);

	int invoiceDtlDelete(HashMap<String, Object> hashmapParam);

	int invoiceInfoUpdate(HashMap<String, Object> hashmapParam);

	int invoicePayYnUpdate(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStdParentsListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStdParentInfoRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStdParentsSrchListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getRepParentChk(HashMap<String, Object> hashmapParam);

	int parentsCreate(StudentInfoMngVO studentMngVO);

	String getRptSeqEnc(StudentInfoMngVO studentMngVO);

	String getRptParentId(StudentInfoMngVO studentMngVO);
	
	List<HashMap<String, Object>> stdBroList(StudentInfoMngVO studentMngVO);

	String getStdParentId();

	int parentInfoCreate(StudentInfoMngVO studentMngVO);

	int parentCreate(StudentInfoMngVO studentMngVO);

	int updateRepParentInfo(StudentInfoMngVO studentMngVO);

	int parentInfoUpdate(StudentInfoMngVO studentMngVO);

	int parentUpdate(StudentInfoMngVO studentMngVO);

	List<HashMap<String, Object>> getRepParentInfo(HashMap<String, Object> hashmapParam);

	// 원생상세 수강정보 목록
	List<HashMap<String, Object>> getStdLectureList(HashMap<String, Object> hashmapParam);
	
	// 원생 자매학원 수강정보 목록
	List<HashMap<String, Object>> getStdAllianceLectureList(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStdBroListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStdBroSrchListRetrieve(HashMap<String, Object> hashmapParam);

	int broCreate(StudentInfoMngVO studentMngVO);

	int broSameCreate(StudentInfoMngVO studentMngVO);

	int broDelete(HashMap<String, Object> map);

	int broSameDelete(HashMap<String, Object> map);

	List<HashMap<String, Object>> getStdMsgListRetrieve(HashMap<String, Object> hashmapParam);

	int getInvoicePayCheck(String invoice_no);

	HashMap<String, Object> getGoodsInfo(HashMap<String, Object> hashmapParam);

	void callPrcSyncUser(HashMap<String, Object> callPrcSyncUserParam);

	List<HashMap<String, Object>> getLessonEndDate(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStdChgLogListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getChgReasonList(HashMap<String, Object> hashmapParam);

	int getStdAtdLeftCnt(HashMap<String, Object> hashmapParam);

	int chgLogInfoCreate(HashMap<String, Object> hashmapParam);

	int stdAtdInfoCreate(HashMap<String, Object> hashmapParam);

	int chgLogInfoUpdate(HashMap<String, Object> hashmapParam);

	int stdAtdInfoUpdate(HashMap<String, Object> hashmapParam);

	String getMaxInvoiceNo(HashMap<String, Object> hashmapParam);

	int stdLernDtlUpdate(HashMap<String, Object> hashmapParam);

	String getStudentMbrTp(HashMap<String, Object> hashmapParam);
	
	int stdStatusUpdate(HashMap<String, Object> hashmapParam);

	int chgLogInfoDelete(HashMap<String, Object> hashmapParam);

	int chgLogRetInfoUpdate(HashMap<String, Object> hashmapParam);

	String getReasonChkCd(HashMap<String, Object> hashmapParam);

	int stdAtdChkUpdate(HashMap<String, Object> hashmapParam);

	String getNextInvPeriod(HashMap<String, Object> hashmapParam);

	String getStdStatusInfo(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getInvoicePaymentListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStdAtdListRetrieve(HashMap<String, Object> hashmapParam);

	// 원생 출석정보 목록
	List<HashMap<String, Object>> getStdAtdListDtlRetrieve(HashMap<String, Object> hashmapParam);

	// 셔틀버스 운행일정
	List<HashMap<String, Object>> getShuttleScheduleList(HashMap<String, Object> hashmapParam);

	// 원생 셔틀버스 일정 리스트
	List<HashMap<String, Object>> getSelectStdShuttleSchList(HashMap<String, Object> hashmapParam);

	// 원생 재원상태 체크
	String getChkStdStatus(HashMap<String, Object> hashmapParam);

	// 원생 셔틀버스 탑승일정 체크
	int chkStdShuttleSChedule(HashMap<String, Object> hashmapParam);

	//원생 셔틀버스 요일중복 체크
	int chkStdShttleDuplicateSchedule(HashMap<String, Object> hashmapParam);

	String getChkBusSeatStatus(HashMap<String, Object> hashmapParam);

	// 원생 셔틀버스 탑승일정 추가
	int addStdShuttleMember(HashMap<String, Object> hashmapParam);

	// 원생 셔틀버스 탑승일정 삭제
	int delStdShuttleBoardSch(HashMap<String, Object> hashmapParam);

	// 원생 셔틀버스 배정 확인
	HashMap<String, Object> getSelectStdShuttleSchedule(HashMap<String, Object> hashmapParam);

	// 배정학급 목록
	List<HashMap<String, Object>> getAcaClassList(HashMap<String, Object> hashmapParam);

	// 원생 학급배정 중복체크
	int getChkStdDuplicateClass(HashMap<String, Object> hashmapParam);

	// 학급 잔여 좌석수 체크
	HashMap<String, Object> getClassSeatInfo(HashMap<String, Object> hashmapParam);

	// 원생 학급배정 추가
	int setStdClassAdd(HashMap<String, Object> hashmapParam);

	// 원생 학급배정 취소
	int excludeStdClassInfo(HashMap<String, Object> hashmapParam);

	// 응시가능한 시험목록 조회
	List<HashMap<String, Object>> getSelectAcaExamList(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStdExamResultList(HashMap<String, Object> hashmapParam);

	int setStdExamApply(HashMap<String, Object> hashmapParam);

	// 추가&변경 로그 입력
	int addStdInfoLogChg(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getAcaSchMgtListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, String>> getSchMstListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStdMbrTpListRetrieve(HashMap<String, Object> hashmapParam);
	
	String getStdSeatInfo(HashMap<String, Object> hashmapParam);
	
	void updateRoomSeatInfo(HashMap<String, Object> hashmapParam);
	
	List<HashMap<String, Object>> getStdReadingLvlRetrieve(HashMap<String, Object> hashmapParam);
	
	List<HashMap<String, Object>> getStdReadingLvl2Depth(HashMap<String, Object> hashmapParam);
	
	/* 원생 신규 등록 */
	String getStdId();

	int chkUserId(String value);
	
	int userInfoCreate(@Valid CreateStudentInfoVO createStdInfoVO);
	
	int studentInfoCreate(@Valid CreateStudentInfoVO createStdInfoVO);
	
	int stdClassInfoCreate(@Valid CreateStudentInfoVO createStdInfoVO);
	
	int stdInfoChgLogCreate(CreateStudentInfoVO createStdInfoVO);
	
	int stdBroCreate(CreateStudentInfoVO createStdInfoVO);
	
	int broStdCreate(CreateStudentInfoVO createStdInfoVO);
	
	List<HashMap<String, Object>> stdBroList(CreateStudentInfoVO createStdInfoVO);

	public int createParentInfo(CreateStudentInfoVO createStdInfoVO);

	public int createParent(CreateStudentInfoVO createStdInfoVO);
	
	void updateSaleMstInfo(HashMap<String, Object> hashmap);
	
	void updatePaymentInfo(HashMap<String, Object> hashmap);
	
	void updateInvoiceInfo(HashMap<String, Object> hashmap);

	
	List<HashMap<String, Object>> getStdWaitingListRetrieve(HashMap<String, Object> hashmapParam);
	
	List<HashMap<String, Object>> getDebateClassListRetrieve(HashMap<String, Object> hashmapParam);
}