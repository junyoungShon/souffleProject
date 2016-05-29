package us.co.souffleProtoType.dao;

import java.util.HashMap;

import us.co.souffleProtoType.vo.MemberInfo;
import us.co.souffleProtoType.vo.StuTeaRel;
import us.co.souffleProtoType.vo.StudentInfo;
import us.co.souffleProtoType.vo.TeacherInfo;

public interface TeacherDAO {


	public void insertMemberInfo(HashMap<String, String> parameter);

	public void insertTeacherInfo(HashMap<String, String> parameter);

	public void insertTeaAcaRelInfo(HashMap<String, String> parameter);

	public void insertStudentInfo(HashMap<String, String> parameter);

	public MemberInfo memberLogin(HashMap<String, String> parameter);

	public TeacherInfo getTeacherInfo(String memberEmail);

	public StudentInfo getStudentInfo(String memberEmail);

	public StudentInfo findStudentByEamil(String memberEmail);

	public void insertStuTeaRel(StuTeaRel stuTeaRel);

	public void diconnectRelation(StuTeaRel stuTeaRel);

	public void insertVideoInfo(HashMap<String, Object> parameter);

	public void insertVideoReceiverInfo(HashMap<String, String> paraMap);




}
