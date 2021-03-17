package kr.co.domain;

import java.io.Serializable;

public class TotalVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int aPrice;
	private String sellDate;
	public TotalVO() {
		// TODO Auto-generated constructor stub
	}
	public TotalVO(int aPrice, String sellDate) {
		super();
		this.aPrice = aPrice;
		this.sellDate = sellDate;
	}
	public int getaPrice() {
		return aPrice;
	}
	public void setaPrice(int aPrice) {
		this.aPrice = aPrice;
	}
	public String getSellDate() {
		return sellDate;
	}
	public void setSellDate(String sellDate) {
		this.sellDate = sellDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "TotalVO [aPrice=" + aPrice + ", sellDate=" + sellDate + "]";
	}
	
	
}
