package com.testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.reimbursements.model.Employee;
import com.reimbursements.model.Reimbursements;

import antlr.collections.List;

public class Test {
	ArrayList<Employee> employee = new ArrayList<Employee>();
Reimbursements r = new Reimbursements();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@org.junit.Test
	public void test() {
		
		String username = "benjanminB";
		String password= "12345678";
		Employee e = new Employee(username,password);
		System.out.print(e);
	}
	public void add() {
		
		String username = "benjanminB";
		String password= "12345678";
		Employee e = new Employee(username,password);
		employee.add(e);
	}

}
