package com.posadskiy.costaccounting.projects.core;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Slf4j
@Configuration
@ComponentScan
public class MongoConfiguration {
	private final static String DB_NAME = "cost_accounting";

	@Value("${mongo.user.password}")
	private String mongoPassword;
	
	@Value("${environment.mode}")
	private Integer mode;

	@Bean
	public MongoClient mongoClient() {
		if (Environment.Local.mode == mode) return getLocalClient();
		if (Environment.Production.mode == mode) return getProductionClient();
		
		log.error("Undefined Environment mode: " + mode);
		throw new RuntimeException();
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), DB_NAME);
	}
	
	private MongoClient getLocalClient() {
		return new MongoClient();
	}
	
	private MongoClient getProductionClient() {
		MongoClientURI uri = new MongoClientURI(
			"mongodb+srv://nimda:" + mongoPassword + "@cluster0.bvzgv.mongodb.net/" + DB_NAME + "?retryWrites=true&w=majority");
		return new MongoClient(uri);
	}
	
	enum Environment {
		Local(0),
		Production(1);
		
		int mode;
		
		Environment(int mode) {
			this.mode = mode;
		}
	}
}
