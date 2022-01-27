package com.reimbursements.model;

import java.lang.System.Logger;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.type.IntegerType;

import io.javalin.http.Handler;
import testing.EmployeeDAO;
import testing.ReimbursementDAO;

public class Reimbursements {
  org.apache.log4j.Logger logger = LogManager.getLogger(Reimbursements.class);
  static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	private  int selction;
	private int reimbursementId;
	private String status;
	private double amount;
	private String description;
	private String type;
	private int resolvetime;
	public static ArrayDeque<Reimbursements> queue = new ArrayDeque<>();

	public int getReimbursementId() {
		return reimbursementId;
	}

	private int submittime;
	private int authorid;
	private int resolverid;

	public Reimbursements() {

	}

	public Reimbursements(int reimbursementId, String status, double amount, String description, String type,
			int resolvetime, int submittime, int authorid, int resolverid) {
		this.reimbursementId = reimbursementId;
		this.status = status;
		this.amount = amount;
		this.description = description;
		this.type = type;
		this.resolvetime = resolvetime;
		this.submittime = submittime;
		this.authorid = authorid;
		this.resolverid = resolverid;
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

	public int getSelction() {
		return selction;
	}

	public void setSelction(int selction) {
		this.selction = selction;
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
		return "reimbursementId" + reimbursementId + "status" + status + "amount" + amount + "description" + description
				+ "type" + type + "resolvetime" + resolvetime + "submittime" + submittime + "authorid" + authorid
				+ "resolverid" + resolverid;
	}

	public static Handler employeeSubmit = ctx -> {

		int reimbursementidJs = Integer.parseInt(ctx.formParam("reimbursementid"));
		String statusJs = ctx.formParam("status");
		double amountJs = Double.parseDouble(ctx.formParam("amount"));
		String descriptionJs = ctx.formParam("description");
		String typeJs = ctx.formParam("type");
		int resolvetimeJs = Integer.parseInt(ctx.formParam("resolvetime"));
		int submittimeJs = Integer.parseInt(ctx.formParam("submittime"));
		int authoridJs = Integer.parseInt(ctx.formParam("authorid"));
		int resolveridJs = Integer.parseInt(ctx.formParam("resolverid"));
		Reimbursements re = new Reimbursements(reimbursementidJs, statusJs, amountJs, descriptionJs, typeJs,
				resolvetimeJs, submittimeJs, authoridJs, resolveridJs);
		queue.add(re);
		ctx.status(200);
		ctx.redirect("employeeHome.html");

	};
	public static Handler pendingH = ctx -> {
	
System.out.println(queue);
			ctx.json(queue);

		
	};
//    public static Handler viewMyReimbursements =ctx ->{
//		int authoridJs = Integer.parseInt(ctx.formParam("authorid"));
//		
//		
//		if (ctx.formParam("username") != null) {
//			usernameJs = ctx.formParam("username");
//		}
//		ReimbursementDAO reimbursementdao = new ReimbursementDAO();
//		List<Reimbursements> rlist = reimbursementdao.getReimbursementbyid(reimbursementidJS);
//		        ctx.json(rlist);
//				ctx.redirect("employeeHome.html");
//			
//			
//		
//		
//		
//		
//    };
	public static Handler approveH = ctx -> {

		int reimbursementidJs = Integer.parseInt(ctx.formParam("reimbursementid"));
		String statusJs = ctx.formParam("status");
		double amountJs = Double.parseDouble(ctx.formParam("amount"));
		String descriptionJs = ctx.formParam("description");
		String typeJs = ctx.formParam("type");
		int resolvetimeJs = Integer.parseInt(ctx.formParam("resolvetime"));
		int submittimeJs = Integer.parseInt(ctx.formParam("submittime"));
		int authoridJs = Integer.parseInt(ctx.formParam("authorid"));
		int resolveridJs = Integer.parseInt(ctx.formParam("resolverid"));
	
ReimbursementDAO dao = new ReimbursementDAO();
dao.addReimbursement(reimbursementidJs,statusJs,amountJs,descriptionJs,typeJs,resolvetimeJs,submittimeJs,authoridJs,resolveridJs);

      ctx.redirect("viewReimbursements.html");

	};
	public static Handler viewAll= ctx->{
		ReimbursementDAO reimbursementdao = new ReimbursementDAO();
		List<Reimbursements> rlist = reimbursementdao.getAllReimbursements();
	
			ctx.json(rlist);
			
	
	};
	public static Handler transaction= ctx ->{
		int reimbursementidJS = Integer.parseInt(ctx.formParam("reimbursementid"));
		String field = ctx.formParam("status");
		ReimbursementDAO reimbursement = new ReimbursementDAO();
		for(Reimbursements rem: queue) {
			rem.setStatus(field);
			
			int queid = rem.getReimbursementId();
			String qstatus = rem.getStatus();
			double qamount = rem.getAmount();
			String qdescriptions = rem.getDescription();
			String qtype = rem.getType();
			int qresolvetime = rem.getResolvetime();
			int qsubmittime = rem.getSubmittime();
			int qauthorid = rem.getAuthorid();
			int qresolverid = rem.getResolverid();
		    reimbursement.addReimbursement(queid,qstatus,qamount,qdescriptions, qtype, qresolvetime, qsubmittime, qauthorid, qresolverid);
			queue.pop();
			
		}
		ctx.redirect("managerHome.html");
		
		
	};
	


}