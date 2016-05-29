package us.co.souffleProtoType.vo;

import java.util.List;

public class TeacherInfo extends MemberInfo{
	private String teacherSubject;
	private String teacherPhone;
	private List<StuTeaRel> stuTeaRelList;
	public TeacherInfo() {
		super();
	}
	public String getTeacherSubject() {
		return teacherSubject;
	}
	public void setTeacherSubject(String teacherSubject) {
		this.teacherSubject = teacherSubject;
	}
	public String getTeacherPhone() {
		return teacherPhone;
	}
	public void setTeacherPhone(String teacherPhone) {
		this.teacherPhone = teacherPhone;
	}
	public List<StuTeaRel> getStuTeaRelList() {
		return stuTeaRelList;
	}
	public void setStuTeaRelList(List<StuTeaRel> stuTeaRelList) {
		this.stuTeaRelList = stuTeaRelList;
	}
	@Override
	public String toString() {
		return "TeacherInfo [teacherSubject=" + teacherSubject
				+ ", teacherPhone=" + teacherPhone + ", stuTeaRelList="
				+ stuTeaRelList + "]";
	}
}
