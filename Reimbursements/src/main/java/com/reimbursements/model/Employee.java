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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import io.javalin.http.Handler;
@Entity
@Table (name = "users")

@NamedQueries({

@NamedQuery(name = "findStudentByUsername", query = "FROM users u WHERE u.username = :username")
})
public class Employee {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
		private int userId;
	@Column (name = "userrole")
		private String userRole;
	@Column (name = "lastname")
		private String lastName;
	@Column (name = "firstname")
	private String firstName;
	@Column (name = "email")
	private String email;
	@Column (name = "username")
	private String username;
	@Column (name = "password")
	private String password;
	@Column (name = "authorid")
	private int authorId;
	
	private static String usernameJs;
	
	public static List<Employee> eset = new ArrayList<Employee>();

	public Employee() {

	}



	public Employee(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public Employee( int userid,String field) {
		
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


//----------------------------------------------------------------------------------------
	public Employee viewInfo(int useridInput) {
		Employee employee = new Employee();
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
		Session lSession = sessionFactory.getCurrentSession();
		Transaction ltransaction = lSession.beginTransaction();
		employee = lSession.get(Employee.class, useridInput);
	
		return employee;
		
	}
	
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
          Employee s=session.load(Employee.class, new Integer(1)); System.out.println(s);
         
        
	

		ctx.status(200);*/

	};
//-------------------------------------------------------------------------------------
	
	  public static Handler employeeH = ctx -> {
		
		  if(ctx.formParam("username") != null) {
			    usernameJs = ctx.formParam("username");
		  }
		  
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
			equery.select(root).where(ebuilder.equal(root.get("username"), usernameJs));
			
		List<Employee> allEmployee = esession.createQuery(equery).getResultList();
		  System.out.println(usernameJs);
		ctx.json(allEmployee);
         ctx.redirect("updateinfo.html");
	  
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
			} catch (Exception el) {
				el.printStackTrace();
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);

				}
			}

		}
	    
		Session elSession = sessionFactory.getCurrentSession();
		Transaction eltransaction = elSession.beginTransaction();
		CriteriaBuilder elBuilder = elSession.getCriteriaBuilder();
		CriteriaQuery<Employee> elquery = elBuilder.createQuery(Employee.class);
		Root<Employee> elroot = elquery.from(Employee.class);
		elquery.select(elroot);
		
		List<Employee> elogins = elSession.createQuery(elquery).getResultList();

			ctx.status(200);
		   ctx.redirect("updateinfo.html");
		
		eltransaction.commit();
		
	  
	  
	  };
	  
//		public static Handler viewMyInfo= ctx->{
//			int useridInput = Integer.parseInt(ctx.formParam("userid"));
//		
//			if (sessionFactory == null) {
//				try {
//					// Create registry
//					registry = new StandardServiceRegistryBuilder().configure().build();
//					// Create MetadataSources
//					MetadataSources sources = new MetadataSources(registry);
//					// Create Metadata
//					Metadata metadata = sources.getMetadataBuilder().build();
//					// Create SessionFactory
//					sessionFactory = metadata.getSessionFactoryBuilder().build();
//				} catch (Exception ve) {
//					ve.printStackTrace();
//					if (registry != null) {
//						StandardServiceRegistryBuilder.destroy(registry);
//
//					}
//				}
//
//			}
//			Session vesession = sessionFactory.getCurrentSession();
//			Transaction vtransaction = vesession.beginTransaction();
//			CriteriaBuilder vebuilder = vesession.getCriteriaBuilder();
//			CriteriaQuery<Employee> vequery = vebuilder.createQuery(Employee.class);
//			Root<Employee> vroot = vequery.from(Employee.class);
//			vequery.select(vroot).where(fistnameInput ) fieldInput)
//			List<Employee> allEmployee = vesession.createQuery(vequery).getResultList();
//			
//			ctx.json(allEmployee.contains(useridInput));
//			
//		};
//			public static Handler updateInfo= ctx->{
//			
//				int useridInput = Integer.parseInt(ctx.formParam("userid"));
//				String info = ctx.formParam("field");
//				Session session = sessionFactory.openSession();
//					Employee e = session.load(Employee.class, new Integer(useridInput));
//					Transaction transaction = session.beginTransaction();
//					e.setFirstName(info);
//					session.saveOrUpdate(e);
//					transaction.commit();
//			
//		};
}	 
