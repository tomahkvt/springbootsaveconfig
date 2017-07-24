package org.oa.getmac.sheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.oa.getmac.model.Schedule;
import org.oa.getmac.process.ThreadGetDataFromDevice;
import org.oa.getmac.repository.DeviceRepository;
import org.oa.getmac.repository.ScheduleRepository;
import org.oa.getmac.repository.TaskComplitedRepository;
import org.oa.getmac.repository.TaskGroupHostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ServiceCron {
	@Autowired
	private ScheduleRepository scheduleRepository;
	@Autowired
	private DeviceRepository deviceRepository;
	@Autowired
	private TaskGroupHostRepository taskGroupHostRepository;
	@Autowired 
	private TaskComplitedRepository taskComplitedRepository;
	
	private ThreadGetDataFromDevice threadGetDataFromDevice;
	
	@Autowired
	private ApplicationContext _aApplicationContext;

	@Scheduled(cron = "0 0/1 * * * ?")
	public void demoServiceMethod() {
		Date curentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("u:H:m");
		String curentdateString = dateFormat.format(curentDate);
		
		System.out.println("cron");
		
		List<Schedule> schedules = scheduleRepository.findByStringDate(curentdateString);
		if (schedules.isEmpty() == false) {
			
			
			if ((threadGetDataFromDevice == null) || (threadGetDataFromDevice.getState() == Thread.State.TERMINATED)) {
				threadGetDataFromDevice = (ThreadGetDataFromDevice)
						_aApplicationContext.getBean(ThreadGetDataFromDevice.class);
			}
			
			/*
			 * threadGetDataFromDevice = (ThreadGetDataFromDevice)
			 * _aApplicationContext.getBean(ThreadGetDataFromDevice.class);
			 * threadGetDataFromDevice.setWebSocketSession(this.getSession());
			 * if ((threadGetDataFromDevice == null) ||
			 * (threadGetDataFromDevice.getState() == Thread.State.TERMINATED))
			 * { threadGetDataFromDevice = new ThreadGetDataFromDevice();
			 * threadGetDataFromDevice.setWebSocketSession(session);
			 * this.setStarted(true); }
			 * threadGetDataFromDevice.setmanual(idTask);
			 * threadGetDataFromDevice.start();
			 */

			for (Schedule schedule : schedules) {
				threadGetDataFromDevice.setcron(schedule.getTaskid());
			}
			
			threadGetDataFromDevice.start();
		}

	}
}