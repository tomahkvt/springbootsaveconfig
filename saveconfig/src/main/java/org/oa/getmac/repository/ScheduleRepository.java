/*
 * This class for save and load to database entity Schedule
 */
package org.oa.getmac.repository;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.oa.getmac.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository

public class ScheduleRepository {
	private static Logger log = Logger.getLogger(ScheduleRepository.class);
	private SessionFactory sessionFactory;

	@Autowired
	public ScheduleRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void create(Schedule item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		session.close();
	}

	public void update(Schedule item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(Schedule item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(item);
		session.getTransaction().commit();
		log.info("delete");
		session.close();
	}

	public List<Schedule> findAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Schedule");
		List<Schedule> result = query.list();
		session.getTransaction().commit();
		session.close();
		return result;
	}

	public List<Schedule> findByTaskId(int idTask) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Schedule.class);
		criteria.add(Restrictions.eq("taskid", idTask));
		List<Schedule> schedules = criteria.list();
		session.getTransaction().commit();
		session.close();
		return schedules;
	}

	public List<Schedule> findByStringDate(String date) {
		String part[] = date.split(":");
		String hql = "FROM Schedule where day_of_week = " + part[0] + " and hour = " + part[1] + " and minute = "
				+ part[2];
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		List<Schedule> result = query.list();
		session.getTransaction().commit();
		session.close();
		return result;
	}

	public void deleteForTask(int idTask) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("delete Schedule where taskid = :taskid");
		query.setParameter("taskid", idTask);
		int result = query.executeUpdate();
		session.getTransaction().commit();

		session.close();
	}
}
