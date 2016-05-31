
select 
	count(read_status)
from
	lecture_receiver_info
where
	read_status = 2 
	and lecture_receipt_id = 1 
	
select 
	count(read_status)
from
	lecture_receiver_info
where
	lecture_receipt_id = 1 


select DISTINCT 
	lecture_receiver_info.lecture_receipt_id,
	lecture_receiver_info.lecture_id,
	lecture_receiver_info.due_date,
	lecture_info.lecture_type,
	lecture_info.lecture_title,
	lecture_info.teacher_email
from  
	lecture_receiver_info
	inner join lecture_info 
where 
	lecture_receiver_info.student_email = 'student@test.com' 
	and lecture_receiver_info.read_status = 0 
	and lecture_receiver_info.lecture_id = lecture_info.lecture_id
	and date(lecture_receiver_info.due_date) >= date(now())

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

오늘 날짜를 기준으로 듀데이트가 오늘 이후인 readStatus select



select
(select count(*)
from lecture_receiver_info
where date(due_date) >= date(now()) and read_status = 2 and student_email='student@test.com')
/(select count(*)
from lecture_receiver_info
where date(due_date) >= date(now()) and student_email='student@test.com')

select 


7일 이내 구매 한 
 SELECT * FROM purchaseT
> WHERE date(buydate) >= date(subdate(now(), INTERVAL 7 DAY)) and date(buydate) <= date(now())
> ORDER BY idx ASC;

select count(*)
from lecture_receiver_info
where date(due_date) >= date(now()) and read_status = 1 and student_email='student@test.com'


select
		(
			select 
				count(*)
			from 
				lecture_receiver_info
			where 
				date(due_date) >= date(now()) and read_status = 2 and student_email='student@test.com'
		)
		/
		(
			select
				count(*)
			from 
				lecture_receiver_info
			where 
				date(due_date) >= date(now()) and student_email='student@test.com'
		)

			select DISTINCT 
			lecture_receiver_info.lecture_receipt_id as lectureReceiptId,
			lecture_receiver_info.lecture_id as lectureId,
			lecture_receiver_info.due_date as dueDate,
			lecture_info.lecture_type as lectureType,
			lecture_info.lecture_title as lectureTitle,
			lecture_info.teacher_email as teacherEmail,
			member_info.member_name as teacherName
		from  
			lecture_receiver_info
			inner join lecture_info 
			inner join member_info
		where 
			lecture_receiver_info.student_email = 'student@test.com'
			and lecture_receiver_info.read_status = 0 
			and lecture_receiver_info.lecture_id = lecture_info.lecture_id
			and date(lecture_receiver_info.due_date) >= date(now())
			and lecture_info.teacher_email =  member_info.member_email
			
			
			select DISTINCT 
				lecture_receiver_info.lecture_receipt_id as lectureReceiptId,
				lecture_receiver_info.lecture_id as lectureId,
				lecture_receiver_info.due_date as dueDate,
				lecture_info.lecture_type as lectureType,
				lecture_info.lecture_title as lectureTitle,
				lecture_info.teacher_email as teacherEmail,
				member_info.member_name as teacherName
			from  
				lecture_receiver_info
				inner join lecture_info 
				inner join member_info
			where 
				lecture_receiver_info.student_email = 'student@test.com'
				and lecture_receiver_info.read_status = 0 
				and lecture_receiver_info.lecture_id = lecture_info.lecture_id
				and date_add(now(),interval 3 day) >= date(lecture_receiver_info.due_date) 
				and lecture_info.teacher_email =  member_info.member_email
		
				
				select 
				member_info.member_name,stu_tea_rel.is_connected
			from 
				stu_tea_rel 
				inner join member_info
			where
				stu_tea_rel.student_email = 'student@test.com'
				and stu_tea_rel.teacher_email = member_info.member_email
		
				
				
				select DISTINCT 
				lecture_receiver_info.lecture_receipt_id as lectureReceiptId,
				lecture_receiver_info.lecture_id as lectureId,
				lecture_receiver_info.due_date as dueDate,
				lecture_info.lecture_type as lectureType,
				lecture_info.lecture_title as lectureTitle,
				lecture_info.teacher_email as teacherEmail,
				member_info.member_name as teacherName
			from  
				lecture_receiver_info
				inner join lecture_info 
				inner join member_info
			where 
				
			order by lecture_info.upload_date asc