package com.web.kdebate.student.students.service;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.kdebate.student.students.domain.InvoiceMsgVO;
import com.web.kdebate.student.students.domain.StudentVO;
import com.web.kdebate.student.students.mapper.StudentMapper;


@Service("StudentService")
public class StudentService {
	@Autowired
	private StudentMapper mapper;

	public int getQueryTotalCnt() {
		return mapper.getQueryTotalCnt();
	}
	
	public List<HashMap<String, Object>> getSelectSchMst(HashMap<String, Object> hashmapParam) {
		return mapper.getSelectSchMst(hashmapParam);
	}

	public List<HashMap<String, Object>> getStudentListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStudentListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getStudentDtlListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStudentDtlListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getMngClassList(HashMap<String, Object> hashmapParam) {
		return mapper.getMngClassList(hashmapParam);
	}

	public List<HashMap<String, Object>> getMngClassList1(HashMap<String, Object> hashmapParam) {
		return mapper.getMngClassList1(hashmapParam);
	}

	public List<HashMap<String, Object>> getMngClassDtlList(HashMap<String, Object> hashmapParam) {
		return mapper.getMngClassDtlList(hashmapParam);
	}

	public List<HashMap<String, Object>> getMngClassDtlList1(HashMap<String, Object> hashmapParam) {
		return mapper.getMngClassDtlList1(hashmapParam);
	}

	public String getPcodeUseYn(HashMap<String, Object> hashmapParam) {
		return mapper.getPcodeUseYn(hashmapParam);
	}

	public List<HashMap<String, Object>> getSrchTbCodeList(HashMap<String, Object> hashmapParam) {
		return mapper.getSrchTbCodeList(hashmapParam);
	}

	public List<HashMap<String, Object>> getSrchAcdCodeList(HashMap<String, Object> hashmapParam) {
		return mapper.getSrchAcdCodeList(hashmapParam);
	}

	public List<HashMap<String, Object>> getSrchInstructor(HashMap<String, Object> hashmapParam) {
		return mapper.getSrchInstructor(hashmapParam);
	}

