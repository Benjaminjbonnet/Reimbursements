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

		app.post("/managerlogin", Employee.loginH);
		app.get("/managerhome", Employee.allEmployeeH);
		// manager views pending request
		app.get("/pending", Reimbursements.pendingH);
		// manager approved requests
		app.post("managersubmit",Reimbursements.approveH);
		app.get("managersubmit",Reimbursements.approveH);
		app.post("approve", Reimbursements.transaction);
		
		
		app.get("/reimbursements", Reimbursements.viewAll);

		// Employee submitted tickets
		app.post("/employeelogin", Employee.employeeH);
    
		app.post("/submit", Reimbursements.employeeSubmit);
//        app.get("employeeHome.html",Employee.viewMyInfo);
		app.post("/viewmyinfo", Employee.viewMyInfo);

	app.post("/updateinfo", Employee.updateInfo);
    	 app.get("/viewmyinfo", Employee.viewMyInfo);
;
    	 app.post("/employeeLogin.html",Employee.employeeH);

	}

}
