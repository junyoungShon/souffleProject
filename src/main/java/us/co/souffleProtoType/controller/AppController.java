package us.co.souffleProtoType.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import us.co.souffleProtoType.service.AppService;
import us.co.souffleProtoType.service.TeacherService;
import us.co.souffleProtoType.vo.MemberInfo;

@Controller
public class AppController {
	@Resource
	private AppService appService;
	@Resource
	private TeacherService teacherService;
	
	//아이디 중복검사
	@RequestMapping(value="api_isDuplicated.do", method=RequestMethod.GET)
	@ResponseBody
	public boolean isDuplicated(String targetId){
		return appService.isDuplicated(targetId);
	}
	
	//회원 가입 성공시 토큰 발생시켜준다.
	@RequestMapping (value="api_memberJoin.do", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> memberJoin(@RequestBody HashMap<String,String> parameter){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		appService.insertMemberInfo(parameter);
		resultMap.put("token",Jwts.builder().setSubject(parameter.get("emailId")).claim("roles", parameter.get("role")).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact());
		resultMap.put("success", true);
		return resultMap;
	}
	
	
	//왜 반환형을 스트링으로 하면 안될까??
	@RequestMapping (value="api_memberLogin.do")
	@ResponseBody
	public HashMap<String, Object> memberLogin(@RequestBody HashMap<String,String> parameter,HttpServletRequest request){
		//TeacherInfo teacherInfo = (TeacherInfo) parameter;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		MemberInfo memberInfo = teacherService.memberLogin(parameter,request);
		if(memberInfo!=null){
			if(memberInfo.getMemberRole().equals("teacher")){
				resultMap.put("token",Jwts.builder().setSubject(parameter.get("memberEmail")).claim("roles", "teacher").setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact());
			}else if(memberInfo.getMemberRole().equals("student")){
				resultMap.put("token",Jwts.builder().setSubject(parameter.get("memberEmail")).claim("roles", "student").setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact());
			}
			resultMap.put("success", true);
		}else{
			resultMap.put("success", false);
		}
		return resultMap;
	}

	@RequestMapping (value="api_member_getDashboardData.do")
	@ResponseBody
	public HashMap<String, Object> getDashboardData(HttpServletRequest request){
		final Claims claims = (Claims) request.getAttribute("claims");
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
	
		resultMap.put("emailId", claims.getSubject());
		if(claims.get("roles").equals("teacher")){
			resultMap.put("role", "선생님");
		}else{
			resultMap.put("role", "학생");
			resultMap = appService.getDashboardDataForStudent(resultMap);
		}
		return resultMap;
	}
	@RequestMapping (value="api_member_getTeachersData")
	@ResponseBody
	public HashMap<String, Object> getTeachersData(HttpServletRequest request){
		final Claims claims = (Claims) request.getAttribute("claims");
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println("오긴오냐");
		resultMap.put("emailId", claims.getSubject());
		if(claims.get("roles").equals("teacher")){
			resultMap.put("role", "선생님");
		}else{
			resultMap.put("role", "학생");
			resultMap = appService.getTeachersData(resultMap);
		}
		System.out.println(resultMap);
		return resultMap;
	}
	@RequestMapping (value="api_member_getLectureListOrderbyUpdateDate")
	@ResponseBody
	public HashMap<String, Object> getLectureListOrderbyUpdateDate(HttpServletRequest request){
		final Claims claims = (Claims) request.getAttribute("claims");
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println("오긴오냐");
		resultMap.put("emailId", claims.getSubject());
		if(claims.get("roles").equals("teacher")){
			resultMap.put("role", "선생님");
		}else{
			resultMap.put("role", "학생");
			resultMap = appService.getLectureListOrderbyUpdateDate(resultMap);
		}
		System.out.println(resultMap);
		return resultMap;
	}
}
