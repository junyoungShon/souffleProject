
-- 학원 기본 정보 테이블
CREATE TABLE Academy_Info(
	academy_code varchar(10) not null,
	academy_name varchar(30) not null,
	academy_address varchar(100) not null,
	academy_region varchar(20) not null,
	academy_phone  varchar(15) not null,
	primary key(academy_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 
drop table Academy_Info ;


-- 회원 기본 정보 테이블
CREATE TABLE Member_info(
	member_email varchar(20) not null,
	member_password varchar(20) not null,
	member_name varchar(20) not null,
	member_role varchar(10) not null,
	member_phone varchar(20) NOT NULL,
	primary key(member_email)
)ENGINE=InnoDB DEFAULT CHARSET=utf8; 

drop table Member_info;


--회원 로그인 기록 테이블
CREATE TABLE member_login ( 
    member_email varchar(20) NOT NULL, 
    logintime date NOT NULL default '0000-00-00 00:00:00', 
    PRIMARY KEY  (member_email,logintime), 
        FOREIGN KEY (`member_email`) REFERENCES `member_info` (`member_email`) 
            ON DELETE CASCADE 
            ON UPDATE CASCADE 
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 
drop table member_login;


--선생님 정보 테이블
CREATE TABLE teacher_info ( 
    teacher_email varchar(20) NOT NULL, 
    teacher_subject varchar(20) NOT NULL, 
    PRIMARY KEY  (teacher_email), 
    CONSTRAINT `fk_teacherid` 
        FOREIGN KEY (`teacher_email`) REFERENCES `member_info` (member_email) 
            ON DELETE CASCADE 
            ON UPDATE CASCADE 
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 
drop table teacher_info;

--학생 정보 테이블
CREATE TABLE student_info ( 
    student_email varchar(20) NOT NULL, 
    student_birth date NOT NULL  default '0000-00-00', 
    PRIMARY KEY  (student_email), 
    CONSTRAINT `fk_studentid` 
        FOREIGN KEY (`student_email`) REFERENCES `member_info` (member_email) 
            ON DELETE CASCADE 
            ON UPDATE CASCADE 
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 
drop table student_info;

--학생 , 선생님 관계 테이블
CREATE TABLE stu_tea_rel ( 
    teacher_email varchar(20) NOT NULL, 
    student_email varchar(20) NOT NULL, 
    is_connected varchar(1) not null default 'N',
    PRIMARY KEY  (teacher_email,student_email), 
  
        FOREIGN KEY (`teacher_email`) REFERENCES `member_info` (member_email) 
            ON DELETE CASCADE 
            ON UPDATE CASCADE 
 ,
        FOREIGN KEY (`student_email`) REFERENCES `member_info` (member_email) 
            ON DELETE CASCADE 
            ON UPDATE CASCADE 
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 
drop table stu_tea_rel;


--학생 , 학원 관계 테이블

CREATE TABLE stu_aca_rel ( 
    academy_code varchar(20) NOT NULL, 
    student_email varchar(20) NOT NULL, 
    is_connected varchar(1) not null default 'N',
    PRIMARY KEY  (academy_code,student_email), 
  
        FOREIGN KEY (`academy_code`) REFERENCES `academy_info` (academy_code) 
            ON DELETE CASCADE 
            ON UPDATE CASCADE 
 ,
        FOREIGN KEY (`student_email`) REFERENCES `member_info` (member_email) 
            ON DELETE CASCADE 
            ON UPDATE CASCADE 
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 
drop table stu_aca_rel;


--학원 , 선생님 관계 테이블
CREATE TABLE tea_aca_rel ( 
    academy_code varchar(20) NOT NULL, 
    member_email varchar(20) NOT NULL, 
    is_connected varchar(1) not null default 'N',
    PRIMARY KEY  (academy_code,member_email), 
  
        FOREIGN KEY (`academy_code`) REFERENCES `academy_info` (academy_code) 
            ON DELETE CASCADE 
            ON UPDATE CASCADE 
 ,
        FOREIGN KEY (`member_email`) REFERENCES `member_info` (member_email) 
            ON DELETE CASCADE 
            ON UPDATE CASCADE 
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 
drop table tea_aca_rel;

ALTER TABLE lecture_info ADD COLUMN lecture_type varchar(10)
CREATE TABLE lecture_info(
	lecture_id int(11) not null auto_increment,
	lecture_type varchar(10) not null,
	teacher_email varchar(20) NOT NULL, 
	view_count int not null default 0,
    upload_date date not null,
    lecture_title varchar(100) not null,
    lecture_inst  text not null,
    PRIMARY KEY  (lecture_id), 
    FOREIGN KEY (`teacher_email`) REFERENCES `member_info` (member_email) 
    	ON DELETE CASCADE 
        ON UPDATE CASCADE 
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 
)
drop table lecture_info;

ALTER TABLE lecture_receiver_info ADD COLUMN due_date int(11)

ALTER TABLE lecture_receiver_info ADD COLUMN lecture_receipient_id date

CREATE TABLE lecture_receiver_info ( 
    lecture_id int(11) NOT NULL, 
    lecture_receipt_id int(11) not null,
    student_email varchar(20) NOT NULL, 
    read_status int not null default 0,
    last_view date not null default '0000-00-00',
    isPicked int not null default 0,
    studyTime int(7) not null default 0,
    due_date date not null default '0000-00-00',
    
    PRIMARY KEY  (lecture_id,student_email, lecture_receipt_id), 
  	FOREIGN KEY (`lecture_receipt_id`) REFERENCES `lecture_receipt_info` (lecture_receipt_id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE,
    FOREIGN KEY (`lecture_id`) REFERENCES `lecture_info` (lecture_id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE,
    FOREIGN KEY (`student_email`) REFERENCES `member_info` (member_email) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 
drop table lecture_receiver_info;

CREATE TABLE lecture_receipt_info (
	lecture_receipt_id int(11) not null auto_increment,
	lecture_id int(11) not null,
    PRIMARY KEY  (lecture_receipt_id), 
	FOREIGN KEY (`lecture_id`) REFERENCES `lecture_info` (lecture_id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
)

CREATE TABLE video_info ( 
    video_id varchar(20) NOT NULL,
    lecture_id int(11) not null,
    PRIMARY KEY  (video_id), 
    FOREIGN KEY (`lecture_id`) REFERENCES `lecture_info` (lecture_id) 
    	ON DELETE CASCADE 
        ON UPDATE CASCADE 
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 
drop table video_info;

CREATE TABLE audio_info ( 
    video_id varchar(20) NOT NULL,
    lecture_id int(11) not null,
    PRIMARY KEY  (video_id), 
    FOREIGN KEY (`lecture_id`) REFERENCES `lecture_info` (lecture_id) 
    	ON DELETE CASCADE 
        ON UPDATE CASCADE 
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 
drop table audio_info;


	 <insert id="insertVideoInfo" parameterType="HashMap">
	 	insert into video_info (video_id,teacher_email,view_count,upload_date,video_inst) values (#{viewId},#{teacherEmail},0,DATE_FORMAT(NOW(),'%Y-%m-%d')))
	 </insert>
	 <insert id="insertVideoReceiverInfo" parameterType="HashMap">
	 	insert into video_receiver_info (video_id,student_email,read_status,last_view) values (#{viewId),#{studentEmail},0)
	 </insert>



insert into 'member_info' ('member_email','member_password','member_name','member_role')  values ('teacher@test.com','asdfasdf','서정우','teacher')
insert into 'member_info' (member_email,member_password,member_name,member_role) values 'teacher@test.com','asdfasdf','서정우','teacher'
insert into member_info (member_email, member_password, member_name, member_role) values('teacher@test.com', 'asdfasdf', '서정우', 'teacher')

select member_info.member_email,member_info.member_name,member_info.member_role,teacher_info.teacher_phone,teacher_info.teacher_subject
		from member_info Inner join teacher_info
		On member_info.member_email = teacher_info.member_email and member_info.member_email = 'teacher@test.com'
		
		select member_info.member_email,member_info.member_name,member_info.member_role,student_info.student_birth
		from member_info Inner join student_info
		On member_info.member_email = student_info.member_email and member_info.member_email = 'student@test.com'
		
		select member_info.member_email,member_info.member_name,member_info.member_role,student_info.student_birth
		from member_info Inner join student_info
		On member_info.member_email = student_info.member_email and member_info.member_email = 'student@test.com'
		
		select member_info.member_email,member_info.member_name,member_info.member_role,teacher_info.teacher_phone,teacher_info.teacher_subject,stu_tea_rel.student_email,stu_tea_rel.is_connected,member_name,
		from 
		On
		
		
	
		select 
		from (
			select member_info.member_email
			,member_info.member_name as 
			,member_info.member_role
			,teacher_info.
			teacher_phone,
			teacher_info.teacher_subject
			,stu_tea_rel.student_email
			,stu_tea_rel.is_connected
			from member_info Inner join teacher_info Inner join stu_tea_rel
			On member_info.member_email = teacher_info.member_email and  stu_tea_rel.teacher_email = member_info.member_email and member_info.member_email = 'teacher@test.com' 
		) inner join student_info inner join member_info
		where stu_tea_rel.student_email
		
		
		select member_info.member_email,member_info.member_name,member_info.member_role,teacher_info.teacher_phone,teacher_info.teacher_subject,stu_tea_rel.teacher_email,stu_tea_rel.student_email,stu_tea_rel.is_connected
		from member_info Inner join teacher_info Inner join stu_tea_rel
		On member_info.member_email = teacher_info.member_email and  stu_tea_rel.teacher_email = member_info.member_email and member_info.member_email ='teacher3@test.com' 
		
		insert into video_info (video_id,teacher_email,view_count,upload_date,video_inst) values ('wUF_Tlxyvtk','teacher@test.com',0,DATE_FORMAT(NOW(),'%Y-%m-%d'),'sdafdsf')
		