/*
 * This class for save and load to database entity Command
 */
package org.oa.getmac.repository;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.oa.getmac.model.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CommandRepository {
	private static Logger log = Logger.getLogger(CommandRepository.class);
	private SessionFactory sessionFactory;

	@Autowired
	public CommandRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void create(Command item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		session.close();
	}

	public void update(Command item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(Command item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(item);
		session.getTransaction().commit();

		session.close();
	}

	public List<Command> findStartForTemplate(int idTemplate) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Command.class);
		criteria.add(Restrictions.eq("idTemplate", idTemplate));
		criteria.add(Restrictions.le("order", 50));
		criteria.addOrder(Order.asc("order"));
		List<Command> commands = criteria.list();
		session.getTransaction().commit();
		session.close();
		return commands;
	}

	public List<Command> findEndForTemplate(int idTemplate) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Command.class);
		criteria.add(Restrictions.eq("idTemplate", idTemplate));
		criteria.add(Restrictions.gt("order", 100));
		criteria.addOrder(Order.asc("order"));
		List<Command> commands = criteria.list();
		session.getTransaction().commit();
		session.close();
		return commands;
	}

	public List<Command> findBodyForTemplate(int idTemplate) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Command.class);
		criteria.add(Restrictions.eq("idTemplate", idTemplate));
		criteria.add(Restrictions.gt("order", 50));
		criteria.add(Restrictions.le("order", 100));
		criteria.addOrder(Order.asc("order"));
		List<Command> commands = criteria.list();
		session.getTransaction().commit();
		session.close();
		return commands;
	}

	public void deleteForTemplate(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("delete Command where idTemplate = :idTemplate");
		query.setParameter("idTemplate", id);
		int result = query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
}
