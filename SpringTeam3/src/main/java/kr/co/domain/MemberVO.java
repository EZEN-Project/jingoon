package kr.co.domain;

import java.io.Serializable;
import java.util.Date;

public class MemberVO implements Serializable{

	/**
	 * create table member(
mnum number(6) primary key,
id varchar2(10) not null,
email varchar2(20) not null,
pw varchar2(15) not null,
name varchar2(12) not null,
phone number(15) not null,
address varchar2(300) not null,
regdate DATE DEFAULT SYSDATE,
updatedate DATE DEFAULT SYSDATE,
birth varchar2(14) not null,
point number(10) DEFAULT 0,
mtype number(10) DEFAULT 1,
memo varchar2(100)
)
	 */
	private static final long serialVersionUID = 1L;
	
	private int mnum;
	private String id;
	private String email;
	private String pw;
	private String name;
	private String phone;
	private String address;
	private Date regDate;
	private Date updateDate;
	private String birth;
	private int point;
	private int mType;
	private String memo;
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}

	public MemberVO(int mnum, String id, String email, String pw, String name, String phone, String address, Date regDate,
			Date updateDate, String birth, int point, int mType, String memo) {
		super();
		this.mnum = mnum;
		this.id = id;
		this.email = email;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.birth = birth;
		this.point = point;
		this.mType = mType;
		this.memo = memo;
	}

	public MemberVO(String id, String email, String pw, String name, String phone, String address, String birth) {
		super();
		this.id = id;
		this.email = email;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.birth = birth;
	}

	public int getMnum() {
		return mnum;
	}

	public void setMnum(int mnum) {
		this.mnum = mnum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getmType() {
		return mType;
	}

	public void setmType(int mType) {
		this.mType = mType;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MemberVO [mnum=" + mnum + ", id=" + id + ", email=" + email + ", pw=" + pw + ", name=" + name
				+ ", phone=" + phone + ", address=" + address + ", regDate=" + regDate + ", updateDate=" + updateDate
				+ ", birth=" + birth + ", point=" + point + ", mType=" + mType + ", memo=" + memo + "]";
	}

	
	
	

}
