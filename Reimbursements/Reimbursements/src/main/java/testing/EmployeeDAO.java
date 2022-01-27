package testing;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.reimbursements.model.Employee;
import com.reimbursements.model.Reimbursements;

public class EmployeeDAO {
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;

	public List<Employee> getEmployeeByUserName(String usernameJs) {
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
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee> query = builder.createQuery(Employee.class);

		Root<Employee> root = query.from(Employee.class);
		query.select(root).where(builder.equal(root.get("username"), usernameJs));
		List<Employee> user = session.createQuery(query).getResultList();
		trans.commit();
		return user;

//	        
//	             

//	            
//	              Student s=session.load(Student.class, new Integer(1)); System.out.println(s);

		/*
		 * Student s=session.load(Student.class, new Integer(2)); s.setFirstName("Ben");
		 * session.saveOrUpdate(s); transaction.commit();
		 */

//
//	           Student s=session.load(Student.class, new Integer(2));
//	           Transaction tx=session.beginTransaction();
//	           session.delete(s);
//	           tx.commit();
//	           

	}

	public List<Employee> getAllEmployee() {
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
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee> query = builder.createQuery(Employee.class);

		Root<Employee> root = query.from(Employee.class);
		query.select(root);
		List<Employee> user = session.createQuery(query).getResultList();
		System.out.println(user);
		transaction.commit();
		return user;

	}
	public void updateEmployee(Employee emp) {
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

		}Session session = sessionFactory.openSession();
	        Transaction transactionupdate =session.beginTransaction(); 
	         Employee e = new Employee(emp.getFirstName(), emp.getLastName(), emp.getEmail());

	        
	         session.saveOrUpdate(emp); transactionupdate.commit();
		


	}
}
