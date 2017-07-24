/*
 * This class for save and load to database entity Device
 */

package org.oa.getmac.repository;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.oa.getmac.model.Command;
import org.oa.getmac.model.Device;
import org.oa.getmac.model.DeviceGroup;
import org.oa.getmac.model.Macros;
import org.oa.getmac.model.Task;
import org.oa.getmac.model.Template;
import org.oa.getmac.model.TerminalServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository

public class DeviceRepository {
	private static Logger log = Logger.getLogger(DeviceRepository.class);
	private SessionFactory sessionFactory;
	@Autowired
	TerminalServerRepository terminalServerRepository;

	@Autowired
	TaskGroupHostRepository taskGroupHostRepository;

	@Autowired
	public DeviceRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void create(Device item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		session.close();
	}

	public void update(Device item) {
		Device device;
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		device = (Device) session.get(Device.class, item.getId());
		if (item.getPassword1() != null) {
			device.setPassword1(item.getPassword1());
		}
		if (item.getPassword2() != null) {
			device.setPassword2(item.getPassword2());
		}
		if (item.getPassword3() != null) {
			device.setPassword3(item.getPassword3());
		}
		if (item.getPassword4() != null) {
			device.setPassword4(item.getPassword4());
		}
		device.setTemplate(item.getTemplate());
		device.setDeviceIp(item.getDeviceIp());
		device.setDeviceName(item.getDeviceName());
		device.setPromt1(item.getPromt1());
		device.setPromt2(item.getPromt2());
		device.setPromt3(item.getPromt3());
		device.setPromt4(item.getPromt4());
		device.setUsername1(item.getUsername1());
		device.setUsername2(item.getUsername2());
		device.setUsername3(item.getUsername3());
		device.setUsername4(item.getUsername4());
		device.setSwitchEnable(item.isSwitchEnable());
		device.setGroups(item.getGroups());
		device.setRightGroups(item.getRightGroups());

		session.update(device);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(Device item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(item);
		session.getTransaction().commit();
		log.info("delete");
		session.close();
	}

	public List<Device> findAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM Device");
		List<Device> result = query.list();

		session.getTransaction().commit();
		session.close();
		return result;
	}

	public List<Device> findAllDto() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM Device");
		session.createCriteria(Device.class);
		List<Device> result = query.list();

