package kr.co.domain;

import java.io.Serializable;

public class CartVO implements Serializable{

	/*
		create table cart(
		cartNo number primary key,
		memberNo number not null,
		goodsNo number not null,
		amount number default '0'
		)
	 */
	private static final long serialVersionUID = 1L;
	
	private int cartNo;
	private int memberNo;
	private int sellboardNo;
	private int amount;
	
	public CartVO() {
		// TODO Auto-generated constructor stub
	}

	public CartVO(int cartNo, int memberNo, int sellboardNo, int amount) {
		super();
		this.cartNo = cartNo;
		this.memberNo = memberNo;
		this.sellboardNo = sellboardNo;
		this.amount = amount;
	}

	public CartVO(int memberNo, int sellboardNo, int amount) {
		super();
		this.memberNo = memberNo;
		this.sellboardNo = sellboardNo;
		this.amount = amount;
	}

	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getSellboardNo() {
		return sellboardNo;
	}

	public void setSellboardNo(int sellboardNo) {
		this.sellboardNo = sellboardNo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartNo;
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
		CartVO other = (CartVO) obj;
		if (cartNo != other.cartNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CartVO [cartNo=" + cartNo + ", memberNo=" + memberNo + ", sellboardNo=" + sellboardNo + ", amount=" + amount
				+ "]";
	}
	
	

}
