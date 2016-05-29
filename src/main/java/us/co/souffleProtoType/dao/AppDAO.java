package us.co.souffleProtoType.dao;

import java.util.HashMap;
import java.util.List;

public interface AppDAO {

	String isDuplicated(String targetId);

	void insertMemberInfo(HashMap<String, String> parameter);

	void insertTeacherInfo(HashMap<String, String> parameter);

	void insertStudentInfo(HashMap<String, String> parameter);
	void insertTeaAcaRelInfo(HashMap<String, String> parameter);

	int getCompleteRate(String memberId);

	List<HashMap<String, String>> getNewLecture(String memberId);

	float getCompleteReceiverByReceiptId(String receiptId);

	float getTotalReceiverByReceiptId(String receiptId);

	List<HashMap<String, String>> getDeadlineLectureMapList(String memberId);

}
