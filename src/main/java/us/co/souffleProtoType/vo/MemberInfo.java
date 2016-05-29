package us.co.souffleProtoType.vo;

public class MemberInfo {
	private String memberEmail;
	private String memberPassword;
	private String memberName;
	private String memberRole;
	
	public MemberInfo() {
		super();
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberRole() {
		return memberRole;
	}
	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}
	@Override
	public String toString() {
		return "MemberInfo [memberEmail=" + memberEmail + ", memberPassword="
				+ memberPassword + ", memberName=" + memberName
				+ ", memberRole=" + memberRole + "]";
	}
	
}