	public List<HashMap<String, Object>> getCorpListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getCorpListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getGoodsListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getGoodsListRetrieve(hashmapParam);
	}
	
	public int chgLogInfoBatchCreate(HashMap<String, Object> hashmapParam) {
		return mapper.chgLogInfoBatchCreate(hashmapParam);
	}
	
	public String getStudentMbrTp(HashMap<String, Object> hashmapParam) {
		return mapper.getStudentMbrTp(hashmapParam);
	}

	public int stdStatusUpdate(HashMap<String, Object> hashmapParam) {
		return mapper.stdStatusUpdate(hashmapParam);
	}
	
	public String getReasonChkCd(HashMap<String, Object> hashmapParam) {
		return mapper.getReasonChkCd(hashmapParam);
	}

	public int stdAtdChkUpdate(HashMap<String, Object> hashmapParam) {
		return mapper.stdAtdChkUpdate(hashmapParam);
	}

	public String getInvNo() {
		return mapper.getInvNo();
	}

	public int chkUserId(String value) {
		return mapper.chkUserId(value);
	}

	public int studentChgLvlInfoCreate(HashMap<String, Object> map) {
		return mapper.studentChgLvlInfoCreate(map);
	}

	public int studentChgLvlInfoUpdate(HashMap<String, Object> map) {
		return mapper.studentChgLvlInfoUpdate(map);
	}

	public int getStdAtdCdConfirm(HashMap<String, Object> hashmapParam) {
		return mapper.getStdAtdCdConfirm(hashmapParam);
	}

	public String getStdId() {
		return mapper.getStdId();
	}

	public int userInfoCreate(@Valid StudentVO studentVO) {
		return mapper.userInfoCreate(studentVO);
	}

	public int studentInfoCreate(@Valid StudentVO studentVO) {
		return mapper.studentInfoCreate(studentVO);
	}

	public int stdClassCreate(@Valid StudentVO studentVO) {
		return mapper.stdClassCreate(studentVO);
	}

	public int stdInfoChgLogCreate(StudentVO studentVO) {
		return mapper.stdInfoChgLogCreate(studentVO);
	}

	public int hpDupChk(StudentVO studentVO) {
		return mapper.hpDupChk(studentVO);
	}

	public String getUserId(StudentVO studentVO) {
		return mapper.getUserId(studentVO);
	}

	public String getStdParentId() {
		return mapper.getStdParentId();
	}

	public int parentInfoCreate(StudentVO studentVO) {
		return mapper.parentInfoCreate(studentVO);
	}

	public int parentCreate(StudentVO studentVO) {
		return mapper.parentCreate(studentVO);
	}

	public List<HashMap<String, Object>> stdBroList(StudentVO studentVO) {
		return mapper.stdBroList(studentVO);
	}

	public int stdBroCreate(StudentVO studentVO) {
		return mapper.stdBroCreate(studentVO);
	}

	public int broStdCreate(StudentVO studentVO) {
		return mapper.broStdCreate(studentVO);
	}

	public int stdInfoSmsCreate(StudentVO studentVO) {
		return mapper.stdInfoSmsCreate(studentVO);
	}

	public int invoiceInfoCreate(StudentVO vo) {
		return mapper.invoiceInfoCreate(vo);
	}

	public int invoiceDtlInfoCreate(StudentVO vo) {
		return mapper.invoiceDtlInfoCreate(vo);
	}

	public String getParentId(HashMap<String, Object> hashmapParam) {
		return mapper.getParentId(hashmapParam);
	}

	public String getNotiSeq() {
		return mapper.getNotiSeq();
	}

	public int notiInfoCreate(StudentVO vo) {
		return mapper.notiInfoCreate(vo);
	}

	public int notiReadCreate(StudentVO vo) {
		return mapper.notiReadCreate(vo);
	}

	public int smsInfoCreate(StudentVO vo) {
		return mapper.smsInfoCreate(vo);
	}

	public HashMap<String, Object> getStudentIdConfirm(HashMap<String, Object> hashmapParam) {
		return mapper.getStudentIdConfirm(hashmapParam);
	}

	public int getParentHpNoConfirm(HashMap<String, Object> hashmapParam) {
		return mapper.getParentHpNoConfirm(hashmapParam);
	}

	public List<HashMap<String, Object>> getParentInfo(HashMap<String, Object> hashmapParam) {
		return mapper.getParentInfo(hashmapParam);
	}

	public List<HashMap<String, Object>> getAcaListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getAcaListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getClassListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getClassListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getClsTimeListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getClsTimeListRetrieve(hashmapParam);
	}

	public String getMaxTtime(HashMap<String, Object> hashmapParam) {
		return mapper.getMaxTtime(hashmapParam);
	}

	public List<HashMap<String, Object>> getStdClsTimeListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStdClsTimeListRetrieve(hashmapParam);
	}

	public int userInfoUpdate(HashMap<String, Object> map) {
		return mapper.userInfoUpdate(map);
	}

	public int stdInfoUpdate(HashMap<String, Object> map) {
		return mapper.stdInfoUpdate(map);
	}

	public int stdParentInfoUpdate(HashMap<String, Object> map) {
		return mapper.stdParentInfoUpdate(map);
	}

	public int stdMoveClsTimeDelete(HashMap<String, Object> map) {
		return mapper.stdMoveClsTimeDelete(map);
	}

	public int stdClsTimeDelete(HashMap<String, Object> map) {
		return mapper.stdClsTimeDelete(map);
	}

	public int stdClsTimeCreate(HashMap<String, Object> map) {
		return mapper.stdClsTimeCreate(map);
	}

	public int stdChgLogInfoInsert(HashMap<String, Object> map) {
		return mapper.stdChgLogInfoInsert(map);
	}

	public int stdChgResvCreate(HashMap<String, Object> map) {
		return mapper.stdChgResvCreate(map);
	}

	public void callPrcCreateStdTimeTable(HashMap<String, Object> callPrcParam) {
		System.out.println("BEFORE call pro:(PRC_CREATE_STD_TIME_TABLE) "+callPrcParam);

		mapper.callPrcCreateStdTimeTable(callPrcParam);

		System.out.println("BEFORE call pro:(PRC_CREATE_STD_TIME_TABLE) "+callPrcParam);
	}

	public HashMap<String, Object> getGoodsInfo(HashMap<String, Object> hashmapParam) {
		return mapper.getGoodsInfo(hashmapParam);
	}

	public void callPrcSyncUser(HashMap<String, Object> callPrcSyncUserParam) {
		mapper.callPrcSyncUser(callPrcSyncUserParam);
	}

	public void callApiLmsTeacherClsSync(HashMap<String, Object> callApiLmsTeacherClsSyncParam) {
		mapper.callApiLmsTeacherClsSync(callApiLmsTeacherClsSyncParam);
	}

	public List<HashMap<String, Object>> getTeacherList(HashMap<String, Object> hashmapParam) {
		return mapper.getTeacherList(hashmapParam);
	}

	public List<HashMap<String, Object>> getClassTmList(HashMap<String, Object> hashmapParam) {
		return mapper.getClassTmList(hashmapParam);
	}

	public HashMap<String, Object> getClassSeatInfo(HashMap<String, Object> hashmapParam) {
		return mapper.getClassSeatInfo(hashmapParam);
	}

	public int getChkStdDuplicateClass(HashMap<String, Object> hashmapParam) {
		return mapper.getChkStdDuplicateClass(hashmapParam);
	}

	public int clsSpcCreate(HashMap<String, Object> hashmapParam) {
		return mapper.clsSpcCreate(hashmapParam);
	}

	public int addStdInfoLogChg(HashMap<String, Object> hashmapParam) {
		return mapper.addStdInfoLogChg(hashmapParam);
	}

	public int clsSpcSmsInfoCreate(HashMap<String, Object> hashmapParam) {
		return mapper.clsSpcSmsInfoCreate(hashmapParam);
	}

	public List<HashMap<String, Object>> getLessonEndDate(HashMap<String, Object> hashmapParam) {
		return mapper.getLessonEndDate(hashmapParam);
	}

	public List<HashMap<String, Object>> getAtdListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getAtdListRetrieve(hashmapParam);
	}

	public void deleteStdUser(HashMap<String, Object> callPrcParam) {
		mapper.deleteStdUser(callPrcParam);
	}

	public void deleteStdInfo(HashMap<String, Object> callPrcParam) {
		mapper.deleteStdInfo(callPrcParam);
	}

	public void deleteStdParents(HashMap<String, Object> callPrcParam) {
		mapper.deleteStdParents(callPrcParam);
	}

	public int chgStdClassBatchDel(HashMap<String, Object> hashmapParam) {
		return mapper.chgStdClassBatchDel(hashmapParam);
	}

	public int chgStdClassBatchInfo(HashMap<String, Object> hashmapParam) {
		return mapper.chgStdClassBatchInfo(hashmapParam);
	}

	public int addClsStdBatchInfo(HashMap<String, Object> hashmapParam) {
		return mapper.addClsStdBatchInfo(hashmapParam);
	}

	public List<HashMap<String, Object>> getStdExamList(HashMap<String, Object> hashmapParam) {
		return mapper.getStdExamList(hashmapParam);
	}

	public List<HashMap<String, Object>> getStdClassList(HashMap<String, Object> hashmapParam) {
		return mapper.getStdClassList(hashmapParam);
	}

	public int setStdExamAdd(HashMap<String, Object> hashmapParam) {
		return mapper.setStdExamAdd(hashmapParam);
	}

	public int chkStdExamData(HashMap<String, Object> hashmapParam) {
		return mapper.chkStdExamData(hashmapParam);
	}

	public List<HashMap<String, Object>> getAcaLiveClassOperYn(HashMap<String, Object> hashmapParam) {
		return mapper.getAcaLiveClassOperYn(hashmapParam);
	}

	public List<HashMap<String, Object>> getStdClassBatchList(HashMap<String, Object> hashmapParam) {
		return mapper.getStdClassBatchList(hashmapParam);
	}

	public List<HashMap<String, Object>> getStdClsBatchSrchInfo(HashMap<String, Object> hashmapParam) {
		return mapper.getStdClsBatchSrchInfo(hashmapParam);
	}
	
	public List<HashMap<String, Object>> getStdClsBatchMoveInfo(HashMap<String, Object> hashmapParam) {
		return mapper.getStdClsBatchMoveInfo(hashmapParam);
	}

	public int addStdClsBatchMove(HashMap<String, Object> hashmapParam) {
		return mapper.addStdClsBatchMove(hashmapParam);
	}
	
	public int updateCurrClsState(HashMap<String, Object> hashmapParam) {
		return mapper.updateCurrClsState(hashmapParam);
	}
	
	public List<HashMap<String, String>> getStdUnpayInvoiceList(String stdId) {
		return mapper.getStdUnpayInvoiceList(stdId);
	}
	
	public String getInvoiceReqNo() {
		return mapper.getInvoiceReqNo();
	}
	
	public void createInvoiceReqList(HashMap<String, String> hashmap) {
		mapper.createInvoiceReqList(hashmap);
	}
	
	public void insertSmsList(InvoiceMsgVO vo) {
		mapper.insertSmsList(vo);
	}

	public int updateChgAcaUserInfo(HashMap<String, Object> hashmapParam) {
		return mapper.updateChgAcaUserInfo(hashmapParam);
	}
	
	public int updateChgAcaStdInfo(HashMap<String, Object> hashmapParam) {
		return mapper.updateChgAcaStdInfo(hashmapParam);
	}

	public int addStdChgAca(HashMap<String, Object> hashmapParam) {
		return mapper.addStdChgAca(hashmapParam);
	}

}