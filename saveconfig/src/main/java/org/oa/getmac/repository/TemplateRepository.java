/*
 * This class for save and load to database entity Template
 */

package org.oa.getmac.repository;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.oa.getmac.model.Template;
import org.oa.getmac.model.TerminalServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository

public class TemplateRepository {
	private static Logger log = Logger.getLogger(TemplateRepository.class);
	private SessionFactory sessionFactory;


	@Autowired
	public TemplateRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void create(Template item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		session.close();
	}

	public void update(Template item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(Template item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(item);
		session.getTransaction().commit();
		log.info("delete");
		session.close();
	}

	public List<Template> findAll() {
		Session session = sessionFactory.openSession();
    	 session.beginTransaction();

		Query query = session.createQuery("FROM Template");
		List<Template> result = query.list();
		session.getTransaction().commit();
		session.close();
		return result;
	}

	public Template findById(int idTemplate) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Template result = (Template) session.get(Template.class, idTemplate);
		//.out.println(result);
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
	public List<Template> findByIdTerminalServer(TerminalServer terminalServer){
		
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Template.class);
		cr.add(Restrictions.eq("terminalServer", terminalServer));
		session.beginTransaction();
		List<Template> result = cr.list();
		session.getTransaction().commit();
		session.close();
		
		return result;
		
	}

	public boolean isExistByName(Template template) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(Template.class);
		cr.add(Restrictions.like("templateName",template.getTemplateName()));
		cr.add(Restrictions.neOrIsNotNull("id", template.getId()));
		Long countRow = (Long) cr.setProjection(Projections.rowCount()).uniqueResult();
		session.getTransaction().commit();
		session.close();
		if (countRow == 0){
			return false;
		}else{
			return true;
		}
	}

	public Long getCountTempelate() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sqlQuery = "select count(*) from template";
		Query query = session.createSQLQuery(sqlQuery);
		Long count = ((Number) query.uniqueResult()).longValue();
		session.getTransaction().commit();
		session.close();
		return count;
	}
}
