package us.co.souffleProtoType.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import us.co.souffleProtoType.dao.TeacherDAO;
import us.co.souffleProtoType.vo.MemberInfo;
import us.co.souffleProtoType.vo.StuTeaRel;
import us.co.souffleProtoType.vo.StudentInfo;
import us.co.souffleProtoType.vo.TeacherInfo;

@Service
public class TeacherServiceImpl implements TeacherService{

	@Resource
	private TeacherDAO teacherDAO ;
	
	/**
	 * 
	 */
	@Override
	@Transactional
	public void teacherJoin(HashMap<String, String> parameter) {
		/**
		 * 선생님 회원 가입시 거쳐야 할 과정
		 * 1. memberInfo에 기본적인 선생님 정보 입력
		 * 2. teacherInfo에 선생님에 대한 정보 입력
		 * 3. 학원과 선생님 간의 관계 테이블에 선생님 정보 삽입
		 */
		HashMap<String, String> paraMap = parameter;
		parameter.put("memberRole", "teacher");
		teacherDAO.insertMemberInfo(paraMap);
		teacherDAO.insertTeacherInfo(paraMap);
		teacherDAO.insertTeaAcaRelInfo(paraMap);
	}

	@Override
	@Transactional
	public void studentJoin(HashMap<String, String> parameter) {
		/**
		 * 학생 회원 가입시 거쳐야 할 과정
		 * 1. memberInfo에 기본적인 선생님 정보 입력
		 * 2. studentInfo에 학생에 대한 정보 입력
		 */
	
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String studentBirth = transFormat.format(transFormat.parse(parameter.get("studentBirth")));
			System.out.println(studentBirth);
			parameter.replace("studentBirth",studentBirth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(parameter.get("studentBirth"));
		parameter.put("memberRole", "student");
		teacherDAO.insertMemberInfo(parameter);
		teacherDAO.insertStudentInfo(parameter);
	}

	@Override
	public MemberInfo memberLogin(HashMap<String, String> parameter,HttpServletRequest request) {
		
		MemberInfo memberInfo = teacherDAO.memberLogin(parameter);
		HttpSession session = request.getSession();
		if(memberInfo!=null){
			if(memberInfo.getMemberRole().equals("teacher")){
				session.setAttribute("teacherInfoInSession", memberInfo);
			}else if(memberInfo.getMemberRole().equals("student")){
				session.setAttribute("teacherInfoInSession", memberInfo);
			}
		}
		
		
		return memberInfo;
	}
	
	/**
	 * 선생님이 관리 인덱스로 넘어갈 때 필요한 정보들을 얻와와 줌
	 */
	@Override
	public HashMap<String, Object> getTeacherInfoInTeacherIndex(String memberEmail) {
		
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
		
		TeacherInfo teacherInfo = teacherDAO.getTeacherInfo(memberEmail); 
		// 선생님이 초대한 관리 학생 대상들을 불러온다.
		for(int i=0;i<teacherInfo.getStuTeaRelList().size();i++){
			teacherInfo.getStuTeaRelList().get(i).setStudentInfo(teacherDAO.getStudentInfo(teacherInfo.getStuTeaRelList().get(i).getStudentEmail()));
		}
		resultMap.put("teacherInfo",teacherInfo);
		System.out.println(teacherInfo);
		return resultMap;
	}
	/**
	 * 이메일을 기준으로 학생을 검색합니다.
	 */
	@Override
	public StudentInfo findStudentByEamil(String memberEmail) {
		System.out.println(" 서비스 파라미터 memberEmail :"+memberEmail);
		StudentInfo studentInfo = teacherDAO.findStudentByEamil(memberEmail);
		System.out.println(" 서비스 studentInfo :"+studentInfo);
		return studentInfo;
	}
	/**
	 * 선생님이 관리 페이지에서 학생의 이메일로 초대를 실행합니다.
	 */
	@Override
	public StuTeaRel inviteStudentByEamil(String memberEmail,HttpServletRequest request) {
		//디비에 선생님과 학생의 관계를 설정해 줍니다.
		HttpSession session = request.getSession();
		String teacherEmail = ((MemberInfo) session.getAttribute("teacherInfoInSession")).getMemberEmail();
		StuTeaRel stuTeaRel = new StuTeaRel();
		stuTeaRel.setIsConnected('0');
		stuTeaRel.setStudentEmail(memberEmail);
		stuTeaRel.setTeacherEmail(teacherEmail);
		teacherDAO.insertStuTeaRel(stuTeaRel);
		
		//서버에 반환해줄 StuTeaRel 객체 정보를 갱신해줍니다.
		stuTeaRel.setStudentInfo(teacherDAO.getStudentInfo(memberEmail));
		// TODO Auto-generated method stub
		/*StuTeaRel 
		[getTeacherEmail()=teacher@test.com, getStudentEmail()=student1@test.com, getTeacherInfo()=null, 
		getStudentInfo()=
		StudentInfo [studentBirth=2016-12-29, getMemberEmail()=student1@test.com, getMemberPassword()=null, getMemberName()=손준영, getMemberRole()=student, toString()
		=MemberInfo [memberEmail=student1@test.com, memberPassword=null, memberName=손준영, memberRole=student]]*/
		return stuTeaRel;
	}

	@Override
	public void diconnectRelation(String memberEmail,HttpServletRequest request) {
		//디비에 선생님과 학생의 관계를 설정해 줍니다.
		HttpSession session = request.getSession();
		String teacherEmail = ((MemberInfo) session.getAttribute("teacherInfoInSession")).getMemberEmail();
		StuTeaRel stuTeaRel = new StuTeaRel();
		stuTeaRel.setStudentEmail(memberEmail);
		stuTeaRel.setTeacherEmail(teacherEmail);
		teacherDAO.diconnectRelation(stuTeaRel);
	}

	@Override
	@Transactional
	public void insertVideoInfo(HashMap<String, Object> parameter) {
		/*System.out.println("비디오아이디"+parameter.get("videoId").toString());
		System.out.println("선택된 학생 리스트"+parameter.get("selectedStudentList").toString());
		System.out.println("비디오설명"+parameter.get("videoInst").toString());
		System.out.println("비디오제목"+parameter.get("videoTitle"));
		//teacherService.studentJoin(parameter);
		teacherService.insertVideoInfo(parameter);*/
		System.out.println(parameter.get("videoId"));
		//비디오아이디 ( 유튜브 비디오 아이디, 비디오 설명, 비디오 제목, 선생님 이메일 이 추가되어 videoInfo 테이블에 저장된다.
		teacherDAO.insertVideoInfo(parameter);
		//비디오와 학생을 연결해주는 테이블 
		//비디오의 수신자 정보 등록
		ArrayList<HashMap<String,String>> studentList =  (ArrayList<HashMap<String,String>>) parameter.get("selectedStudentList");
		for( int i=0; i<studentList.size();i++ ){
			HashMap<String,String> paraMap = new HashMap<String, String>();
			System.out.println(studentList.get(i).get("studentEmail"));
			paraMap.put("studentEmail", studentList.get(i).get("studentEmail"));
			paraMap.put("videoId",(String) parameter.get("videoId"));
			System.out.println(paraMap);
			teacherDAO.insertVideoReceiverInfo(paraMap);
		}
	}
}
