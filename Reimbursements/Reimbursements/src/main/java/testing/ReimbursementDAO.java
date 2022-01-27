package testing;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.reimbursements.model.Employee;
import com.reimbursements.model.Reimbursements;

public class ReimbursementDAO {
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;

	public List<Reimbursements> getAllReimbursements() {
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
		Transaction trns = session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Reimbursements> rquery = builder.createQuery(Reimbursements.class);

		Root<Reimbursements> root = rquery.from(Reimbursements.class);
		rquery.select(root);
		List<Reimbursements> reimbursements = session.createQuery(rquery).getResultList();
		System.out.println(reimbursements);
		trns.commit();
		return reimbursements;

	}

	public List<Reimbursements> getReimbursementbyid(int reimbursementidJs,String field) {
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
		Transaction tr = session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Reimbursements> query = builder.createQuery(Reimbursements.class);

		Root<Reimbursements> root = query.from(Reimbursements.class);
		query.select(root).where(builder.equal(root.get("reimbursementid"), reimbursementidJs));
		List<Reimbursements> reimbursement = session.createQuery(query).getResultList();
		tr.commit();

		return reimbursement;

	}

	public void addReimbursement(int reimbursementidJs, String statusJs, double amountJs, String descriptionJs,
			String typeJs, int resolvetimeJs, int submittimeJs, int authoridJs, int resolveridJs) {
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

		Session session = sessionFactory.openSession();
		Transaction trn = session.beginTransaction();
		Reimbursements r = new Reimbursements(reimbursementidJs, statusJs, amountJs, descriptionJs, typeJs,
				resolvetimeJs, submittimeJs, authoridJs, resolveridJs);
		session.save(r);
		
		trn.commit();
	}
	
	public void updateReimbursement(int reimbursementidJs, String field) {
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
		Session session = sessionFactory.openSession();
       Reimbursements r=session.load(Reimbursements.class, new Integer(reimbursementidJs));
        Transaction
        transactionupdate =session.beginTransaction(); r.setStatus(field);
         session.saveOrUpdate(r); transactionupdate.commit();
	}

}
