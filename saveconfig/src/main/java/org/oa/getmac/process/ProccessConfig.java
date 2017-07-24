/*
 * Bean of class Thread
 */
package org.oa.getmac.process;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ProccessConfig {
	@Bean
	@Scope("prototype")
	public ThreadGetDataFromDevice treadGetDataFromDevice(){
		return new ThreadGetDataFromDevice();
	}
	
	@Bean
	@Scope("singleton")
	public ControlGetData controlGetData(){
		return new ControlGetData();
	}
}
