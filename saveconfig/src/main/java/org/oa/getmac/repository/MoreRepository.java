/*
 * This class for save and load to database entity More
 */

package org.oa.getmac.repository;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.oa.getmac.model.Macros;
import org.oa.getmac.model.More;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class MoreRepository {
	private static Logger log = Logger.getLogger(MoreRepository.class);
	private SessionFactory sessionFactory;
	@Autowired
	TerminalServerRepository terminalServerRepository;

	@Autowired
	public MoreRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void create(More item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		session.close();
	}

	public void update(More item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(More item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(item);
		session.getTransaction().commit();
		log.info("delete");
		session.close();
	}

	
	public void deleteForTemplate(int idTemplate) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("delete More where idTemplate = :idTemplate");
		query.setParameter("idTemplate", idTemplate);
		int result = query.executeUpdate();
		session.getTransaction().commit();
		
		session.close();
		
	}		
	
	
	public List<More> findAll() {
		Session session = sessionFactory.openSession();
    	 session.beginTransaction();

		Query query = session.createQuery("FROM More");
		List<More> result = query.list();

		
		session.getTransaction().commit();
		session.close();
		return result;
	}

	public List<More> findById(int idTemplate) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(More.class);
		criteria.add(Restrictions.eq("idTemplate", idTemplate));
		List<More> mores= criteria.list();
		session.getTransaction().commit();
		session.close();
		return mores;
	}
	
	public List<More> getMoreToDo(int idTemplate) {
		List<More> result = this.findById(idTemplate);
		Macros macros = new Macros();
		macros.getMacros().put("{n}", "\n");
		macros.getMacros().put("{s}", " ");
		Pattern pattern = Pattern.compile("\\{[^\\}|\\{]+\\}");
		for (More more : result) {
			Matcher matcher1 = pattern.matcher(more.getMore());
			while (matcher1.find()) {
				String macrosKey = matcher1.group();
				String macrosValue = macros.getMacros().get(macrosKey);
				if (macrosValue != null) {
					more.setMore(more.getMore().replace(macrosKey, macrosValue));
				}
			}
			
			Matcher matcher2 = pattern.matcher(more.getMoreDo());
			while (matcher2.find()) {
				String macrosKey2 = matcher2.group();
				String macrosValue2 = macros.getMacros().get(macrosKey2);
				if (macrosValue2 != null) {
					more.setMoreDo(more.getMoreDo().replace(macrosKey2, macrosValue2));
				}
			}
		}
		return result;
	}
}
