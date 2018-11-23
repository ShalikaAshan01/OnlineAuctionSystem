/**
 * 
 */
package model;

/**
 * @author Shalika Ashan
 *
 */
public class PersonModel {

	private int cid;
	private String fname;
	private String lname;
	private String email;
	private String addrl1;
	private String addrl2;
	private String addrl3;
	private String phone;
	private String dob;
	private String gender;
	private String password;
	

	public PersonModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the cid
	 */
	public int getCid() {
		return cid;
	}

	/**
	 * @param cid the cid to set
	 */
	public void setCid(int cid) {
		this.cid = cid;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the addrl1
	 */
	public String getAddrl1() {
		return addrl1;
	}

	/**
	 * @param addrl1 the addrl1 to set
	 */
	public void setAddrl1(String addrl1) {
		this.addrl1 = addrl1;
	}

	/**
	 * @return the addrl2
	 */
	public String getAddrl2() {
		return addrl2;
	}

	/**
	 * @param addrl2 the addrl2 to set
	 */
	public void setAddrl2(String addrl2) {
		this.addrl2 = addrl2;
	}

	/**
	 * @return the addrl3
	 */
	public String getAddrl3() {
		return addrl3;
	}

	/**
	 * @param addrl3 the addrl3 to set
	 */
	public void setAddrl3(String addrl3) {
		this.addrl3 = addrl3;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "PersonModel [cid=" + cid + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", addrl1="
				+ addrl1 + ", addrl2=" + addrl2 + ", addrl3=" + addrl3 + ", phone=" + phone + ", dob=" + dob
				+ ", gender=" + gender + ", password=" + password + "]";
	}

}
