/*
 * This class for save and load to database entity TaskComplited
 */

package org.oa.getmac.repository;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.oa.getmac.model.DeviceGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository

public class TaskGroupHostRepository {
	private static Logger log = Logger.getLogger(TaskGroupHostRepository.class);
	private SessionFactory sessionFactory;
	
	@Autowired
	public TaskGroupHostRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<DeviceGroup> findGroupByTaskId(Integer taskId) {
		Session session = sessionFactory.openSession();
		 session.beginTransaction();
		 String sql = "Select groups.* from tasks_group_host " +
				 "left join groups on tasks_group_host.grouphost_id = groups.groupid "+
				 "where tasks_group_host.`type`= 'g' and tasks_group_host.taskid =:id";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("id", taskId);
		query.addEntity(DeviceGroup.class);
		List<DeviceGroup> result = query.list();
		 session.getTransaction().commit();
		session.close();
		return result;
	}

}
