package kr.co.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReplyVO {
   private int rno;
   private int bno;
   private String replyText;
   private String replyer;
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
   private Date regDate;
   
   private Date updateDate;
   
   public ReplyVO() {
      // TODO Auto-generated constructor stub
   }

   public ReplyVO(int bno, String replyText, String replyer) {
      super();
      this.bno = bno;
      this.replyText = replyText;
      this.replyer = replyer;
   }
   
   

   public ReplyVO(int rno, int bno, String replyText, String replyer, Date regDate, Date updateDate) {
	super();
	this.rno = rno;
	this.bno = bno;
	this.replyText = replyText;
	this.replyer = replyer;
	this.regDate = regDate;
	this.updateDate = updateDate;
   }

   public int getRno() {
      return rno;
   }

   public void setRno(int rno) {
      this.rno = rno;
   }

   public int getBno() {
      return bno;
   }

   public void setBno(int bno) {
      this.bno = bno;
   }

   public String getReplyText() {
      return replyText;
   }

   public void setReplyText(String replyText) {
      this.replyText = replyText;
   }

   public String getReplyer() {
      return replyer;
   }

   public void setReplyer(String replyer) {
      this.replyer = replyer;
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

@Override
public String toString() {
	return "ReplyVO [rno=" + rno + ", bno=" + bno + ", replyText=" + replyText + ", replyer=" + replyer
			+ ", updateDate=" + updateDate + "]";
}
   
   

}