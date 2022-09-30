package com.posadskiy.costaccounting.projects.web;

import com.posadskiy.costaccounting.projects.core.MongoConfiguration;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration("web-configuration")
@ComponentScan({"com.posadskiy.costaccounting.projects.core.controller","com.posadskiy.costaccounting.projects.core.validation","com.posadskiy.costaccounting.projects.core.mapper"})
@Import({com.posadskiy.costaccounting.projects.core.SpringConfiguration.class, MongoConfiguration.class})
public class SpringConfiguration {
	@Bean
	public Queue mainLoginQueue() {
		return new Queue("login");
	}
}
