/*
 * This class for save and load to database entity GlobalParam
 */

package org.oa.getmac.repository;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.oa.getmac.model.GlobalParamDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.*;

@Repository
public class GlobalParamRepository {
	private static Logger log = Logger.getLogger(GlobalParamRepository.class);
	private SessionFactory sessionFactory;

	@Autowired
	public GlobalParamRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Map<String, String> getMap() throws SQLException {

		List<GlobalParamDB> result = null;
		Map<String, String> map = null;

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		map = new HashMap<String, String>();

		Query query = session.createQuery("FROM GlobalParamDB");
		result = query.list();

		session.getTransaction().commit();
		session.close();

		for (GlobalParamDB r : result) {
			map.put(r.getParamkey(), r.getParamvalue());
		}
		return map;

	}

	public void setMap(Map<String, String> globalParamMap) {
		String hql = "UPDATE GlobalParamDB set paramvalue = :paramvalue " + "WHERE paramkey = :paramkey";
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		for (Map.Entry<String, String> param : globalParamMap.entrySet()) {
			query.setParameter("paramkey", param.getKey());
			query.setParameter("paramvalue", param.getValue());
			query.executeUpdate();
		}
		session.getTransaction().commit();
		session.close();
	}

}
