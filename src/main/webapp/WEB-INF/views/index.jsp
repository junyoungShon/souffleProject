<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html ng-app="souffleApp">
<head>
	<title>Souffle Index</title>
	
	<!-- css 파일 임포트 -->
	<link href="${initParam.root}resources/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${initParam.root}resources/bootstrap/dist/css/bootstrap-theme.css" rel="stylesheet"/>
    <link href="${initParam.root}resources/css/custom.css" rel="stylesheet"/>
	
	
	<!-- 자바스크립트 파일 임포트 -->
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="${initParam.root}resources/jquery/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="${initParam.root}resources/angular/angular.js"></script>
        <script src="${initParam.root}resources/angular/modules/angular-file-upload-shim.min.js"></script>
    <script src="${initParam.root}resources/angular/modules/angular-file-upload.min.js"></script>
    <!-- AngularJS Components -->
    <script src="${initParam.root}resources/angularModules/app/souffleApp.js"></script>
    <script src="${initParam.root}resources/angularModules/controllers/indexController.js"></script>
    
    
</head>
<body>
	<div class="page-header">
		  <h1 class="text-center">Souffle Index<small></small></h1>
	</div>
<div class="container" ng-controller="indexCtrl">
	<div class="btn-group btn-group-justified" role="group" aria-label="...">
		<div class="btn-group" role="group">
	    <button type="button" class="btn" ng-click="btnStatusChanger(btnStatus,'login')" ng-class="btnStatus[0].active==true ? 'btn-primary' : 'btn-default'">로그인</button>
	  </div>
	  <div class="btn-group" role="group">
	    <button type="button" class="btn" ng-click="btnStatusChanger(btnStatus,'teacherJoin')" ng-class="btnStatus[1].active==true ? 'btn-primary' : 'btn-default'">선생님 가입</button>
	  </div>
	  <div class="btn-group" role="group">
	    <button type="button" class="btn" ng-click="btnStatusChanger(btnStatus,'studentJoin')" ng-class="btnStatus[2].active==true ? 'btn-primary' : 'btn-default'">학생 가입</button>
	  </div>
	  
	</div>
	<!-- Usage as a class -->
	<div class="clearfix"></div>
	<div class="formWrapper jumbotron">
		<div class="raw">
			<!-- 로그인 폼 -->
			<div class="loginFormDiv" ng-show="btnStatus[0].active">
				<div class="page-header">
					  <h3 class="text-center">로그인<small></small></h3>
				</div>
				<form name="memberLoginForm" id="memberLoginForm" novalidate ng-submit="memberLogin(loginMember)">
				 <div class="form-group">
				    <label for="memberEmail">아이디</label>
				    <input type="email" class="form-control" name="memberEmail" id="memberEmail" placeholder="이메일을 입력하세요" ng-model="loginMember.memberEmail" 
				    ng-pattern="regex.emailRegex" ng-minlength="5" ng-maxlength="20" name="memberEmail" required>
				 
				    <div class="error" ng-show="memberLoginForm.memberEmail.$invalid && memberLoginForm.memberEmail.$dirty">
                    	<span ng-show="memberLoginForm.memberEmail.$error.email">
                        	이메일 주소를 정확하게 입력해주세요!
                        </span>
               	   </div>
				  </div>
				  <div class="form-group">
				    <label for="memberPassword">암호</label>
				    <input type="password" class="form-control" name="memberPassword" id="memberPassword" placeholder="암호" ng-model="loginMember.memberPassword" 
				    ng-minlength="5" ng-maxlength="20" required>
				    	<span ng-show="memberLoginForm.memberPassword.$error">
                        	암호를 정확하게 입력해주세요!
                        </span>
				  </div>
				  <div class="checkbox">
				    <label>
				      <input type="checkbox"> 입력을 기억합니다
				    </label>
				  </div>
					  <button type="submit" class="btn btn-success btn-lg  btn-block" ng-disabled="memberLoginForm.$invalid">Login</button>
				</form>
			</div>
			<!-- 선생님 가입폼 -->
			<div class="teacherJoinFormDiv" ng-show="btnStatus[1].active">
				<div class="page-header">
					  <h3 class="text-center">강사님 가입폼<small></small></h3>
				</div>
				<form name="teacherJoinForm" id="teacherJoinForm" novalidate ng-submit="addUser('teacher',newUser)">
				 <div class="form-group">
				    <label for="memberEmail">아이디</label>
				    <input type="email" class="form-control" name="memberEmail" id="memberEmail" placeholder="이메일을 입력하세요" ng-model="newUser.memberEmail" 
				    ng-pattern="regex.emailRegex" ng-minlength="5" ng-maxlength="20" name="memberEmail" required>
				 
				    <div class="error" ng-show="teacherJoinForm.memberEmail.$invalid && teacherJoinForm.memberEmail.$dirty">
                    	<span ng-show="teacherJoinForm.memberEmail.$error.email">
                        	이메일 주소를 정확하게 입력해주세요!
                        </span>
               	   </div>
				  </div>
				  <div class="form-group">
				    <label for="memberPassword">암호</label>
				    <input type="password" class="form-control" name="memberPassword" id="memberPassword" placeholder="암호" ng-model="newUser.memberPassword" 
				    ng-minlength="5" ng-maxlength="20" required>
				    	<span ng-show="teacherJoinForm.memberPassword.$error">
                        	암호를 정확하게 입력해주세요!
                        </span>
				  </div>
				  <div class="form-group">
				    <label for="teacherName">강사이름</label>
				    <input type="text" class="form-control" name="memberName" id="memberName" placeholder="강사님의 닉네임 또는 성함을 적어주세요" ng-model="newUser.memberName" 
				    ng-minlength="1" ng-maxlength="7" required>
				    <span ng-show="teacherJoinForm.memberName.$error">
                        	이름을 정확하게 입력해주세요!
                    </span>
				  </div>
				  <div class="form-group">
				    <label for="teacherName">소속학원코드</label>
				    <input type="text" class="form-control" name="academyCode" id="academyCode" placeholder="강사님이 소속한 학원의 코드를 입력해주세요 ex)0200001" ng-model="newUser.academyCode" 
				    ng-minlength="5" ng-maxlength="10" ng-readonly="checkAcademyCode.isChecked" required>
				    <button class="btn btn-default" ng-hide="checkAcademyCode.isChecked" ng-click="checkAcademyCode.checkAcademyFunc(newUser.academyCode)">학원 코드 확인</button>
				    <button class="btn btn-default" ng-show="checkAcademyCode.isChecked" ng-click="checkAcademyCode.modifyAcademyFunc()">소속 학원 수정</button>
				    <span ng-show="checkAcademyCode.isChecked"> : {{checkAcademyCode.confirmedAcademyName}}</span>
				  </div>
				  <div class="form-group">
				    <label for="teacherName">담당과목</label>
				    <input type="text" class="form-control" name="teacherSubject" id="teacherSubject" placeholder="강사님의 담당과목을 입력해주세요" ng-model="newUser.teacherSubject" 
				    ng-minlength="2" ng-maxlength="5" required>
				    <span ng-show="teacherJoinForm.teacherSubject.$error">
                        	담당과목을 정확하게 입력해주세요!
                        </span>
				  </div>
				  <div class="form-group">
				    <label for="teacherName">연락처</label>
				    <input type="tel" class="form-control" name="teacherPhone" id="teacherPhone" placeholder="강사님의 전화번호를 적어주세요" ng-model="newUser.teacherPhone" 
				   required >
				    <span ng-show="teacherJoinForm.teacherPhone.$error">
                        	연락처를 정확하게 입력해주세요!
                        </span>
				  </div>
				  <div class="checkbox">
				    <label>
				      <input type="checkbox" ng-model="newUser.agreed" required> 약관의 내용을 모두 숙지하였으며 동의합니다.
				    </label>
				  </div>
					  <button type="submit" class="btn btn-success btn-lg  btn-block" ng-disabled="teacherJoinForm.$invalid">회원가입</button>
				</form>
			</div>
			<!-- 학생가입 폼 -->
			<div class="studentJoinFormDiv" ng-show="btnStatus[2].active">
				<div class="page-header">
					  <h3 class="text-center">학생 가입폼<small></small></h3>
				</div>
				<form name="studentJoinForm" id="studentJoinForm" novalidate ng-submit="addUser('student',newUser)">
				  <div class="form-group">
				    <label for="memberEmail">아이디</label>
				    <input type="email" class="form-control" name="memberEmail" id="memberEmail" placeholder="이메일을 입력하세요" ng-model="newUser.memberEmail" 
				    ng-pattern="regex.emailRegex" ng-minlength="5" ng-maxlength="20" name="memberEmail" required>
				 
				    <div class="error" ng-show="teacherJoinForm.memberEmail.$invalid && teacherJoinForm.memberEmail.$dirty">
                    	<span ng-show="teacherJoinForm.memberEmail.$error.email">
                        	이메일 주소를 정확하게 입력해주세요!
                        </span>
               	   </div>
				  </div>
				  <div class="form-group">
				    <label for="memberPassword">암호</label>
				    <input type="password" class="form-control" name="memberPassword" id="memberPassword" placeholder="암호" ng-model="newUser.memberPassword" 
				    ng-minlength="5" ng-maxlength="20" required>
				    	<span ng-show="teacherJoinForm.memberPassword.$error">
                        	암호를 정확하게 입력해주세요!
                        </span>
				  </div>
				   <div class="form-group">
				    <label for="teacherName">학생이름</label>
				    <input type="text" class="form-control" name="memberName" id="memberName" placeholder="학생이름 닉네임 또는 성함을 적어주세요" ng-model="newUser.memberName" 
				    ng-minlength="1" ng-maxlength="7" required>
				    <span ng-show="teacherJoinForm.memberName.$error">
                        	이름을 정확하게 입력해주세요!
                    </span>
				  </div>
				   <div class="form-group">
				    <label for="teacherName">학생생일</label>
				    <input type="date" class="form-control" name="studentBirth" id="studentBirth" ng-model="newUser.studentBirth" 
				    required>
				   
				  </div>
				   <div class="checkbox">
				    <label>
				      <input type="checkbox" ng-model="newUser.agreed" required> 약관의 내용을 모두 숙지하였으며 동의합니다.
				    </label>
				  </div>
					 <button type="submit" class="btn btn-success btn-lg  btn-block" ng-disabled="studentJoinForm.$invalid">회원가입</button>
				</form>
			</div>
			
		</div>
	</div>
</div>
</body>
</html>
