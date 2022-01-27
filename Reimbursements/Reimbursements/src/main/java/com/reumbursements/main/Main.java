package com.reumbursements.main;

import com.reumbursements.model.Employee;

import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;
import io.javalin.http.Handler;
import io.javalin.http.staticfiles.Location;

public class Main {
	
	public static void main(String[] args) {
		Javalin app=Javalin.create(ctx->{ctx.enableCorsForAllOrigins();ctx.addStaticFiles("web", Location.CLASSPATH);}).start(4000);
		//creates webserver at http://localhost:4000/

		app.get("/managerlogin",Employee.loginH);
		app.post("/managerlogin",Employee.loginH);
		app.get("/manager/home",Employee.employeesH);
		

	}}




