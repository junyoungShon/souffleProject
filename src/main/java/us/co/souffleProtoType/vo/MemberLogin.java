package us.co.souffleProtoType.vo;

public class MemberLogin extends MemberInfo{
	private String loginTime;

	public MemberLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	@Override
	public String toString() {
		return "MemberLogin [loginTime=" + loginTime + "]";
	}
	
}
