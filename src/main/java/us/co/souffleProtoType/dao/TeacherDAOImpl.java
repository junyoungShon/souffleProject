package us.co.souffleProtoType.dao;

import java.util.HashMap;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import us.co.souffleProtoType.vo.MemberInfo;
import us.co.souffleProtoType.vo.StuTeaRel;
import us.co.souffleProtoType.vo.StudentInfo;
import us.co.souffleProtoType.vo.TeacherInfo;

@Repository
public class TeacherDAOImpl implements TeacherDAO{
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insertMemberInfo(HashMap<String, String> parameter) {
		sqlSessionTemplate.insert("teacher.insertMemberInfo", parameter);
		
	}
	@Override
	public void insertTeacherInfo(HashMap<String, String> parameter) {
		sqlSessionTemplate.insert("teacher.insertTeacherInfo", parameter);
		
	}
	@Override
	public void insertTeaAcaRelInfo(HashMap<String, String> parameter) {
		sqlSessionTemplate.insert("teacher.insertTeaAcaRelInfo", parameter);
		
	}
	@Override
	public void insertStudentInfo(HashMap<String, String> parameter) {
		sqlSessionTemplate.insert("teacher.insertStudentInfo", parameter);
	}
	@Override
	public MemberInfo memberLogin(HashMap<String, String> parameter) {
		return sqlSessionTemplate.selectOne("teacher.memberLogin", parameter);
	}
	@Override
	public TeacherInfo getTeacherInfo(String memberEmail) {
		return sqlSessionTemplate.selectOne("teacher.getTeacherInfo", memberEmail);
	}
	@Override
	public StudentInfo getStudentInfo(String memberEmail) {
		return sqlSessionTemplate.selectOne("teacher.getStudentInfo", memberEmail);
	}
	@Override
	public StudentInfo findStudentByEamil(String memberEmail) {
		return sqlSessionTemplate.selectOne("teacher.findStudentByEamil", memberEmail);
	}
	@Override
	public void insertStuTeaRel(StuTeaRel stuTeaRel) {
		sqlSessionTemplate.insert("teacher.insertStuTeaRel", stuTeaRel);
	}
	@Override
	public void diconnectRelation(StuTeaRel stuTeaRel) {
		sqlSessionTemplate.delete("teacher.diconnectRelation", stuTeaRel);
	}
	@Override
	public void insertVideoInfo(HashMap<String, Object> parameter) {
		sqlSessionTemplate.insert("teacher.insertVideoInfo", parameter);
	}
	@Override
	public void insertVideoReceiverInfo(HashMap<String, String> paraMap) {
		sqlSessionTemplate.insert("teacher.insertVideoReceiverInfo", paraMap);	
	}
	
}
