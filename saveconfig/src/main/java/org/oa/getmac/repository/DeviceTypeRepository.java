/*
 * This class for save and load to database entity Device
 */

package org.oa.getmac.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.oa.getmac.model.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DeviceTypeRepository {

	private final SessionFactory sessionFactory;

	@Autowired
	public DeviceTypeRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void create(DeviceType item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		session.close();
	}

	public void update(DeviceType item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(DeviceType item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(item);
		session.getTransaction().commit();
		session.close();
	}

	public List<DeviceType> findAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM DeviceType");
		List<DeviceType> result = query.list();

		session.getTransaction().commit();
		session.close();
		return result;
	}
}