		session.getTransaction().commit();
		session.close();
		return result;
	}

	public Device findById(int idDevice) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Device result = (Device) session.get(Device.class, idDevice);
		Query queryRightGroups = session.createQuery("From DeviceGroup");
		Set<DeviceGroup> rightGroups = new HashSet<DeviceGroup>(queryRightGroups.list());
		// System.out.println(result);
		session.getTransaction().commit();
		session.close();
		rightGroups.removeAll(result.getGroups());
		result.setRightGroups(rightGroups);
		return result;
	}

	public List<Device> findByIdTemplate(Template template) {

		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Device.class);
		cr.add(Restrictions.eq("template", template));
		session.beginTransaction();

		List<Device> result = cr.list();
		session.getTransaction().commit();
		session.close();

		return result;

	}

	public List<Device> findEnabled() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM Device where switch_enable = 1");
		List<Device> result = query.list();

		session.getTransaction().commit();
		session.close();
		return result;

	}

	public List<Device> findByGroup(DeviceGroup group) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sqlQuery = "Select device.* from device "
				+ "left join device_groups on device_groups.deviceid = device.id "
				+ "where device_groups.groupid = :groupid";
		Query query = session.createSQLQuery(sqlQuery).addEntity(Device.class).setParameter("groupid",
				group.getGroupid());

		List<Device> result = query.list();

		session.getTransaction().commit();
		session.close();
		return result;
	}

	public List<Device> findByGroupId(Integer groupId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sqlQuery = "Select device.* from device "
				+ "left join device_groups on device_groups.deviceid = device.id "
				+ "where device_groups.groupid = :groupid";
		Query query = session.createSQLQuery(sqlQuery).addEntity(Device.class).setParameter("groupid", groupId);

		List<Device> result = query.list();

		session.getTransaction().commit();
		session.close();
		return result;
	}

	public List<Device> findByTask(Task task) {
		Set<Device> setDevices = new HashSet<Device>();
		setDevices.addAll(task.getDevices());
		Set<DeviceGroup> groups = task.getGroups();
		for (DeviceGroup group : groups) {
			setDevices.addAll(this.findByGroup(group));
		}

		return new ArrayList<Device>(setDevices);
	}

	public List<Device> findByTerminalServer(TerminalServer terminalServer) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sqlQuery = "select * from device " + "left join template on device.id_template = template.id "
				+ "where template.id_terminal_server = :idTerminalServer";
		Query query = session.createSQLQuery(sqlQuery).addEntity(Device.class).setParameter("idTerminalServer",
				terminalServer.getId());

		List<Device> result = query.list();

		session.getTransaction().commit();
		session.close();
		return result;
	}

	public List<Command> getCommand(Device device) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sqlQuery = "select commands.* from commands "
				+ "left join template on commands.id_template = template.id "
				+ "left join device on device.id_template = template.id "
				+ "where device.id = :idDevice order by commands.command_order";

		;
		Query query = session.createSQLQuery(sqlQuery).addEntity(Command.class).setParameter("idDevice",
				device.getId());

		List<Command> result = query.list();

		session.getTransaction().commit();
		session.close();
		return result;
	}

	public List<Command> getCommandToDo(Device device) {
		ArrayList<Command> result = (ArrayList<Command>) this.getCommand(device);

		TerminalServer terminalServer = device.getTemplate().getTerminalServer();
		Macros macros = new Macros();
		macros.getMacros().put("{deviceip}", device.getDeviceIp());
		macros.getMacros().put("{devicename}", device.getDeviceName());
		macros.getMacros().put("{username1}", device.getUsername1());
		macros.getMacros().put("{password1}", device.getPassword1());
		macros.getMacros().put("{promt1}", device.getPromt1());
		macros.getMacros().put("{username2}", device.getUsername2());
		macros.getMacros().put("{password2}", device.getPassword2());
		macros.getMacros().put("{promt2}", device.getPromt2());
		macros.getMacros().put("{username3}", device.getUsername3());
		macros.getMacros().put("{password3}", device.getPassword3());
		macros.getMacros().put("{promt3}", device.getPromt3());
		macros.getMacros().put("{username4}", device.getUsername4());
		macros.getMacros().put("{password4}", device.getPassword4());
		macros.getMacros().put("{promt4}", device.getPromt4());
		macros.getMacros().put("{terminal server ip}", terminalServer.getDeviceIp());
		macros.getMacros().put("{terminal server name}", terminalServer.getDeviceName());
		macros.getMacros().put("{terminal server username1}", terminalServer.getUsername1());
		macros.getMacros().put("{terminal server password1}", terminalServer.getPassw1());
		macros.getMacros().put("{terminal server promt1}", terminalServer.getPromt1());
		macros.getMacros().put("{terminal server username2}", terminalServer.getUsername2());
		macros.getMacros().put("{terminal server password2}", terminalServer.getPassw2());
		macros.getMacros().put("{terminal server promt2}", terminalServer.getPromt2());
		macros.getMacros().put("{n}", "\n");
		log.info(terminalServer.getPromt1());
		Pattern pattern = Pattern.compile("\\{[^\\}|\\{]+\\}");
		for (Command command : result) {
			Matcher matcher1 = pattern.matcher(command.getCommand());
			while (matcher1.find()) {
				String macrosKey = matcher1.group();
				String macrosValue = macros.getMacros().get(macrosKey);
				if (macrosValue != null) {
					command.setCommand(command.getCommand().replace(macrosKey, macrosValue));
				}
			}

			Matcher matcher2 = pattern.matcher(command.getWaitFor());
			while (matcher2.find()) {
				String macrosKey2 = matcher2.group();
				String macrosValue2 = macros.getMacros().get(macrosKey2);
				if (macrosValue2 != null) {
					command.setWaitFor(command.getWaitFor().replace(macrosKey2, macrosValue2));
				}
			}
		}

		return result;
	}

	public List<DeviceGroup> findAllGroup() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM DeviceGroup");
		List<DeviceGroup> result = query.list();

		session.getTransaction().commit();
		session.close();
		return result;
	}

	public boolean isExistByName(Device device) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(Device.class);
		cr.add(Restrictions.like("deviceName", device.getDeviceName()));
		cr.add(Restrictions.neOrIsNotNull("id", device.getId()));
		Long countRow = (Long) cr.setProjection(Projections.rowCount()).uniqueResult();
		session.getTransaction().commit();
		session.close();
		if (countRow == 0) {
			return false;
		} else {
			return true;
		}
	}

	public Long getCountDevice() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String sqlQuery = "select count(*) from device";
		Query query = session.createSQLQuery(sqlQuery);
		Long count = ((Number) query.uniqueResult()).longValue();
		session.getTransaction().commit();
		session.close();
		return count;

	}

}
