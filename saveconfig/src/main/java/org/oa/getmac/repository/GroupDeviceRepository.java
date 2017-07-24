/*
 * This class for save and load to database entity GroupDevice
 */

package org.oa.getmac.repository;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.oa.getmac.model.DeviceGroup;
import org.oa.getmac.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository

public class GroupDeviceRepository {
	private static Logger log = Logger.getLogger(GroupDeviceRepository.class);
	private SessionFactory sessionFactory;
	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	public GroupDeviceRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void create(DeviceGroup item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		session.close();
	}

	public void update(DeviceGroup item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(DeviceGroup item) {
		
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(item);
			session.getTransaction().commit();
			session.close();
	
	}

	public List<DeviceGroup> findAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM DeviceGroup");
		List<DeviceGroup> result = query.list();

		session.getTransaction().commit();
		session.close();
		return result;
	}

	public DeviceGroup findById(int idDeviceGroup) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		DeviceGroup result = (DeviceGroup) session.get(DeviceGroup.class, idDeviceGroup);
		session.getTransaction().commit();
		session.close();
		return result;
	}

	public boolean isExistByName(DeviceGroup deviceGroup) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(DeviceGroup.class);
		cr.add(Restrictions.like("name", deviceGroup.getName()));
		cr.add(Restrictions.neOrIsNotNull("groupid", deviceGroup.getGroupid()));
		Long countRow = (Long) cr.setProjection(Projections.rowCount()).uniqueResult();
		session.getTransaction().commit();
		session.close();
		if (countRow == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public List<Task> findTaskByGroup(int idDeviceGroup) {
		Session session = sessionFactory.openSession();
		 session.beginTransaction();
		 String sql = 	"Select tasks.* from tasks_group " +  
				 		"left join tasks on tasks.taskid = tasks_group.taskid " +
				 		" where tasks_group.group_id = :id";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("id", idDeviceGroup);
		query.addEntity(Task.class);
		List<Task> result = query.list();
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
	

}
