package com.reimbursements.main;

import com.reimbursements.model.Employee;
import com.reimbursements.model.Reimbursements;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class MainDriver {

	public static void main(String[] args) {
		Javalin app = Javalin.create(ctx -> {
			ctx.enableCorsForAllOrigins();
			ctx.addStaticFiles("web", Location.CLASSPATH);
		}).start(4000);

		// creates at http://localhost:4000/
		
		
		app.get("/managerlogin", Employee.employeeH);
		app.post("/managerlogin", Employee.loginH);
		app.get("/manager/home", Employee.employeeH);
		//manager views pending request
		app.get("/pending",Reimbursements.pendingH);
		//manager approved requests		
		
		app.post("approve",Reimbursements.approveH);
		app.get("/reimbursements", Reimbursements.viewAll);
		
		// Employee submitted tickets
		app.post("/employeelogin",Employee.eloginH);
		app.post("/submit", Reimbursements.employeeSubmit);
		app.post("/getemployee",Employee.employeeH);
		app.get("/getemployee",Employee.employeeH);

//		app.post("/updateinfo",Employee.updateInfo);
//		app.get("/viewinfo",Employee.viewMyInfo);
		

	
	
	}

}
