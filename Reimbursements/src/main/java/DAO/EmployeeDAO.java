package DAO;
import org.hibernate.boot.Metadata;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.reimbursements.model.Employee;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeDAO {
	private static SessionFactory sessionFactory;
	private static StandardServiceRegistry registry;
public static void main(String[] args) {
	Javalin app=Javalin.create(ctx->{ctx.enableCorsForAllOrigins();ctx.addStaticFiles("web", Location.CLASSPATH);}).start(4000);
	//creates webserver at http://localhost:4000/

	

 

        
        
      
          



}
           
}
	

