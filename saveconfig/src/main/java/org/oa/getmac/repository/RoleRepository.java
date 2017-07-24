/*
 * This class for save and load to database entity Role
 */

package org.oa.getmac.repository;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.oa.getmac.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository

public class RoleRepository {
	private static Logger log = Logger.getLogger(RoleRepository.class);
	private SessionFactory sessionFactory;


	@Autowired
	public RoleRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public List<Role> findAll() {
		Session session = sessionFactory.openSession();
    	 session.beginTransaction();
		Query query = session.createQuery("FROM Role");
		List<Role> result = query.list();
		session.getTransaction().commit();
		session.close();
		return result;
	}


}
