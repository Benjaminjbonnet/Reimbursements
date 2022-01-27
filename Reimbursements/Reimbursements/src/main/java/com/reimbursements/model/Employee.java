package com.reimbursements.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import io.javalin.http.Handler;
import testing.EmployeeDAO;
import testing.ReimbursementDAO;

@Entity
@NamedQueries({ @NamedQuery(name = "viewAllUsers", query = "FROM users u"),
		@NamedQuery(name = "findStudentByUsername", query = "FROM users u WHERE u.username = :username") })
@Table(name = "users")

public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int userId;
	@Column(name = "userrole")
	private String userRole;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "email")
	private String email;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "authorid")
	private int authorId;
    
	private static String usernameJs;

	public static List<Employee> eset = new ArrayList<Employee>();

	public Employee() {

	}

	public Employee(String username, String password) {
		this.username = username;
		this.password = password;
	}
public Employee(String firstname,String lastname,String email) {
	this.firstName = firstname;
	this.lastName= lastname;
	this.email = email;
}
	public Employee(int userid, String field) {

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

	public static Handler loginH = ctx -> {
		String usernameJs = ctx.formParam("username");
		String passwordJs = ctx.formParam("password");	
		if (ctx.formParam("username") != null) {
			usernameJs = ctx.formParam("username");
		
		EmployeeDAO employeedao = new EmployeeDAO();
		List<Employee> elist = employeedao.getEmployeeByUserName(usernameJs);
		for (Employee emp : elist) {
			System.out.print(emp);
			if (emp.getPassword().equals(passwordJs)) {
				ctx.redirect("managerHome.html");
			} else {
				ctx.redirect("managerLogin.html");
			}
		}
		}
	};
//-------------------------------------------------------------------------------------

	public static Handler employeeH = ctx -> {
		 usernameJs = ctx.formParam("username");
		String passwordJs = ctx.formParam("password");	
		if (ctx.formParam("username") != null) {
			usernameJs = ctx.formParam("username");
		
		EmployeeDAO employeedao = new EmployeeDAO();
		List<Employee> elist = employeedao.getEmployeeByUserName(usernameJs);
		for (Employee emp : elist) {
//			System.out.print(emp);
			if (emp.getPassword().equals(passwordJs)) {
				System.out.println(usernameJs+ "before redirect");
				
				ctx.redirect("employeeHome.html");
			} else {
				ctx.redirect("employeeLogin.html");
			}
		}
		}
	};
	// employee login 
	
	
	public static Handler allEmployeeH= ctx ->{
		
		EmployeeDAO employeedao = new EmployeeDAO();
		List<Employee> elist = employeedao.getAllEmployee();
			ctx.json(elist);
			
		
  

	  };

		public static Handler viewMyInfo= ctx->{
	
			EmployeeDAO employeedao = new EmployeeDAO();
			List<Employee> emplist = employeedao.getEmployeeByUserName(usernameJs);
			System.out.println(usernameJs + "after redirect");
			System.out.println(emplist);
             ctx.json(emplist);
       
             
			
			   
			       
			
	
			
		};
			public static Handler updateInfo= ctx->{
			String firstnameJs = ctx.formParam("firstname");
			String lastnameJs = ctx.formParam("lastname");
			String emailJs = ctx.formParam("email");
			
			
EmployeeDAO empdao = new EmployeeDAO();
 List<Employee> updatelist = empdao.getEmployeeByUserName(usernameJs);
       for(Employee emp: updatelist) {
    	   emp.setFirstName(firstnameJs);
    	   emp.setLastName(lastnameJs);
    	   emp.setEmail(emailJs);
    	   
    	   empdao.updateEmployee(emp);
       }
			ctx.redirect("employeeHome.html");
			
		};
}
