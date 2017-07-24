/*
 * This class for save and load to database entity UserRole
 */
package org.oa.getmac.repository;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.oa.getmac.model.User;
import org.oa.getmac.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository

public class UserRoleRepository {
	private static Logger log = Logger.getLogger(UserRoleRepository.class);
	private SessionFactory sessionFactory;


	@Autowired
	public UserRoleRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void create(UserRole item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		session.close();
	}

	public void update(UserRole item) {
		User user;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(UserRole item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(item);
		session.getTransaction().commit();
		log.info("delete");
		session.close();
	}

	public List<UserRole> findAllForUser(String username) {
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(UserRole.class);
		cr.add(Restrictions.eq("username", username));
    	session.beginTransaction();
    	List<UserRole> result = cr.list();
		session.getTransaction().commit();
		session.close();
		return result;
	}


	public void deleteForUser(String username) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("delete UserRole where username = :username");
		query.setParameter("username", username);
		int result = query.executeUpdate();
		session.getTransaction().commit();
		
		session.close();
		
	}		
	


}
