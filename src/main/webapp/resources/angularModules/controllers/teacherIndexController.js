angular.module("souffleApp")
	
	.controller("teacherIndexCtrl",['$scope','$http','ajaxUrl','Upload',function($scope,$http,ajaxUrl,Upload){
		
		//페이지가 로딩될 때 필요한 데이터를 요청하고 모델을 형성한다.
		$scope.init = function () {
			$http({
				method: 'get', //방식
				url: ajaxUrl+'getTeacherInfoInTeacherIndex.do', /* 통신할 URL */
				dataType:'json',
				headers: {'Content-Type': 'application/json; charset=utf-8'} //헤더 
			})
			.success(function(data, status, headers, config) {
				//선생님 정보를 담는다. 선생님의 기본 인적 사항과 선생님이 현재 관리하는 학색들의 정보가 담겨있다.
				$scope.teacherInfo = data.teacherInfo;
			})
			.error(function(data, status, headers, config) {
				console.log(status);
			});
		};
		
		
		//버튼의 상대개체 배열
		$scope.btnStatus = [
		                    {btnName : "studentList" , active : true},
		                    {btnName : "videoList" , active : false},
		                    {btnName : "videoUpload" , active : false}
		                    ]
		//버튼의 상태를 바꿔준다.
		$scope.btnStatusChanger = function(btnStatus,btnName){
			for(var i=0;i<btnStatus.length;i++){
				if(btnStatus[i].btnName == btnName){
					btnStatus[i].active = true;
				}else{
					btnStatus[i].active = false;
				}
			}
		}
		/*
		<tr ng-repeat="student in teacherInfo.stuTeaRelList">
			<td>{{student.studentInfo.memberName}}({{student.studentEmail}})</td>
			<td>{{student.studentInfo.studentBirth}}</td>
		*/
		//선생님이 입력한 데이터를 기준으로 학생을 찾아줍니다.
		$scope.findStudent = function(){
			var flag=true;
			for(var i=0;i<$scope.teacherInfo.stuTeaRelList.length;i++){
			
				if($scope.targetStudentEmail==$scope.teacherInfo.stuTeaRelList[i].studentEmail){
					flag=false;
					break;
				}
			}
			if(flag){
				$http({
					method: 'get', //방식
					url: ajaxUrl+'findStudentByEamil.do?memberEmail='+$scope.targetStudentEmail, /* 통신할 URL */
				})
				.success(function(data, status, headers, config) {
					if( data!="" ) {
						//alert(data);
						$scope.findResultDefined = true;
						$scope.resultOfFindStudent = data;
						$scope.targetStudentEmail;
					}else {
						alert('검색 대상이 없습니다.');
						$scope.findResultDefined = false;
						$scope.targetStudentEmail ="";
					}
				})
				.error(function(data, status, headers, config) {
					console.log(status);
				});
			}else{
				alert('이미 초대된 대상입니다.');
			}
		}
		
		//선생님이 검색한 학생을 초대한다.
		$scope.inviteStudent = function(resultOfFindStudent){
			if(confirm('선택한 회원을 초대하시겠습니까?')){
				//alert(resultOfFindStudent.memberEmail);
				$http({
					method: 'get', //방식
					url: ajaxUrl+'inviteStudentByEamil.do?memberEmail='+resultOfFindStudent.memberEmail, 
				})
				.success(function(data, status, headers, config) {
					$scope.teacherInfo.stuTeaRelList.push(data);
				})
				.error(function(data, status, headers, config) {
					console.log(status);
				});
			}
		}
		//선생님이 학생과의 관계를 끊는다.
		$scope.diconnectRelation = function(student){
			if(confirm('선택한 회원과의 연결을 끊으시겠습니까?')){
				//alert(student.studentEmail);
				$http({
					method: 'get', //방식
					url: ajaxUrl+'diconnectRelation.do?memberEmail='+student.studentEmail, 
				})
				.success(function(data, status, headers, config) {
					//db에서 성공적으로 지우면 객체를 모델에서 삭제해준다.
					$scope.teacherInfo.stuTeaRelList.splice($scope.teacherInfo.stuTeaRelList.indexOf(student),1);
				})
				.error(function(data, status, headers, config) {
					console.log(status);
				});
			}
		}
		
		
		
		//동영상 업로드 관련 소스
		$scope.tagInsert = function(tagListToString){
			console.log(tagListToString);
			$scope.newVideo.tagList = tagListToString.split(',');
		}
		
	/*	//수신자 선택 메서드
		<button class="btn btn-success" ng-if="selectStudentList.isSelected!=1" ng-click="selectStudent(student.studentEmail)">지정</button>
			<button class="btn btn-danger" ng-if="selectStudentList.isSelected==1" ng-click="cancelSelectStudent(student.studentEmail)">취소</button>*/
		
		//수신자로 지정된 사람들 이메일 배열 변수
		$scope.selectedStudentList = [];
		//수신자 지정시 배열에 이메일 저장
		$scope.selectStudent = function(student){
			$scope.selectedStudentList.push({'studentEmail':student.studentEmail,'isSelected':'1'});
			$scope.newVideo.selectedStudentList = $scope.selectedStudentList;
		}
		//수신 해제
		$scope.cancelSelectStudent=function(student){
			for(var i=0;i<$scope.selectedStudentList.length;i++){
				if(student.studentEmail==$scope.selectedStudentList[i].studentEmail){
					$scope.selectedStudentList.splice(i,1);
					$scope.newVideo.selectedStudentList = $scope.selectedStudentList;
				}
			}
		}
		//현재 수신자로 지정되어있는지 여부 확인
		$scope.isSelected = function(student){
			var flag = false;
			for(var i=0;i<$scope.selectedStudentList.length;i++){
				if(student.studentEmail == $scope.selectedStudentList[i].studentEmail){
					flag =true;
					break;
				}
			}
			return flag;
		}
		// 동영상 아이디가 될 랜덤 문자열 생성기
		$scope.videoIdMaker = function(){
			 var ALPHA = ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'];
			 var rN='';
			 for(var i=0; i<8; i++){
			  var randTnum = Math.floor(Math.random()*ALPHA.length);
			  rN += ALPHA[randTnum];
			 }
			 return rN;
		}
		
		// upload later on form submit or something similar
	    $scope.videoSubmit = function() {
	      if ($scope.videoUploadForm.file.$valid && $scope.file) {
	        $scope.upload($scope.file);
	      }
	    };

	    // upload on file select or drop
	    $scope.upload = function (file) {
	    
	        Upload.upload({
	            url: ajaxUrl+'test_videoUpload.do',
	            data: {file: file}
	        }).then(function (resp) {
	        	console.log("동영상아이디가 넘어오나 ?"+ resp.data.result)
	            $scope.newVideo.videoId = resp.data.result;
	        	var jsonData = JSON.stringify($scope.newVideo);
	            var progressPercentage = 80;
	        	$http({
					method: 'POST', //방식
					url: ajaxUrl+'insertVideoInfo.do', 
					data: jsonData,
					dataType:'json',
					headers: {'Content-Type': 'application/json; charset=utf-8'} //헤더 
				})
				.success(function(data, status, headers, config) {
					 var progressPercentage = 100;
					alert('서버 전송이 완료되었습니다. 10분 안에 업로드가 완료되어 동영상 관리페이지에서 확인이 가능합니다.');
					 location.href=ajaxUrl+'teacher_teacherIndex.do';
				})
	           
	        }, function (resp) {
	            console.log('Error status: ' + resp.status);
	        }, function (evt) {
	        	var progressPercentage = parseInt(51.0 * evt.loaded / evt.total);
	            console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
	            $scope.progressPercentage = progressPercentage;
	            if(progressPercentage==100){
	            	
	            }
	        });
	    };
	     
	}]);