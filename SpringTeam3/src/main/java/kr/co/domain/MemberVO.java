
package kr.co.domain;

import java.io.Serializable;
import java.util.Date;

public class MemberVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int mnum;
	private String id;
	private String email;
	private String pw;
	private String name;
	private String phone;
	private String address;
	private Date regdate;
	private Date updatedate;
	private Date birth;
	private int	point;
	private int	mtype;
	private String memo;
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberVO(int mnum, String id, String email, String pw, String name, String phone, String address, Date regdate,
			Date updatedate, Date birth, int point, int mtype, String memo) {
		super();
		this.mnum = mnum;
		this.id = id;
		this.email = email;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.regdate = regdate;
		this.updatedate = updatedate;
		this.birth = birth;
		this.point = point;
		this.mtype = mtype;
		this.memo = memo;
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
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getMtype() {
		return mtype;
	}
	public void setMtype(int mtype) {
		this.mtype = mtype;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", email=" + email + ", name=" + name + "]";
	}
	
	

	
}
