package us.co.souffleProtoType.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.aspectj.weaver.ast.Instanceof;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import us.co.souffleProtoType.dao.AppDAO;

@Service
public class AppServiceImpl implements AppService{
	@Resource
	private AppDAO appDAO;

	@Override
	public boolean isDuplicated(String targetId) {
		String memberId = appDAO.isDuplicated(targetId);
		boolean flag = false;
		if(memberId!=null){
			flag = true;
		}
		System.out.println("targetId : " + targetId + "결과값 : "+flag);
		return flag;
	}

	@Override
	@Transactional
	public void insertMemberInfo(HashMap<String, String> parameter) {
		/*{"brith":"2005-15-12","emailId":"sdfadsf@adsf.net","password":"asdfa","memberName":"asdfasdf","phone":"01026789441","birth":"2016-05-25","isAgreed":true}*/
		//{isAgreed=true, password=asdfasdf, phone=01026789441, memberName=asdf, birth=2016-05-26, emailId=adkfljaf@adsf.net}
		HashMap<String, String> paraMap = parameter;
		appDAO.insertMemberInfo(paraMap);
		if(parameter.get("role").equals("teacher")){
			appDAO.insertTeacherInfo(paraMap);
		}else if(parameter.get("role").equals("student")){
			appDAO.insertStudentInfo(paraMap);
		}
	}

	@Override
	public void getDashboardDataForStudent(HashMap<String, Object> resultMap) {
		System.out.println("서비스 전:" + resultMap);
		System.out.println((String)resultMap.get("emailId"));
		// 오늘 날짜를 기준으로 DueDate 까지 완료된 학습의 갯수를 출력합니다.
		int completeReate = appDAO.getCompleteRate((String)resultMap.get("emailId"));

		resultMap.put("completeRate",completeReate);
		
		// 새로운 과제 (확인x) 정보 얻어오기 lectureId, teacherName, dueDate, totalReceiver,completeReceiver,lectureTitle,lectureType
		List<HashMap<String,String>> newLectureMapList = appDAO.getNewLecture((String)resultMap.get("emailId"));
		// 새로운 과제의 receipt_id를 기준으로 총 수신자와 수신자들 중 학습을 완료한 사람의 수를 얻어옴
		for(int i=0;i<newLectureMapList.size();i++){
			String receiptId = String.valueOf(newLectureMapList.get(i).get("lectureReceiptId"));
			float completeReceiver = appDAO.getCompleteReceiverByReceiptId(receiptId);
			float totalReceiver = appDAO.getTotalReceiverByReceiptId(receiptId);
			newLectureMapList.get(i).put("completeReceiver",completeReceiver+"");
			newLectureMapList.get(i).put("totalReceiver",totalReceiver+"");
		}
		resultMap.put("newLectureMapList",newLectureMapList );
		
		// 마감임박과제 과제 (확인x,마감일 3일전인 강의) 정보 얻어오기 lectureId, teacherName, dueDate, totalReceiver,completeReceiver,lectureTitle,lectureType
		List<HashMap<String,String>> deadlineLectureMapList = appDAO.getDeadlineLectureMapList((String)resultMap.get("emailId"));
		for(int i=0;i<deadlineLectureMapList.size();i++){
			String receiptId = String.valueOf(deadlineLectureMapList.get(i).get("lectureReceiptId"));
			float completeReceiver = appDAO.getCompleteReceiverByReceiptId(receiptId);
			float totalReceiver = appDAO.getTotalReceiverByReceiptId(receiptId);
			newLectureMapList.get(i).put("completeReceiver",completeReceiver+"");
			newLectureMapList.get(i).put("totalReceiver",totalReceiver+"");
		}
		resultMap.put("deadlineLectureMapList",deadlineLectureMapList );
		System.out.println("서비스 후:" + resultMap);
		
		
	}
	
	
}
