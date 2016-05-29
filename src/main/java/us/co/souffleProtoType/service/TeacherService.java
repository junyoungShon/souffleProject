package us.co.souffleProtoType.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import us.co.souffleProtoType.vo.MemberInfo;
import us.co.souffleProtoType.vo.StuTeaRel;
import us.co.souffleProtoType.vo.StudentInfo;

public interface TeacherService {


	public void teacherJoin(HashMap<String, String> parameter);

	public void studentJoin(HashMap<String, String> parameter);

	public MemberInfo memberLogin(HashMap<String, String> parameter,HttpServletRequest request);

	public HashMap<String,Object> getTeacherInfoInTeacherIndex(String memberEmail);

	public StudentInfo findStudentByEamil(String memberEmail);

	public StuTeaRel inviteStudentByEamil(String memberEmail,
			HttpServletRequest request);

	public void diconnectRelation(String memberEmail,
			HttpServletRequest request);

	public void insertVideoInfo(HashMap<String, Object> parameter);



	
}
