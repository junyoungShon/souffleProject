angular.module("souffleApp")
	
	.controller("indexCtrl",function($scope,$http,ajaxUrl){
		
		//버튼의 상대개체 배열
		$scope.btnStatus = [
		                    {btnName : "login" , active : true},
		                    {btnName : "teacherJoin" , active : false},
		                    {btnName : "studentJoin" , active : false}
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
		
		//유효성 검증시 필요한 정규식 패턴 
		$scope.regex = {
				emailRegex : '^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$',
				phoneRegex : "/^\d{3}-\d{3,4}-\d{4}$/;",
				blankRegex : "/\s/g;"
		}
		// 선생님 소속 학원 및 확인  변수
		$scope.checkAcademyCode = {
				checkAcademyFunc : function(teacherAcademy){
									$http({
										method: 'get', //방식
										url: ajaxUrl+'checkAcademy.do?academyCode='+teacherAcademy, /* 통신할 URL */
										/*data: teacherAcademy,  파라메터로 보낼 데이터 */
										//headers: "Accept=*/*"//헤더
									})
									.success(function(data, status, headers, config) {
										if( data ) {
											/* 성공적으로 결과 데이터가 넘어 왔을 때 처리 */
											alert(data.academyName);
											if(data.isValid=='t'){
												$scope.checkAcademyCode.isChecked = true;
												$scope.checkAcademyCode.confirmedAcademyName = data.academyName;
											}
										}
										else {
											/* 통신한 URL에서 데이터가 넘어오지 않았을 때 처리 */
										}
									})
									.error(function(data, status, headers, config) {
										/* 서버와의 연결이 정상적이지 않을 때 처리 */
										console.log(status);
									});
								},
				modifyAcademyFunc : function(){
									$scope.checkAcademyCode.isChecked = false;
									$scope.checkAcademyCode.confirmedAcademyName = '';
									$scope.newUser.teacherAcademy = '';
								},
				isChecked : false,
				confirmedAcademyName : ''
		}
		
		//회원가입 로직
		$scope.addUser = function(type,newUser){
			console.log(type);
			var jsonData = JSON.stringify(newUser);
			if(type=='teacher'){
				$http({
					method: 'POST', //방식
					url: ajaxUrl+'teacherJoin.do', 
					/*data: $('#teacherJoinForm').serialize(),*/
					data: jsonData,
					dataType:'json',
					//headers: {'Content-Type': 'application/json; charset=utf-8'} //헤더 
				})
				.success(function(data, status, headers, config) {
					if( data ) {
						alert(data.academyName);
					}
					else {
						alert('fail');
					}
				})
				.error(function(data, status, headers, config) {
					
				});
			}else if(type=='student'){
				$http({
					method: 'POST', //방식
					url: ajaxUrl+'studentJoin.do', 
					/*data: $('#teacherJoinForm').serialize(),*/
					data: jsonData,
					dataType:'json',
					headers: {'Content-Type': 'application/json; charset=utf-8'} //헤더 
				})
				.success(function(data, status, headers, config) {
					if( data ) {
						alert(data.academyName);
					}
					else {
						alert('fail');
					}
				})
				.error(function(data, status, headers, config) {
					
				});
			}
		}
		
		
		$scope.memberLogin = function(loginMember,$location){
			var jsonData = JSON.stringify(loginMember);
			$http({
				method: 'POST', //방식
				url: ajaxUrl+'memberLogin.do', 
				data: jsonData,
			})
			.success(function(data) {
				console.log(data.result);
					if( data.result=='teacher' ) {
						location.href=ajaxUrl+'teacher_teacherIndex.do'
					}
					else if( data.result=='student' ) {
						location.href=ajaxUrl+'student_studentIndex.do'
					}else if(data.result=='loginFail'){
						alert('아이디 혹은 비밀번호를 확인해주세요!');
					}
			})
		}
    })