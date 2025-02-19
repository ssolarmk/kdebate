package com.web.kdebate.student.students.service;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.kdebate.student.students.domain.CreateStudentInfoVO;
import com.web.kdebate.student.students.domain.StudentInfoMngVO;
import com.web.kdebate.student.students.mapper.StudentInfoMngMapper;


@Service
public class StudentInfoMngService {

	@Autowired
	private StudentInfoMngMapper mapper;

	public int getQueryTotalCnt() {
		return mapper.getQueryTotalCnt();
	}

	public HashMap<String, Object> getStudentDtlRetrieve(String std_id) {
		return mapper.getStudentDtlRetrieve(std_id);
	}

	public List<HashMap<String, Object>> getSchdYearList() {
		return mapper.getSchdYearList();
	}

	public List<HashMap<String, Object>> getMngClassList(HashMap<String, Object> hashmapParam) {
		return mapper.getMngClassList(hashmapParam);
	}

	public List<HashMap<String, Object>> getCorpListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getCorpListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getGoodsListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getGoodsListRetrieve(hashmapParam);
	}
	
	public List<HashMap<String, Object>> getCnslUserListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getCnslUserListRetrieve(hashmapParam);
	}

	public int studentInfoUpdate(HashMap<String, Object> hashmapParam) {
		return mapper.studentInfoUpdate(hashmapParam);
	}

	public int userInfoUpdate(HashMap<String, Object> hashmapParam) {
		return mapper.userInfoUpdate(hashmapParam);
	}

	public int studyLinckInfoUpdate(HashMap<String, Object> hashmapParam) {
		return mapper.studyLinckInfoUpdate(hashmapParam);
	}
	
	public int stdMemoUpdate(HashMap<String, Object> hashmapParam) {
		return mapper.stdMemoUpdate(hashmapParam);
	}

	public int parentUserInfoUpdate(HashMap<String, Object> hashmapParam) {
		return mapper.parentUserInfoUpdate(hashmapParam);
	}

	public int stdParentInfoCreate(HashMap<String, Object> hashmapParam) {
		return mapper.stdParentInfoCreate(hashmapParam);
	}

	public int stdParentCreate(HashMap<String, Object> hashmapParam) {
		return mapper.stdParentCreate(hashmapParam);
	}
	
	public int cParentCreate(StudentInfoMngVO studentMngVO) {
		return mapper.cParentCreate(studentMngVO);
	}
	
	public int cParentDelete(StudentInfoMngVO studentMngVO) {
		return mapper.cParentDelete(studentMngVO);
	}

	public int stdInfoSmsCreate(HashMap<String, Object> hashmapParam) {
		return mapper.stdInfoSmsCreate(hashmapParam);
	}

	public int stdClassChk(HashMap<String, Object> hashmapParam) {
		return mapper.stdClassChk(hashmapParam);
	}

	public int stdClassDelete(HashMap<String, Object> hashmapParam) {
		return mapper.stdClassDelete(hashmapParam);
	}

	public int stdClassCreate(HashMap<String, Object> hashmapParam) {
		return mapper.stdClassCreate(hashmapParam);
	}
	
	public int getParentExistConfirm(HashMap<String, Object> hashmapParam) {
		return mapper.getParentExistConfirm(hashmapParam);
	}

	public int getParentHpNoConfirm(HashMap<String, Object> hashmapParam) {
		return mapper.getParentHpNoConfirm(hashmapParam);
	}
	
	public List<HashMap<String, Object>> getParentInfo(HashMap<String, Object> hashmapParam) {
		return mapper.getParentInfo(hashmapParam);
	}

	public int getStdAtdCdConfirm(HashMap<String, Object> hashmapParam) {
		return mapper.getStdAtdCdConfirm(hashmapParam);
	}

	public List<HashMap<String, Object>> getStdCnslListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStdCnslListRetrieve(hashmapParam);
	}

	public int cnslInfoCreate(StudentInfoMngVO studentMngVO) {
		return mapper.cnslInfoCreate(studentMngVO);
	}

	public int cnslInfoUpdate(StudentInfoMngVO studentMngVO) {
		return mapper.cnslInfoUpdate(studentMngVO);
	}

	public List<HashMap<String, Object>> getStdInvoiceListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStdInvoiceListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getStdInvoiceDtlListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStdInvoiceDtlListRetrieve(hashmapParam);
	}

	public String getInvNo() {
		return mapper.getInvNo();
	}

	public int invoiceInfoCreate(HashMap<String, Object> hashmapParam) {
		return mapper.invoiceInfoCreate(hashmapParam);
	}

	public int invoiceDtlInfoCreate(HashMap<String, Object> hashmapParam) {
		return mapper.invoiceDtlInfoCreate(hashmapParam);
	}

	public String getParentId(HashMap<String, Object> hashmapParam) {
		return mapper.getParentId(hashmapParam);
	}

	public String getNotiSeq() {
		return mapper.getNotiSeq();
	}

	public int notiInfoCreate(HashMap<String, Object> hashmapParam) {
		return mapper.notiInfoCreate(hashmapParam);
	}

	public int notiReadCreate(HashMap<String, Object> hashmapParam) {
		return mapper.notiReadCreate(hashmapParam);
	}

	public int smsInfoCreate(HashMap<String, Object> hashmapParam) {
		return mapper.smsInfoCreate(hashmapParam);
	}

	public int invoiceInfoUpdate(HashMap<String, Object> hashmapParam) {
		return mapper.invoiceInfoUpdate(hashmapParam);
	}

	public int invoiceDtlDelete(HashMap<String, Object> hashmapParam) {
		return mapper.invoiceDtlDelete(hashmapParam);
	}

	public int invoicePayYnUpdate(HashMap<String, Object> hashmapParam) {
		return mapper.invoicePayYnUpdate(hashmapParam);
	}

	public List<HashMap<String, Object>> getStdParentsListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStdParentsListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getStdParentInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStdParentInfoRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getStdParentsSrchListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStdParentsSrchListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getRepParentChk(HashMap<String, Object> hashmapParam) {
		return mapper.getRepParentChk(hashmapParam);
	}

	public int parentsCreate(StudentInfoMngVO studentMngVO) {
		return mapper.parentsCreate(studentMngVO);
	}

	public String getRptSeqEnc(StudentInfoMngVO studentMngVO) {
		return mapper.getRptSeqEnc(studentMngVO);
	}

	public String getRptParentId(StudentInfoMngVO studentMngVO) {
		return mapper.getRptParentId(studentMngVO);
	}
	
	public List<HashMap<String, Object>> stdBroList(StudentInfoMngVO studentMngVO) {
		return mapper.stdBroList(studentMngVO);
	}

	public String getStdParentId() {
		return mapper.getStdParentId();
	}

	public int parentInfoCreate(StudentInfoMngVO studentMngVO) {
		return mapper.parentInfoCreate(studentMngVO);
	}

	public int parentCreate(StudentInfoMngVO studentMngVO) {
		return mapper.parentCreate(studentMngVO);
	}

	public int updateRepParentInfo(StudentInfoMngVO studentMngVO) {
		return mapper.updateRepParentInfo(studentMngVO);
	}

	public int parentInfoUpdate(StudentInfoMngVO studentMngVO) {
		return mapper.parentInfoUpdate(studentMngVO);
	}

	public List<HashMap<String, Object>> getRepParentInfo(HashMap<String, Object> hashmapParam) {
		return mapper.getRepParentInfo(hashmapParam);
	}

	public int parentUpdate(StudentInfoMngVO studentMngVO) {
		return mapper.parentUpdate(studentMngVO);
	}

	// 원생상세 수강정보 목록
	public List<HashMap<String, Object>> getStdLectureList(HashMap<String, Object> hashmapParam) {
		return mapper.getStdLectureList(hashmapParam);
	}
	
	// 원생 자매학원 수강정보 목록
	public List<HashMap<String, Object>> getStdAllianceLectureList(HashMap<String, Object> hashmapParam) {
		return mapper.getStdAllianceLectureList(hashmapParam);
	}

	public List<HashMap<String, Object>> getStdBroListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStdBroListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getStdBroSrchListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStdBroSrchListRetrieve(hashmapParam);
	}

	public int broCreate(StudentInfoMngVO studentMngVO) {
		return mapper.broCreate(studentMngVO);
	}

	public int broSameCreate(StudentInfoMngVO studentMngVO) {
		return mapper.broSameCreate(studentMngVO);
	}

	public int broDelete(HashMap<String, Object> map) {
		return mapper.broDelete(map);
	}

	public int broSameDelete(HashMap<String, Object> map) {
		return mapper.broSameDelete(map);
	}

	public List<HashMap<String, Object>> getStdMsgListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStdMsgListRetrieve(hashmapParam);
	}

	public int getInvoicePayCheck(String invoice_no) {
		return mapper.getInvoicePayCheck(invoice_no);
	}

	public HashMap<String, Object> getGoodsInfo(HashMap<String, Object> hashmapParam) {
		return mapper.getGoodsInfo(hashmapParam);
	}

	public void callPrcSyncUser(HashMap<String, Object> callPrcSyncUserParam) {
		mapper.callPrcSyncUser(callPrcSyncUserParam);
	}

	public List<HashMap<String, Object>> getLessonEndDate(HashMap<String, Object> hashmapParam) {
		return mapper.getLessonEndDate(hashmapParam);
	}

	public List<HashMap<String, Object>> getStdChgLogListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStdChgLogListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getChgReasonList(HashMap<String, Object> hashmapParam) {
		return mapper.getChgReasonList(hashmapParam);
	}

	public int getStdAtdLeftCnt(HashMap<String, Object> hashmapParam) {
		return mapper.getStdAtdLeftCnt(hashmapParam);
	}

	public int chgLogInfoCreate(HashMap<String, Object> hashmapParam) {
		return mapper.chgLogInfoCreate(hashmapParam);
	}

	public int stdAtdInfoCreate(HashMap<String, Object> hashmapParam) {
		return mapper.stdAtdInfoCreate(hashmapParam);
	}

	public int chgLogInfoUpdate(HashMap<String, Object> hashmapParam) {
		return mapper.chgLogInfoUpdate(hashmapParam);
	}

	public int stdAtdInfoUpdate(HashMap<String, Object> hashmapParam) {
		return mapper.stdAtdInfoUpdate(hashmapParam);
	}

	public String getMaxInvoiceNo(HashMap<String, Object> hashmapParam) {
		return mapper.getMaxInvoiceNo(hashmapParam);
	}

	public int stdLernDtlUpdate(HashMap<String, Object> hashmapParam) {
		return mapper.stdLernDtlUpdate(hashmapParam);
	}
	
	public String getStudentMbrTp(HashMap<String, Object> hashmapParam) {
		return mapper.getStudentMbrTp(hashmapParam);
	}

	public int stdStatusUpdate(HashMap<String, Object> hashmapParam) {
		return mapper.stdStatusUpdate(hashmapParam);
	}

	public int chgLogInfoDelete(HashMap<String, Object> hashmapParam) {
		return mapper.chgLogInfoDelete(hashmapParam);
	}

	public int chgLogRetInfoUpdate(HashMap<String, Object> hashmapParam) {
		return mapper.chgLogRetInfoUpdate(hashmapParam);
	}

	public String getReasonChkCd(HashMap<String, Object> hashmapParam) {
		return mapper.getReasonChkCd(hashmapParam);
	}

	public int stdAtdChkUpdate(HashMap<String, Object> hashmapParam) {
		return mapper.stdAtdChkUpdate(hashmapParam);
	}

	public String getNextInvPeriod(HashMap<String, Object> hashmapParam) {
		return mapper.getNextInvPeriod(hashmapParam);
	}

	public String getStdStatusInfo(HashMap<String, Object> hashmapParam) {
		return mapper.getStdStatusInfo(hashmapParam);
	}

	public List<HashMap<String, Object>> getInvoicePaymentListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getInvoicePaymentListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getStdAtdListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStdAtdListRetrieve(hashmapParam);
	}

	// 원생 출석정보 목록
	public List<HashMap<String, Object>> getStdAtdListDtlRetrieve(HashMap<String ,Object> hashmapParam) {
		return mapper.getStdAtdListDtlRetrieve(hashmapParam);
	}

	// 셔틀버스 운행일정
	public List<HashMap<String, Object>> getShuttleScheduleList(HashMap<String, Object> hashmapParam) {
		return mapper.getShuttleScheduleList(hashmapParam);
	}

	// 원생 셔틀버스 일정 리스트
	public List<HashMap<String, Object>> getSelectStdShuttleSchList(HashMap<String, Object> hashmapParam) {
		return mapper.getSelectStdShuttleSchList(hashmapParam);
	}

	// 원생 재원상태 체크
	public String getChkStdStatus(HashMap<String, Object> hashmapParam) {
		return mapper.getChkStdStatus(hashmapParam);
	}

	//원생 셔틀버스 탑승일정 체크
	public int chkStdShuttleSChedule(HashMap<String, Object> hashmapParam) {
		return mapper.chkStdShuttleSChedule(hashmapParam);
	}

	//원생 셔틀버스 요일중복 체크
	public int chkStdShttleDuplicateSchedule(HashMap<String, Object> hashmapParam) {
		return mapper.chkStdShttleDuplicateSchedule(hashmapParam);
	}

	// 셔틀버스 좌석여유 체크
	public String getChkBusSeatStatus(HashMap<String, Object> hashmapParam) {
		return mapper.getChkBusSeatStatus(hashmapParam);
	}

	// 원생 셔틀버스 탑승일정 추가
	public int addStdShuttleMember(HashMap<String, Object> hashmapParam) {
		return mapper.addStdShuttleMember(hashmapParam);
	}

	// 원생 셔틀버스 탑승일정 삭제
	public int delStdShuttleBoardSch(HashMap<String, Object> hashmapParam) {
		return mapper.delStdShuttleBoardSch(hashmapParam);
	}

	// 원생 셔틀버스 배정 확인
	public HashMap<String, Object> getSelectStdShuttleSchedule(HashMap<String, Object> hashmapParam) {
		return mapper.getSelectStdShuttleSchedule(hashmapParam);
	}

	// 배정학급 목록
	public List<HashMap<String, Object>> getAcaClassList(HashMap<String, Object> hashmapParam) {
		return mapper.getAcaClassList(hashmapParam);
	}

	// 원생 학급배정 중복체크
	public int getChkStdDuplicateClass(HashMap<String, Object> hashmapParam) {
		return mapper.getChkStdDuplicateClass(hashmapParam);
	}

	// 학급 잔여 좌석수 체크
	public HashMap<String, Object> getClassSeatInfo(HashMap<String, Object> hashmapParam) {
		return mapper.getClassSeatInfo(hashmapParam);
	}

	// 원생 학급배정 추가
	public int setStdClassAdd(HashMap<String, Object> hashmapParam) {
		return mapper.setStdClassAdd(hashmapParam);
	}

	// 원생 학급배정 취소
	public int excludeStdClassInfo(HashMap<String, Object> hashmapParam) {
		return mapper.excludeStdClassInfo(hashmapParam);
	}

	// 응시가능한 시험목록 조회
	public List<HashMap<String, Object>> getSelectAcaExamList(HashMap<String, Object> hashmapParam) {
		return mapper.getSelectAcaExamList(hashmapParam);
	}

	public List<HashMap<String, Object>> getStdExamResultList(HashMap<String, Object> hashmapParam) {
		return mapper.getStdExamResultList(hashmapParam);
	}

	public int setStdExamApply(HashMap<String, Object> hashmapParam) {
		return mapper.setStdExamApply(hashmapParam);
	}

	// 추가&변경 로그 입력
	public int addStdInfoLogChg(HashMap<String, Object> hashmapParam) {
		return mapper.addStdInfoLogChg(hashmapParam);
	}

	public List<HashMap<String, Object>> getAcaSchMgtListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getAcaSchMgtListRetrieve(hashmapParam);
	}

	public List<HashMap<String, String>> getSchMstListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getSchMstListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getStdMbrTpListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStdMbrTpListRetrieve(hashmapParam);
	}
	
	public String getStdSeatInfo(HashMap<String, Object> hashmapParam) {
		return mapper.getStdSeatInfo(hashmapParam);
	}
	
	public void updateRoomSeatInfo(HashMap<String, Object> hashmapParam) {
		mapper.updateRoomSeatInfo(hashmapParam);
	}
	
	public List<HashMap<String, Object>> getStdReadingLvlRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStdReadingLvlRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getStdReadingLvl2Depth(HashMap<String, Object> hashmapParam) {
		return mapper.getStdReadingLvl2Depth(hashmapParam);
	}

	/* 원생 신규 등록 */
	public String getStdId() {
		return mapper.getStdId();
	}
	
	public int chkUserId(String value) {
		return mapper.chkUserId(value);
	}
	
	public int userInfoCreate(@Valid CreateStudentInfoVO createStdInfoVO) {
		return mapper.userInfoCreate(createStdInfoVO);
	}
	
	public int studentInfoCreate(@Valid CreateStudentInfoVO createStdInfoVO) {
		return mapper.studentInfoCreate(createStdInfoVO);
	}
	
	public int stdClassInfoCreate(@Valid CreateStudentInfoVO createStdInfoVO) {
		return mapper.stdClassInfoCreate(createStdInfoVO);
	}
	
	public int stdInfoChgLogCreate(CreateStudentInfoVO createStdInfoVO) {
		return mapper.stdInfoChgLogCreate(createStdInfoVO);
	}
	
	public int stdBroCreate(CreateStudentInfoVO createStdInfoVO) {
		return mapper.stdBroCreate(createStdInfoVO);
	}
	
	public int broStdCreate(CreateStudentInfoVO createStdInfoVO) {
		return mapper.broStdCreate(createStdInfoVO);
	}

	public List<HashMap<String, Object>> stdBroList(CreateStudentInfoVO createStdInfoVO) {
		return mapper.stdBroList(createStdInfoVO);
	}

	public int createParentInfo(CreateStudentInfoVO createStdInfoVO) {
		return mapper.createParentInfo(createStdInfoVO);
	}

	public int createParent(CreateStudentInfoVO createStdInfoVO) {
		return mapper.createParent(createStdInfoVO);
	}
	
	public void updateSaleMstInfo(HashMap<String, Object> hashmap) {
		mapper.updateSaleMstInfo(hashmap);
	}
	
	public void updatePaymentInfo(HashMap<String, Object> hashmap) {
		mapper.updatePaymentInfo(hashmap);
	}
	
	public void updateInvoiceInfo(HashMap<String, Object> hashmap) {
		mapper.updateInvoiceInfo(hashmap);
	}


	public List<HashMap<String, Object>> getStdWaitingListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStdWaitingListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getDebateClassListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getDebateClassListRetrieve(hashmapParam);
	}
}
