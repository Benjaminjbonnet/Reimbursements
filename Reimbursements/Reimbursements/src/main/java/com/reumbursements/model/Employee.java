package com.reumbursements.model;

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

import DAO.EmployeeDAO;
import io.javalin.http.Handler;

public class Employee {

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

	public static Connection getConnection() throws IOException, SQLException {
		Connection conn;
		FileInputStream connectionprop = new FileInputStream("C:\\Users\\benja\\OneDrive\\Desktop\\project 1\\project 1\\Reimbursements\\Connection.properties");
		Properties prop = new Properties();
		prop.load(connectionprop);

		String url = (String) prop.get("url");
		String username = (String) prop.get("username");
		String password = (String) prop.get("password");
		return DriverManager.getConnection(url, username, password);
	}
//----------------------------------------------------------------------------------------
	public static Handler loginH = ctx -> {
		try (Connection connection = getConnection()) {
			String usernameJs = ctx.formParam("username");
			String passwordJs = ctx.formParam("password");
		PreparedStatement ppst = connection.prepareStatement("select * from users");
			ResultSet rs= ppst.executeQuery();
			while(rs.next()) {
				Employee e = new Employee();
		if((rs.getString("username").equals(usernameJs) && rs.getString("password").equals(passwordJs))){

			ctx.status(200);
		}else
		{

			ctx.status(200);
		}
			}
				
			
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	
	
//-------------------------------------------------------------------------------------
	public static Handler employeesH = ctx -> {
		try (Connection connection = getConnection()) {
			EmployeeDAO employeedao = new EmployeeDAO(connection);
			List<Employee> loginSet = employeedao.getAllEmployees();
		ctx.json(loginSet);
				
		
			
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};


}
