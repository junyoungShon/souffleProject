package us.co.souffleProtoType.vo;

public class AcademyInfo {
	private String academyCode;
	private String academyName;
	private String academyAddress;
	private String academyRegion;
	private String academyPhone;
	public String getAcademyCode() {
		return academyCode;
	}
	public void setAcademyCode(String academyCode) {
		this.academyCode = academyCode;
	}
	public String getAcademyName() {
		return academyName;
	}
	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}
	public String getAcademyAddress() {
		return academyAddress;
	}
	public void setAcademyAddress(String academyAddress) {
		this.academyAddress = academyAddress;
	}
	public String getAcademyRegion() {
		return academyRegion;
	}
	public void setAcademyRegion(String academyRegion) {
		this.academyRegion = academyRegion;
	}
	public String getAcademyPhone() {
		return academyPhone;
	}
	public void setAcademyPhone(String academyPhone) {
		this.academyPhone = academyPhone;
	}
	public AcademyInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AcademyInfo [academyCode=" + academyCode + ", academyName="
				+ academyName + ", academyAddress=" + academyAddress
				+ ", academyRegion=" + academyRegion + ", academyPhone="
				+ academyPhone + "]";
	}
	
}
