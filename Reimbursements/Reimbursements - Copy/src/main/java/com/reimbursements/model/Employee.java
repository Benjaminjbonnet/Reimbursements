package com.reimbursements.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

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

public class Employee {
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	private int userId;
	private String userRole;
	private String lastName;
	private String firstName;
	private String email;
	private String username;
	private String password;
	private int authorId;
	public static List<Employee> eset = new ArrayList<Employee>();

	public Employee() {

	}

	// to constructor to retrieve username and password
	// -------------------------------
	public Employee(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Employee(int userId, String userRole, String lastName, String firstName, String email, String username,
			String password, int authorId) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.userRole = userRole;
		this.authorId = authorId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	@Override
	public String toString() {
		return "userId" + userId + " userRole" + userRole + " lastName" + lastName + ", firstName" + firstName
				+ " email" + email + " username" + username + ", password" + password + ", authorId" + authorId;
	}

	/*
	 * public static Connection getConnection() throws IOException, SQLException {
	 * Connection conn; FileInputStream connectionprop = new
	 * FileInputStream("C:\\Users\\benja\\OneDrive\\Desktop\\project 1\\project 1\\Reimbursements\\Connection.properties"
	 * ); Properties prop = new Properties(); prop.load(connectionprop);
	 * 
	 * String url = (String) prop.get("url"); String username = (String)
	 * prop.get("username"); String password = (String) prop.get("password"); return
	 * DriverManager.getConnection(url, username, password); }
	 */
//----------------------------------------------------------------------------------------
	public static Handler loginH = ctx -> {
		String usernameJs = ctx.formParam("username");
		String passwordJs = ctx.formParam("password");

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
	    
		Session lSession = sessionFactory.getCurrentSession();
		Transaction ltransaction = lSession.beginTransaction();
		CriteriaBuilder lBuilder = lSession.getCriteriaBuilder();
		CriteriaQuery<Employee> lquery = lBuilder.createQuery(Employee.class);
		Root<Employee> lroot = lquery.from(Employee.class);
		lquery.select(lroot);
		
		List<Employee> logins = lSession.createQuery(lquery).getResultList();

			ctx.status(200);
		   ctx.redirect("managerHome.html");
		
		ltransaction.commit();
		
		

	
    
       
		  /*Session session = sessionFactory.openSession();
          Employee s=session.load(Employee.class, new Integer(1)); System.out.println(s);*/
         
        
		/*Session session = sessionFactory.openSession();
		Employee e = session.load(Employee.class, new Integer(3));
		Transaction transaction = session.beginTransaction();
		e.setFirstName("Ben");
		session.saveOrUpdate(e);
		transaction.commit();

		ctx.status(200);*/

	};
//-------------------------------------------------------------------------------------
	
	  public static Handler employeeH = ctx -> {
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
		    
			Session esession = sessionFactory.getCurrentSession();
			Transaction transaction = esession.beginTransaction();
			CriteriaBuilder ebuilder = esession.getCriteriaBuilder();
			
			CriteriaQuery<Employee> equery = ebuilder.createQuery(Employee.class);
			Root<Employee> root = equery.from(Employee.class);
			equery.select(root);
			
		List<Employee> allEmployee = esession.createQuery(equery).getResultList();
	
		ctx.json(allEmployee);
		
	  
	  transaction.commit();
	  };
	public static Handler eloginH= ctx ->{
		
	
		String usernameJs = ctx.formParam("username");
		String passwordJs = ctx.formParam("password");

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
	    
		Session elSession = sessionFactory.getCurrentSession();
		Transaction eltransaction = elSession.beginTransaction();
		CriteriaBuilder elBuilder = lSession.getCriteriaBuilder();
		CriteriaQuery<Employee> elquery = elBuilder.createQuery(Employee.class);
		Root<Employee> elroot = elquery.from(Employee.class);
		lquery.select(elroot);
		
		List<Employee> elogins = elSession.createQuery(elquery).getResultList();

			ctx.status(200);
		   ctx.redirect("employeeHome.html");
		
		ltransaction.commit();
		
	  
	  
	  };
	 
