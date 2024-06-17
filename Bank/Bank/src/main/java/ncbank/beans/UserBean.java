package ncbank.beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserBean {
	/*
	 * CREATE TABLE member ( user_num varchar2(20) NOT NULL, name varchar2(10) NOT
	 * NULL, address varchar2(50) NULL, phone varchar2(20) NOT NULL, resident
	 * varchar2(14) NOT NULL, email varchar2(50) NULL, join_date varchar2(10) NOT
	 * NULL );
	 */

	/*
	 * CREATE TABLE login ( user_num varchar2(20) NOT NULL, id varchar2(20) NOT
	 * NULL, pwd varchar2(100) NOT NULL );
	 */

	private int user_num;

	@Size(min = 2, max = 4)
	@Pattern(regexp = "[가-힣]*") // ㄱ에서 부터 끝까지 읽음
	private String name;
	private String address;
	@Pattern(regexp = "^0\\d{1,2}(-|\\))\\d{3,4}-\\d{4}$") // 전화번호 형식
	private String phone;
	@Pattern(regexp = "\\d{6}\\-[1-4]\\d{6}") // 주민등록번호 형식
	private String resident; // 주민번호
	private String email; // 이메일
	/* /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i */
	private String join_date;
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*") // 영어 또는 숫자만 입력 자주 쓰이는 정규식(Regular Expression)
	private String id;
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	/* /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$ 영문 숫자 특수기호 조합 8자리 이상 */
	private String pwd;
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String pwd2;

	private boolean idExistCheck = true;

	private boolean userLogin;

	// 초기값 로그인이 안되어 있는 상태
	public UserBean() {
		this.userLogin = false;
	}

	/*
	 * public void hashAndSetPwd(String rawPassword) { BCryptPasswordEncoder encoder
	 * = new BCryptPasswordEncoder(); this.pwd = encoder.encode(rawPassword); }
	 */

	public boolean isUserLogin() {
		return userLogin;
	}

	public void setUserLogin(boolean userLogin) {
		this.userLogin = userLogin;
	}

	public int getUser_num() {
		return user_num;
	}

	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getResident() {
		return resident;
	}

	public void setResident(String resident) {
		this.resident = resident;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJoin_date() {
		return join_date;
	}

	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwd2() {
		return pwd2;
	}

	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}

	public boolean isIdExistCheck() {
		return idExistCheck;
	}

	public void setIdExistCheck(boolean idExistCheck) {
		this.idExistCheck = idExistCheck;
	}

}
