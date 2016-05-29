package us.co.souffleProtoType.vo;
public class StuTeaRel {
	private String teacherEmail;
	private String studentEmail;
	private TeacherInfo teacherInfo;
	private StudentInfo studentInfo;
	private char isConnected;
	public StuTeaRel() {
		super();
	}
	public String getTeacherEmail() {
		return teacherEmail;
	}
	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public TeacherInfo getTeacherInfo() {
		return teacherInfo;
	}
	public void setTeacherInfo(TeacherInfo teacherInfo) {
		this.teacherInfo = teacherInfo;
	}
	public StudentInfo getStudentInfo() {
		return studentInfo;
	}
	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}
	public char getIsConnected() {
		return isConnected;
	}
	public void setIsConnected(char isConnected) {
		this.isConnected = isConnected;
	}
	@Override
	public String toString() {
		return "StuTeaRel [getTeacherEmail()=" + getTeacherEmail()
				+ ", getStudentEmail()=" + getStudentEmail()
				+ ", getTeacherInfo()=" + getTeacherInfo()
				+ ", getStudentInfo()=" + getStudentInfo()
				+ ", getIsConnected()=" + getIsConnected() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}
