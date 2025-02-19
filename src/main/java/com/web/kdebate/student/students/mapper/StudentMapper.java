package com.web.kdebate.student.students.mapper;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.web.kdebate.student.students.domain.InvoiceMsgVO;
import com.web.kdebate.student.students.domain.StudentVO;


@Mapper
public interface StudentMapper {

	int getQueryTotalCnt();
	
	List<HashMap<String, Object>> getSelectSchMst(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStudentListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStudentDtlListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getMngClassList(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getMngClassList1(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getMngClassDtlList(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getMngClassDtlList1(HashMap<String, Object> hashmapParam);


	String getPcodeUseYn(HashMap<String, Object> hashmapParam);


	List<HashMap<String, Object>> getSrchTbCodeList(HashMap<String, Object> hashmapParam);


	List<HashMap<String, Object>> getSrchAcdCodeList(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getSrchInstructor(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getCorpListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getGoodsListRetrieve(HashMap<String, Object> hashmapParam);

	String getInvNo();

	int chkUserId(String value);

	int studentChgLvlInfoCreate(HashMap<String, Object> map);

	int studentChgLvlInfoUpdate(HashMap<String, Object> map);

	int getStdAtdCdConfirm(HashMap<String, Object> hashmapParam);
	
	int chgLogInfoBatchCreate(HashMap<String, Object> hashmapParam);
	
	String getReasonChkCd(HashMap<String, Object> hashmapParam);
	
	int stdAtdChkUpdate(HashMap<String, Object> hashmapParam);
	
	String getStudentMbrTp(HashMap<String, Object> hashmapParam);
	
	int stdStatusUpdate(HashMap<String, Object> hashmapParam);

	String getStdId();

	int userInfoCreate(@Valid StudentVO studentVO);

	int studentInfoCreate(@Valid StudentVO studentVO);

	int stdClassCreate(@Valid StudentVO studentVO);

	int stdInfoChgLogCreate(StudentVO studentVO);

	int hpDupChk(StudentVO studentVO);

	String getUserId(StudentVO studentVO);

	String getStdParentId();

	int parentInfoCreate(StudentVO studentVO);

	int parentCreate(StudentVO studentVO);

	List<HashMap<String, Object>> stdBroList(StudentVO studentVO);

	int stdBroCreate(StudentVO studentVO);

	int broStdCreate(StudentVO studentVO);

	int stdInfoSmsCreate(StudentVO studentVO);

	int invoiceInfoCreate(StudentVO vo);

	int invoiceDtlInfoCreate(StudentVO vo);

	String getParentId(HashMap<String, Object> hashmapParam);

	String getNotiSeq();

	int notiInfoCreate(StudentVO vo);

	int notiReadCreate(StudentVO vo);

	int smsInfoCreate(StudentVO vo);

	//int getStudentIdConfirm(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getStudentIdConfirm(HashMap<String, Object> hashmapParam);
	
	int getParentHpNoConfirm(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getParentInfo(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getAcaListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getClassListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getClsTimeListRetrieve(HashMap<String, Object> hashmapParam);

	String getMaxTtime(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStdClsTimeListRetrieve(HashMap<String, Object> hashmapParam);

	int userInfoUpdate(HashMap<String, Object> map);

	int stdInfoUpdate(HashMap<String, Object> map);

	int stdParentInfoUpdate(HashMap<String, Object> map);

	int stdMoveClsTimeDelete(HashMap<String, Object> map);

	int stdClsTimeDelete(HashMap<String, Object> map);

	int stdClsTimeCreate(HashMap<String, Object> map);

	int stdChgLogInfoInsert(HashMap<String, Object> map);

	int stdChgResvCreate(HashMap<String, Object> map);

	void callPrcCreateStdTimeTable(HashMap<String, Object> callPrcParam);

	HashMap<String, Object> getGoodsInfo(HashMap<String, Object> hashmapParam);

	void callPrcSyncUser(HashMap<String, Object> callPrcSyncUserParam);

	void callApiLmsTeacherClsSync(HashMap<String, Object> callApiLmsTeacherClsSyncParam);

	List<HashMap<String, Object>> getTeacherList(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getClassTmList(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getClassSeatInfo(HashMap<String, Object> hashmapParam);

	int getChkStdDuplicateClass(HashMap<String, Object> hashmapParam);

	int clsSpcCreate(HashMap<String, Object> hashmapParam);

	int addStdInfoLogChg(HashMap<String, Object> hashmapParam);

	int clsSpcSmsInfoCreate(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getLessonEndDate(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getAtdListRetrieve(HashMap<String, Object> hashmapParam);

	void deleteStdUser(HashMap<String, Object> callPrcParam);
	void deleteStdInfo(HashMap<String, Object> callPrcParam);
	void deleteStdParents(HashMap<String, Object> callPrcParam);

	int chgStdClassBatchDel(HashMap<String, Object> hashmapParam);

	int chgStdClassBatchInfo(HashMap<String, Object> hashmapParam);

	int addClsStdBatchInfo(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStdExamList(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStdClassList(HashMap<String, Object> hashmapParam);

	int setStdExamAdd(HashMap<String, Object> hashmapParam);

	int chkStdExamData(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getAcaLiveClassOperYn(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStdClassBatchList(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStdClsBatchSrchInfo(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getStdClsBatchMoveInfo(HashMap<String, Object> hashmapParam);

	int addStdClsBatchMove(HashMap<String, Object> hashmapParam);

	int updateCurrClsState(HashMap<String, Object> hashmapParam);
	
	List<HashMap<String, String>> getStdUnpayInvoiceList(String stdId);
	
	String getInvoiceReqNo();
	
	void createInvoiceReqList(HashMap<String, String> hashmap);
	
	void insertSmsList(InvoiceMsgVO vo);

	int updateChgAcaUserInfo(HashMap<String, Object> hashmapParam);
	
	int updateChgAcaStdInfo(HashMap<String, Object> hashmapParam);
	
	int addStdChgAca(HashMap<String, Object> hashmapParam);

}
