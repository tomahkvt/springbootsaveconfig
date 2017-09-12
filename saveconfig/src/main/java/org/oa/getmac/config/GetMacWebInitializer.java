package org.oa.getmac.config;

import org.oa.getmac.web.WebConfig;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@EnableTransactionManagement
public class GetMacWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override

	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}