/*
 * This class for save and load to database entity TaskComplited
 */

package org.oa.getmac.repository;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.oa.getmac.model.TaskComplited;
import org.oa.getmac.modelTDO.DTODataTable;
import org.oa.getmac.modelTDO.DTOTaskComplitedJoinTask;
import org.oa.getmac.modelTDO.DTResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

@Repository

public class TaskComplitedRepository {
	private static Logger log = Logger.getLogger(TaskComplitedRepository.class);
	private SessionFactory sessionFactory;
	private static int draw = 0;

	@Autowired
	public TaskComplitedRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void create(TaskComplited item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		session.close();
	}

	public void update(TaskComplited item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(TaskComplited item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(item);
		session.getTransaction().commit();
		log.info("delete");
		session.close();
	}

	public List<TaskComplited> findAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM TaskComplited");
		List<TaskComplited> result = query.list();

		session.getTransaction().commit();
		session.close();
		return result;
	}

	public List<TaskComplited> findByTaskId(int idTask) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(TaskComplited.class);
		criteria.add(Restrictions.eq("taskid", idTask));
		List<TaskComplited> taskCompliteds = criteria.list();
		session.getTransaction().commit();
		session.close();
		return taskCompliteds;
	}

	public List<TaskComplited> findByWaitTask() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(TaskComplited.class);
		criteria.add(Restrictions.eq("status", "wait"));
		criteria.addOrder(Order.asc("startTime"));
		criteria.setMaxResults(1);
		List<TaskComplited> taskCompliteds = criteria.list();
		session.getTransaction().commit();
		session.close();
		return taskCompliteds;
	}

	public DTODataTable findJoinTaskByTaskId(HttpServletRequest request) {
		String sqlQuery;
		String sqlQueryBase = "select " + "tasks_complited.taskcompid as `taskcompid`, "
				+ "tasks.taskname as `taskname`, " + "tasks_complited.`type` as `type`, "
				+ "tasks_complited.`status` as `status`, " + "tasks_complited.starttime as `starttime`, "
				+ "tasks_complited.stoptime as `stoptime` " + "from tasks_complited " + "left join tasks "
				+ "on tasks_complited.taskid = tasks.taskid ";
		String[] cols = { "taskcompid", "taskname", "type", "status", "startTime", "stopTime" };
		String[] searchCols = new String[cols.length];
		String table = "tasks_complited";
		int amount = 5; /* Amount in Show Entry drop down */
		int start = 0; /* Counter for Paging, initially 0 */
		int col = 0; /* Column number in the datatable */
		draw++;
		List<String> sArray = new ArrayList<String>();

		for (int i = 0; i < cols.length; i++) {
			String tempSearch = request.getParameter("columns[" + i + "][search][value]");
			if (!tempSearch.equals("")) {
				String partSql = " " + cols[i] + " like '%" + tempSearch + "%'";
				sArray.add(partSql);
			}
		}

		String individualSearch = "";

		if (sArray.size() == 1) {
			individualSearch = sArray.get(0);
		} else if (sArray.size() > 1) {
			for (int i = 0; i < sArray.size() - 1; i++) {
				individualSearch += sArray.get(i) + " and ";
			}
			individualSearch += sArray.get(sArray.size() - 1);
		}

		String dir = "asc";
		String dtStart = request.getParameter("start");
		System.out.println("dtStart=" + dtStart);
		String dtAmount = request.getParameter("length");
		System.out.println("dtAmount=" + dtAmount);
		String dtCol = request.getParameter("order[0][column]");
		System.out.println("dtCol=" + dtCol);
		String dtDir = request.getParameter("order[0][dir]");
		System.out.println("dtDir=" + dtDir);
		String dtSearch = request.getParameter("search[value]");
		System.out.println("dtSearch=" + dtSearch);

		if (dtStart != null) {
			start = Integer.parseInt(dtStart);
			if (start < 0)
				start = 0;
		}
		if (dtAmount != null) {
			amount = Integer.parseInt(dtAmount);
			if (amount < 10 || amount > 100)
				amount = 10;
		}

		if (dtCol != null) {
			col = Integer.parseInt(dtCol);
			if (col < 0 || col > 5)
				col = 0;
		}
		if (dtDir != null) {
			if (!dtDir.equals("asc"))
				dir = "desc";
		}

		String searchSQL = "";
		String colNameOrderBy = cols[col];
		String searchTerm = request.getParameter("search[value]");
		String globeSearch = " where (";
		for (int i = 0; i < cols.length; i++) {
			globeSearch = globeSearch + "`" + cols[i] + "` like '%" + searchTerm + "%' ";
			if (i + 1 != cols.length) {
				globeSearch = globeSearch + " or ";
			}
		}
		globeSearch = globeSearch + ")";

		System.out.println("searchTerm=" + searchTerm);
		System.out.println("individualSearch=" + individualSearch);

		if (searchTerm != "" && individualSearch != "") {
			searchSQL = globeSearch + " and " + individualSearch;
		} else if (individualSearch != "") {
			System.out.println("individualSearch!=");
			searchSQL = " where " + individualSearch;
		} else if (searchTerm != "") {
			searchSQL = globeSearch;
		}

		sqlQuery = sqlQueryBase + searchSQL;
		sqlQuery += " order by " + colNameOrderBy + " " + dir;
		sqlQuery += " limit " + dtStart + ", " + dtAmount;

		System.out.println("sqlQuery=" + sqlQuery);
		// Count before filter
		Session sessionCountBeforeFilter = sessionFactory.openSession();
		sessionCountBeforeFilter.beginTransaction();
		String sqlQueryCount = "SELECT count(*) FROM " + table;
		Query queryCount = sessionCountBeforeFilter.createSQLQuery(sqlQueryCount);
		Integer countrow = ((Number) queryCount.uniqueResult()).intValue();
		sessionCountBeforeFilter.getTransaction().commit();
		sessionCountBeforeFilter.close();

		Integer totalBeforeFilter = countrow;

		// Select row
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createSQLQuery(sqlQuery).addScalar("taskcompid", StandardBasicTypes.INTEGER)
				.addScalar("taskname", StandardBasicTypes.STRING).addScalar("type", StandardBasicTypes.STRING)
				.addScalar("status", StandardBasicTypes.STRING).addScalar("startTime").addScalar("stopTime")
				.setResultTransformer(Transformers.aliasToBean(DTOTaskComplitedJoinTask.class));

		List<DTOTaskComplitedJoinTask> result = query.list();
		session.getTransaction().commit();
		session.close();

		List<DTResult> result2 = new ArrayList<>();
		for (DTResult r : result) {
			result2.add(r);
		}

		// Count after filter
		Session sessionCountAfterFilter = sessionFactory.openSession();
		sessionCountAfterFilter.beginTransaction();
		String sqlQueryCountAfterFilter = "select count(*) " + "from tasks_complited " + "left join tasks "
				+ "on tasks_complited.taskid = tasks.taskid ";

		sqlQueryCountAfterFilter = sqlQueryCountAfterFilter + searchSQL;

		Query queryCountAfterFilter = sessionCountAfterFilter.createSQLQuery(sqlQueryCountAfterFilter);
		Integer countrowAfterFilter = ((Number) queryCountAfterFilter.uniqueResult()).intValue();
		sessionCountAfterFilter.getTransaction().commit();
		sessionCountAfterFilter.close();

		DTODataTable tdDtoDataTable = new DTODataTable();
		tdDtoDataTable.setDraw(draw);
		tdDtoDataTable.setRecordsFiltered(countrowAfterFilter);
		tdDtoDataTable.setRecordsTotal(totalBeforeFilter);
		tdDtoDataTable.setData(result2);

		return tdDtoDataTable;
	}

}
