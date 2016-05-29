<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE>
<html ng-app="souffleApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Souffle Teacher's Index</title>
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
<script src="${initParam.root}resources/angularModules/controllers/teacherIndexController.js"></script>
</head>
<body ng-controller="teacherIndexCtrl" data-ng-init="init()">
<div class="container">
	<div class="btn-group btn-group-justified" role="group" aria-label="...">
		<div class="btn-group" role="group">
			<button type="button" class="btn" ng-click="btnStatusChanger(btnStatus,'studentList')" ng-class="btnStatus[0].active==true ? 'btn-primary' : 'btn-default'">학생목록</button>
		</div>
		<div class="btn-group" role="group">
			<button type="button" class="btn" ng-click="btnStatusChanger(btnStatus,'videoList')" ng-class="btnStatus[1].active==true ? 'btn-primary' : 'btn-default'">동영상 목록</button>
		</div>
		<div class="btn-group" role="group">
			<button type="button" class="btn" ng-click="btnStatusChanger(btnStatus,'videoUpload')" ng-class="btnStatus[2].active==true ? 'btn-primary' : 'btn-default'">동영상업로드</button>
		</div>
	</div>
	<!-- Usage as a class -->
	<div class="clearfix">
	</div>
	<div class="formWrapper jumbotron">
		 선생님 이메일 : {{teacherInfo.memberEmail}} 선생님 이름 : {{teacherInfo.memberName}} 선생님 폰번호 : {{teacherInfo.teacherPhone}}
		<div class="raw">
			<!-- 학생 관리 페이지 -->
			<div class="studentManagementDiv" ng-show="btnStatus[0].active">
				<div class="row">
					<div class="panel panel-success">
						<div class="panel-heading">
							학생 검색
						</div>
						<div class="panel-body">
							<div class="input-group">
								<div class="input-group-btn">
									<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">이메일로찾기 <span class="caret"></span></button>
									<ul class="dropdown-menu" role="menu">
										<li><a href="#">이메일로 찾기</a></li>
										<li><a href="#">전화번호로 초대</a></li>
										<li><a href="#"></a></li>
										<li class="divider"></li>
										<li><a href="#">Separated link</a></li>
									</ul>
								</div>
								<!-- /btn-group -->
								<input type="text" class="form-control" aria-label="..." ng-model="targetStudentEmail">
								<span class="input-group-btn">
								<button class="btn btn-default" type="button" ng-click="findStudent(targetStudentEmail)">검색</button>
								</span>
							</div>
						</div>
						<!-- /input-group -->
						<table class="table " ng-show="findResultDefined">
						<thead>
						<tr>
							<th>
								Name
							</th>
							<th>
								Mail
							</th>
							<th>
								Birth
							</th>
							<th>
								intvite
							</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td>
								{{resultOfFindStudent.memberName}}
							</td>
							<td>
								{{resultOfFindStudent.memberEmail}}
							</td>
							<td>
								{{resultOfFindStudent.studentBirth}}
							</td>
							<td>
								<button class="btn btn-success" ng-click="inviteStudent(resultOfFindStudent)">초대</button>
							</td>
						</tr>
						</tbody>
						</table>
					</div>
					<div class="panel panel-default">
						<!-- Default panel contents -->
						<div class="panel-heading">
							관리 학생
						</div>
						<div class="panel-body">
							<p>
							</p>
						</div>
						<!-- Table -->
						<table class="table">
						<thead>
						<tr>
							<th>
								Name(email)
							</th>
							<th>
								Birth
							</th>
							<th>
								초대상태
							</th>
							<th>
								연결끊기
							</th>
							<th>
								Details
							</th>
						</tr>
						<tr ng-repeat="student in teacherInfo.stuTeaRelList">
							<td>
								{{student.studentInfo.memberName}}({{student.studentEmail}})
							</td>
							<td>
								{{student.studentInfo.studentBirth}}
							</td>
							<td>
								<button class="btn btn-success" ng-if="student.isConnected==1">초대완료</button>
								<button class="btn btn-danger" ng-if="student.isConnected==0">초대중</button>
							</td>
							<td>
								<button class="btn btn-danger" ng-click="diconnectRelation(student)">연결끊기</button>
							</td>
							<td>
								<button class="btn btn-primary">Detail</button>
							</td>
							<td>
							</td>
							<td>
							</td>
						</tr>
						</thead>
						</table>
					</div>
				</div>
			</div>
			<!-- 선생님 가입폼 -->
			<div class="videoMangementDiv" ng-show="btnStatus[1].active">
				 동영상 관리 페이지
			</div>
			<!-- 학생가입 폼 -->
			<div class="videoUploadDiv" ng-show="btnStatus[2].active">
				<!--  enctype="multipart/form-data" -->
				<form name="videoUploadForm" id="videoUploadForm" novalidate ng-submit="uploadVideo()">
					<div class="form-group">
						<label for="videoTitle">강의 제목</label>
						<input type="text" class="form-control" name="videoTitle" id="videoTitle" placeholder="강의제목을 입력해주세요" ng-model="newVideo.videoTitle" ng-minlength="5" ng-maxlength="20" name="videoTitle" required>
						<div class="error" ng-show="videoUploadDiv.videoTitle.$invalid && videoUploadDiv.videoTitle.$dirty">
							<span ng-show="videoUploadDiv.videoTitle.$error">
							강의 제목을 입력해주세요 </span>
						</div>
					</div>
					<div class="form-group">
						<label for="teacherName">파일 업로드</label>
						{{file.name}}
						<button class="button" ngf-select ng-model="file" name="file" ngf-pattern="'video/*'" ngf-accept="'video/*'" ngf-max-size="200MB" ngf-min-height="100">
							영상 업로드
						</button>
						<button type="submit" ng-click="videoSubmit()">submit</button>
					</div>
					<div class="form-group">
						<label for="memberPassword">강의 설명</label>
						<textarea class="form-control" rows="3"  placeholder="강의에 대한 간략한 설명" ng-model="newVideo.videoInst" ng-minlength="5" ng-maxlength="100"  ></textarea>
					</div>
					<div class="form-group">
						<div>
							<div class="text-left" style="display: inline">
								<label for="teacherName">수신자 선택</label>
							</div>
							<div class="text-right" style="display: inline">
								<button class="btn btn-success" ng-click="">전체선택</button>
								<button class="btn btn-danger" ng-click="">전체취소</button>
							</div>
						</div>
						<table class="table">
						<thead>
						<tr>
							<th>
								Name(email)
							</th>
							<th>
								수신인지정
							</th>
						</tr>
						<tr ng-repeat="student in teacherInfo.stuTeaRelList" ng-if="student.isConnected==1">
							<td>
								{{student.studentInfo.memberName}}({{student.studentEmail}})
							</td>
							<td>
								<button class="btn btn-success" ng-hide="isSelected(student)" ng-click="selectStudent(student)">지정</button>
								<button class="btn btn-danger" ng-show="isSelected(student)" ng-click="cancelSelectStudent(student)">취소</button>
							</td>
						</tr>
						</thead>
						</table>
					</div>
					<div class="progress">
						<div class="progress-bar" role="progressbar" aria-valuenow="{{progressPercentage}}" aria-valuemin="0" aria-valuemax="100" style="width: {{progressPercentage}}%;">
							 {{progressPercentage}}%
						</div>
					</div>
					<button type="button" class="btn btn-success btn-lg btn-block" ng-disabled="videoUploadForm.$invalid" ng-click="videoSubmit()">동영상 업로드 및 링크배포</button>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>