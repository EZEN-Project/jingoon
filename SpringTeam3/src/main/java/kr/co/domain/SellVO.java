package kr.co.domain;

import java.io.Serializable;
import java.util.Date;



public class SellVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	
	private int sellNum;
	private int buyNum;
	private int sellboardNo;
	private int amount;
	private int aPrice;
	private int groupNum;
	private Date sellDate;
	private String img;
	public SellVO() {
		// TODO Auto-generated constructor stub
	}
	public SellVO(int sellNum, int buyNum, int sellboardNo, int amount, int aPrice, int groupNum, Date sellDate,
			String img) {
		super();
		this.sellNum = sellNum;
		this.buyNum = buyNum;
		this.sellboardNo = sellboardNo;
		this.amount = amount;
		this.aPrice = aPrice;
		this.groupNum = groupNum;
		this.sellDate = sellDate;
		this.img = img;
	}
	
	

	public SellVO(int buyNum, int sellboardNo, int amount, int aPrice, String img) {
		super();
		this.buyNum = buyNum;
		this.sellboardNo = sellboardNo;
		this.amount = amount;
		this.aPrice = aPrice;
		this.img = img;
	}

	public SellVO(int buyNum, int sellboardNo, int amount, int aPrice, Date sellDate, String img) {
		super();
		this.buyNum = buyNum;
		this.sellboardNo = sellboardNo;
		this.amount = amount;
		this.aPrice = aPrice;
		this.sellDate = sellDate;
		this.img = img;
	}
	public int getSellNum() {
		return sellNum;
	}
	public void setSellNum(int sellNum) {
		this.sellNum = sellNum;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
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
	public int getaPrice() {
		return aPrice;
	}
	public void setaPrice(int aPrice) {
		this.aPrice = aPrice;
	}
	public int getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}
	public Date getSellDate() {
		return sellDate;
	}
	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SellVO [sellNum=" + sellNum + ", buyNum=" + buyNum + ", sellboardNo=" + sellboardNo + ", amount="
				+ amount + ", aPrice=" + aPrice + ", groupNum=" + groupNum + ", sellDate=" + sellDate + ", img=" + img
				+ "]";
	}
	
		
	

	
	

}
