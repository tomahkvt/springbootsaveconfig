/*
 * This class for save and load to database entity TaskComplitedLogging
 */

package org.oa.getmac.repository;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.oa.getmac.model.TaskComplitedLogging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository

public class TaskComplitedLoggingRepository {
	private static Logger log = Logger.getLogger(TaskComplitedLoggingRepository.class);
	private SessionFactory sessionFactory;

	@Autowired
	public TaskComplitedLoggingRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void create(TaskComplitedLogging item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		session.close();
	}

	public void update(TaskComplitedLogging item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(TaskComplitedLogging item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(item);
		session.getTransaction().commit();
		log.info("delete");
		session.close();
	}

	public List<TaskComplitedLogging> findAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM TaskComplitedLogging");
		List<TaskComplitedLogging> result = query.list();

		session.getTransaction().commit();
		session.close();
		return result;
	}

	public List<TaskComplitedLogging> findByTaskComplitedId(int idTaskcomp) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(TaskComplitedLogging.class);
		criteria.add(Restrictions.eq("taskcompid", idTaskcomp));
		List<TaskComplitedLogging> taskCompliteds = criteria.list();
		session.getTransaction().commit();
		session.close();
		return taskCompliteds;
	}
}
