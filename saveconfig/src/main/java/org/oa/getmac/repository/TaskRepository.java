/*
 * This class for save and load to database entity Task
 */

package org.oa.getmac.repository;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.oa.getmac.model.Task;
import org.oa.getmac.model.DeviceGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository

public class TaskRepository {
	private static Logger log = Logger.getLogger(TaskRepository.class);
	private SessionFactory sessionFactory;

	@Autowired
	public TaskRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void create(Task item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		session.close();
	}

	public void update(Task item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(Task item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(item);
		session.getTransaction().commit();
		session.close();
	}

	public List<Task> findAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Task");
		List<Task> result = query.list();
		session.getTransaction().commit();
		session.close();
		return result;
	}

	public boolean isExistByName(Task task) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(Task.class);
		cr.add(Restrictions.like("name", task.getName()));
		cr.add(Restrictions.neOrIsNotNull("taskid", task.getTaskid()));
		Long countRow = (Long) cr.setProjection(Projections.rowCount()).uniqueResult();
		session.getTransaction().commit();
		session.close();
		if (countRow == 0) {
			return false;
		} else {
			return true;
		}
	}

	public Task findById(int idTask) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query queryExcludeGroups = session.createQuery("From DeviceGroup");
		Set<DeviceGroup> excludeGroups = new HashSet<DeviceGroup>(queryExcludeGroups.list());
		Task result = (Task) session.get(Task.class, idTask);
		session.getTransaction().commit();
		session.close();
		if (result != null) {
			excludeGroups.removeAll(result.getGroups());
			result.setExcludeGroups(excludeGroups);
		}
		return result;
	}

}
