package com.reimbursements.model;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import io.javalin.http.Handler;

public class Reimbursements {
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;

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
		if (sessionFactory == null) {
			try {
				// Create registry
				registry = new StandardServiceRegistryBuilder().configure().build();
				// Create MetadataSources
				MetadataSources sources = new MetadataSources(registry);
				// Create Metadata
				Metadata metadata = sources.getMetadataBuilder().build();
				// Create SessionFactory
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} catch (Exception e) {
				e.printStackTrace();
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);

				}
			}

		}

		Session rsession = sessionFactory.getCurrentSession();
		Transaction transaction = rsession.beginTransaction();
		CriteriaBuilder rbuilder = rsession.getCriteriaBuilder();

		CriteriaQuery<Reimbursements> rquery = rbuilder.createQuery(Reimbursements.class);
		Root<Reimbursements> root = rquery.from(Reimbursements.class);
		rquery.select(root);

		List<Reimbursements> reimbursements = rsession.createQuery(rquery).getResultList();

		ctx.json(reimbursements);
		ctx.json(queue);

		transaction.commit();
	};

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
		Reimbursements re = new Reimbursements(reimbursementidJs, statusJs, amountJs, descriptionJs, typeJs,
				resolvetimeJs, submittimeJs, authoridJs, resolveridJs);

		if (sessionFactory == null) {
			try {
				// Create registry
				registry = new StandardServiceRegistryBuilder().configure().build();
				// Create MetadataSources
				MetadataSources sources = new MetadataSources(registry);
				// Create Metadata
				Metadata metadata = sources.getMetadataBuilder().build();
				// Create SessionFactory
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} catch (Exception ex) {
				ex.printStackTrace();
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);

				}
			}

		}
		ctx.status(200);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(re);
		ctx.redirect("managerHome.html");
		transaction.commit();
	};

}
