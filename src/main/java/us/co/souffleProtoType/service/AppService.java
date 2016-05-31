package us.co.souffleProtoType.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import us.co.souffleProtoType.vo.MemberInfo;

public interface AppService {

	boolean isDuplicated(String targetId);

	void insertMemberInfo(HashMap<String, String> parameter);

	HashMap<String, Object> getDashboardDataForStudent(HashMap<String, Object> resultMap);

	HashMap<String, Object> getTeachersData(HashMap<String, Object> resultMap);

	HashMap<String, Object> getLectureListOrderbyUpdateDate(HashMap<String, Object> resultMap);

}
