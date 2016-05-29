package us.co.souffleProtoType.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import us.co.souffleProtoType.service.TeacherService;
import us.co.souffleProtoType.service.UploadVideo;
import us.co.souffleProtoType.vo.MemberInfo;
import us.co.souffleProtoType.vo.StuTeaRel;
import us.co.souffleProtoType.vo.StudentInfo;

@Controller
public class TeacherController {
	
	@Resource	
	private TeacherService teacherService;
	
	@Resource
	private UploadVideo uploadVide;
	/**
	 * <pre>
	 *  1. 개요 : 특별한 데이터 교환 없이 페이지만 이동할 때 사용하는 메서드 
	 *  2. 처리내용 : 뷰아이디와 일치하는 .jsp파일로 이동시킴
	 * </pre> 
	 * @Method Name : goAnyWhere
	 * 2016. 5. 19.
	 * junyoung
	 * @param viewId
	 * @return
	 */
	@RequestMapping("{viewId}.do")
	public ModelAndView goAnyWhere(@PathVariable String viewId){
		return new ModelAndView(viewId);
		
	}
	
	@RequestMapping("checkAcademy.do")
	@ResponseBody
	public HashMap<String, String> checkAcademy(int academyCode){
		HashMap<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("academyName", "교연학원");
		resultMap.put("isValid", "t");
		return resultMap;
	}
	@RequestMapping (value="teacherJoin.do", method=RequestMethod.POST)
	@ResponseBody
	public void teacherJoin(@RequestBody HashMap<String,String> parameter){
		//TeacherInfo teacherInfo = (TeacherInfo) parameter;
		System.out.println(parameter);
		teacherService.teacherJoin(parameter);
	}
	
	/*@RequestMapping (value="studentJoin.do", method=RequestMethod.POST)
	@ResponseBody
	public void studentJoin(@RequestBody HashMap<String,String> parameter){
		//TeacherInfo teacherInfo = (TeacherInfo) parameter;
		System.out.println(parameter);
		teacherService.studentJoin(parameter);
	}*/
	
	//왜 반환형을 스트링으로 하면 안될까??
	@RequestMapping (value="memberLogin.do", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> memberLogin(@RequestBody HashMap<String,String> parameter,HttpServletRequest request){
		//TeacherInfo teacherInfo = (TeacherInfo) parameter;
		HashMap<String, String> resultMap = new HashMap<String, String>();
		System.out.println("오긴오나");
		String result = null;
		MemberInfo memberInfo = teacherService.memberLogin(parameter,request);
		if(memberInfo!=null){
			if(memberInfo.getMemberRole().equals("teacher")){
				result = "teacher";
			}else if(memberInfo.getMemberRole().equals("student")){
				result = "student";
			}
		}else{
			result = "loginFail";
		}
		System.out.println(result);
		resultMap.put("result", result);
		return resultMap;
	}
	//티처인덱스로 페이지 이동
	@RequestMapping("teacher_teacherIndex.do")
	public ModelAndView goTeacherIndex(HttpServletRequest request){
		
		return new ModelAndView("teacher/teacherIndex");
	}
	
	@RequestMapping("getTeacherInfoInTeacherIndex.do")
	@ResponseBody
	public HashMap<String,Object> getTeacherInfoInTeacherIndex(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		HashMap<String,Object> result = null;
		if(session!=null){
			MemberInfo teacherInfo = (MemberInfo) session.getAttribute("teacherInfoInSession");
			//티처 인덱스에 필요한 자료들을 받아온다.
			result = teacherService.getTeacherInfoInTeacherIndex(teacherInfo.getMemberEmail());
		}
		return result;
	}
	
	//왜 반환형을 스트링으로 하면 안될까??
	@RequestMapping (value="findStudentByEamil.do", method=RequestMethod.GET)
	@ResponseBody
	public StudentInfo findStudentByEamil(String memberEmail,HttpServletRequest request){
		
		StudentInfo studentInfo = teacherService.findStudentByEamil(memberEmail);
		return studentInfo;
	}
	//왜
	@RequestMapping (value="inviteStudentByEamil.do", method=RequestMethod.GET)
	@ResponseBody
	public StuTeaRel inviteStudentByEamil(String memberEmail,HttpServletRequest request){
		System.out.println(memberEmail);
		StuTeaRel stuTeaRel = teacherService.inviteStudentByEamil(memberEmail,request);
		return stuTeaRel;
	}
	
	@RequestMapping (value="diconnectRelation.do", method=RequestMethod.GET)
	@ResponseBody
	public void diconnectRelation(String memberEmail,HttpServletRequest request){
		System.out.println(memberEmail);
		teacherService.diconnectRelation(memberEmail,request);
	}
	
	@RequestMapping("student_studentIndex.do")
	public ModelAndView studentIndex(){
		return new ModelAndView("student/studentIndex");
	}
	
	@RequestMapping(value = "test_videoUpload.do")
	@ResponseBody
	public HashMap<String,String> testVideoUpload(@RequestParam("file") MultipartFile file) throws IOException{
		File convFile = new File(file.getOriginalFilename());
	    convFile.createNewFile(); 
	    FileOutputStream fos = new FileOutputStream(convFile); 
	    fos.write(file.getBytes());
	    fos.close(); 
        //System.out.println(String.format("receive %s from %s", file.getOriginalFilename(), username));
		String youtubeVideoId = uploadVide.uploadFile(convFile);
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("result",youtubeVideoId);
		return result;
	}
	@RequestMapping (value="insertVideoInfo.do", method=RequestMethod.POST)
	@ResponseBody
	public void insertVideoInfo(@RequestBody HashMap<String,Object> parameter,HttpServletRequest request){
		HttpSession session = request.getSession();
		parameter.put("teacherEmail", ((MemberInfo) session.getAttribute("teacherInfoInSession")).getMemberEmail());
		//teacherService.studentJoin(parameter);
		teacherService.insertVideoInfo(parameter);
	}
	

	
}
