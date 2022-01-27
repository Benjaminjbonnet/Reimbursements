package com.reumbursements.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Set;

import DAO.EmployeeDAO;

public class Employee {

	private int userId;
	private String lastName;
	private String firstName;
	private String email;
	private String username;
	private String password;

	public Employee() {

	}
	// to constructor to retrieve username and password -------------------------------
	public Employee(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	
	public Employee(int userId, String lastName, String firstName, String email, String username, String password) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
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
		password = password;
	}

	@Override
	public String toString() {
		return "Reimbursements [userId=" + userId + ", lastName=" + lastName + ", firstName=" + firstName + ", email="
				+ email + ", username=" + username + ", Password=" + password + "]";
	}

	public static Connection getConnection() throws IOException, SQLException {
		Connection conn;
		FileInputStream connectionprop = new FileInputStream(
				"C:\\Users\\benja\\OneDrive\\Desktop\\project 1\\Reimbursements\\Connection.properties");
		Properties prop = new Properties();
		prop.load(connectionprop);

		String url = (String) prop.get("URL");
		String username = (String) prop.get("username");
		String password = (String) prop.get("password");
		return DriverManager.getConnection(url,username,password);
	}
	
	public boolean logins(String username,String password) {
		try (Connection connection = getConnection()){
			EmployeeDAO employeedao = new EmployeeDAO(connection);
			Set<Employee> loginSet = employeedao.getEmployee();
			for(Employee e: loginSet) {
				if((e.getUsername().contains(username)&& e.getPassword().contains(password))){
					System.out.print("Successful login");
				}
			}
			
			
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
		
	}

}
