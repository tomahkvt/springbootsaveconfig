package org.oa.getmac.config;

import java.util.regex.Pattern;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.oa.getmac.config.RootConfig.WebPackage;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = { "org.oa.getmac" }, excludeFilters = {
		@Filter(type = FilterType.CUSTOM, value = WebPackage.class) })
public class RootConfig {
	public static class WebPackage extends RegexPatternTypeFilter {
		public WebPackage() {
			super(Pattern.compile("org\\.oa\\.getmac\\.web"));
		}
	}
}
