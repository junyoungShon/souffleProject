package us.co.souffleProtoType.vo;

public class StudentInfo extends MemberInfo{
	private String studentBirth;
	public String getStudentBirth() {
		return studentBirth;
	}
	public void setStudentBirth(String studentBirth) {
		this.studentBirth = studentBirth;
	}
	public StudentInfo() {
		super();
	}
	@Override
	public String toString() {
		return "StudentInfo [studentBirth=" + studentBirth
				+ ", getMemberEmail()=" + getMemberEmail()
				+ ", getMemberPassword()=" + getMemberPassword()
				+ ", getMemberName()=" + getMemberName() + ", getMemberRole()="
				+ getMemberRole() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

}
