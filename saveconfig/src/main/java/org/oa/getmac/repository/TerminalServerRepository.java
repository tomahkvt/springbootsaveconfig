/*
 * This class for save and load to database entity TerminalServer
 */
package org.oa.getmac.repository;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.oa.getmac.model.Device;
import org.oa.getmac.model.Template;
import org.oa.getmac.model.TerminalServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public class TerminalServerRepository {
	private static Logger log = Logger.getLogger(TerminalServerRepository.class);
	private SessionFactory sessionFactory;
	@Autowired
	private TemplateRepository templateRepository;

	@Autowired
	public TerminalServerRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void create(TerminalServer item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		session.close();
	}

	public void update(TerminalServer item) {
		TerminalServer terminalServer;
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		terminalServer = (TerminalServer) session.get(TerminalServer.class, item.getId());

		terminalServer.setDeviceName(item.getDeviceName());
		terminalServer.setDeviceIp(item.getDeviceIp());
		terminalServer.setPromt1(item.getPromt1());
		terminalServer.setUsername1(item.getUsername1());
		if (item.getPassw1() != null) {
			terminalServer.setPassw1(item.getPassw1());
		}
		terminalServer.setPromt2(item.getPromt2());
		terminalServer.setUsername2(item.getUsername2());
		if (item.getPassw2() != null) {
			terminalServer.setPassw2(item.getPassw2());
		}
		terminalServer.setPromt2(item.getPromt2());
		session.update(terminalServer);
		session.getTransaction().commit();
		session.close();
	}

	public List<Template> delete(TerminalServer item) {
		List<Template> templates = templateRepository.findByIdTerminalServer(item);

		if (templates.isEmpty()) {

			Session session = sessionFactory.openSession();
			session.beginTransaction();

			session.delete(item);
			session.getTransaction().commit();
			log.info("delete");
			session.close();

		}
		return templates;

	}

	public List<TerminalServer> findAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM TerminalServer");
		List<TerminalServer> result = (List<TerminalServer>) query.list();

		session.getTransaction().commit();
		session.close();
		return result;
	}

	public TerminalServer findForDevice(Device device) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sqlQuery = "select terminal_server.* from terminal_server "
				+ "left join template on terminal_server.id = template.id_terminal_server "
				+ "left join device on device.id_template = template.id " + "where device.id = :idDevice";
		Query query = session.createSQLQuery(sqlQuery).addEntity(TerminalServer.class).setParameter("idDevice",
				device.getId());

		TerminalServer result = (TerminalServer) query.list().get(0);

		session.getTransaction().commit();
		session.close();
		return result;
	}

	public TerminalServer findById(int idTerminalServer) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		TerminalServer result = (TerminalServer) session.get(TerminalServer.class, idTerminalServer);
		session.getTransaction().commit();
		session.close();
		return result;
	}

	public Boolean isExistByName(TerminalServer terminalServer) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(TerminalServer.class);
		cr.add(Restrictions.like("deviceName", terminalServer.getDeviceName()));
		cr.add(Restrictions.neOrIsNotNull("id", terminalServer.getId()));
		Long countRow = (Long) cr.setProjection(Projections.rowCount()).uniqueResult();

		session.getTransaction().commit();
		session.close();
		if (countRow == 0) {
			return false;
		} else {
			return true;
		}
	}
}
