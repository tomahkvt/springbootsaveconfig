/*
 * This class for save and load to database entity User
 */
package org.oa.getmac.repository;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.oa.getmac.model.Role;
import org.oa.getmac.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository

public class UserRepository {
	private static Logger log = Logger.getLogger(UserRepository.class);
	private SessionFactory sessionFactory;

	@Autowired
	public UserRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void create(User item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		session.close();
	}

	public void update(User item) {
		User user;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		user = (User) session.get(User.class, item.getUsername());
		if (item.getPassword() != null) {
			user.setPassword(item.getPassword());
		}
		user.setEnable(item.getEnable());
		user.setRightRoles(item.getRightRoles());
		user.setRoles(item.getRoles());
		session.update(user);
		session.getTransaction().commit();
		session.close();

	}

	public void delete(User item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(item);
		session.getTransaction().commit();
		log.info("delete");
		session.close();
	}

	public List<User> findAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM User");
		List<User> result = query.list();

		session.getTransaction().commit();
		session.close();
		return result;
	}

	public User findByLogin(String login) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		User result = (User) session.get(User.class, login);
		Query queryRightRole = session.createQuery("From Role");
		Set<Role> rightRole = new HashSet<Role>(queryRightRole.list());
		rightRole.removeAll(result.getRoles());
		result.setRightRoles(rightRole);
		session.getTransaction().commit();
		session.close();
		return result;
	}

	public boolean isExistByLogin(User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.like("username", user.getUsername()));
		Long countRow = (Long) cr.setProjection(Projections.rowCount()).uniqueResult();
		session.getTransaction().commit();
		session.close();
		if (countRow == 0) {
			return false;
		} else {
			return true;
		}
	}
}
