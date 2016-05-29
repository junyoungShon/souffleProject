package us.co.souffleProtoType.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class AppDAOImpl implements AppDAO {
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public String isDuplicated(String targetId) {
		return sqlSessionTemplate.selectOne("app.isDuplicated", targetId);
	}

	@Override
	public void insertMemberInfo(HashMap<String, String> parameter) {
		sqlSessionTemplate.insert("app.insertMemberInfo", parameter);
		
	}
	@Override
	public void insertTeacherInfo(HashMap<String, String> parameter) {
		sqlSessionTemplate.insert("app.insertTeacherInfo", parameter);
		
	}
	@Override
	public void insertStudentInfo(HashMap<String, String> parameter) {
		sqlSessionTemplate.insert("app.insertStudentInfo", parameter);
		
	}
	@Override
	public void insertTeaAcaRelInfo(HashMap<String, String> parameter) {
		sqlSessionTemplate.insert("app.insertTeaAcaRelInfo", parameter);
		
	}

	@Override
	public int getCompleteRate(String studentEmail) {
		return sqlSessionTemplate.selectOne("app.getCompleteRate", studentEmail);
	}

	@Override
	public List<HashMap<String, String>> getNewLecture(String memberId) {
		return sqlSessionTemplate.selectList("app.getNewLecture", memberId);
	}

	@Override
	public float getCompleteReceiverByReceiptId(String receiptId) {
		return sqlSessionTemplate.selectOne("app.getCompleteReceiverByReceiptId", receiptId);
	}

	@Override
	public float getTotalReceiverByReceiptId(String receiptId) {
		return sqlSessionTemplate.selectOne("app.getTotalReceiverByReceiptId", receiptId);
	}

	@Override
	public List<HashMap<String, String>> getDeadlineLectureMapList(
			String memberId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("app.getDeadlineLectureMapList", memberId);
	}
	
}
