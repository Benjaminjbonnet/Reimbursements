package com.reumbursements.model;

public class Reimbursements {

	private int reimbursementId;
	private String status;
	private double amount;
	private String description;
	private String type;
	private int resolvetime;
	private int submittime;
	private int authorid;
	private int resolverid;
	
	public Reimbursements() {
		
	}
	public Reimbursements(int reimbursementId,String status,double amount, String description, String type, int resolvetime, int submittime, int authorid,int resolverid) {
		
	}
	
	public int getReimbursementId() {
		return reimbursementId;
	}


	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getResolvetime() {
		return resolvetime;
	}


	public void setResolvetime(int resolvetime) {
		this.resolvetime = resolvetime;
	}


	public int getSubmittime() {
		return submittime;
	}


	public void setSubmittime(int submittime) {
		this.submittime = submittime;
	}


	public int getAuthorid() {
		return authorid;
	}


	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}


	public int getResolverid() {
		return resolverid;
	}


	public void setResolverid(int resolverid) {
		this.resolverid = resolverid;
	}


	@Override
	public String toString() {
		return "reimbursementId" + reimbursementId + "accepted"  + "status" + status + "amount" + amount +
				 "description" + description + "type" + type + "resolvetime" + resolvetime + "submittime"
				+ submittime + "authorid" + authorid + "resolverid" + resolverid ;
	}
	
	
	
}
