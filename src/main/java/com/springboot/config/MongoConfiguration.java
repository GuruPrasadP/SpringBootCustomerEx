package com.springboot.config;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;
@SuppressWarnings("deprecation")
@Configuration
public class MongoConfiguration {
	
	@Value("${mongo.host}")
	private String mongoHost;
	
	@Value("${mongo.port}")
	private String mongoPort;
	
	@Value("${mongo.db.name}")
	private String mongoDbName;
	
	@Value("${mongo.user}")
	private String mongoUserName;
	
	@Value("${mongo.password}")
	private String mongoPassword;
	
	private static final int CONNECTION_PER_HOST = 5;
	private static final int SOCKET_TIMEOUT = 15000;
	private static final int W_TIMEOUT = 5000;
	private static final int MAX_CONNECT_RETRY = 15 * 1000;
	private static final int CONNECT_TIMEOUT = 2 * 1000;
	
	@Bean
	public Mongo getMongo() throws UnknownHostException {
		return new Mongo(new ServerAddress(mongoHost,Integer.valueOf(mongoPort)), createOptions());
		//return new Mongo(new ServerAddress("localhost",27017), createOptions());
	}

	public MongoOptions createOptions() {
		MongoOptions mongoOptions = new MongoOptions();
		mongoOptions.connectTimeout = CONNECT_TIMEOUT;
		
		//mongoOptions.autoConnectRetry = true;
		//mongoOptions.maxAutoConnectRetryTime = MAX_CONNECT_RETRY; // driver default as of 2.7.0
		mongoOptions.alwaysUseMBeans = true;
		mongoOptions.maxWaitTime = MAX_CONNECT_RETRY;
		
		mongoOptions.wtimeout = W_TIMEOUT;
		mongoOptions.socketTimeout = SOCKET_TIMEOUT; // SO_TIMEOUT - Longest read delay
		mongoOptions.connectionsPerHost = CONNECTION_PER_HOST;
		mongoOptions.w = 1;
		return mongoOptions;
	}

	@Bean
	public MongoTemplate getMongoTemplate(Mongo mongo) {
		return new MongoTemplate(mongo, mongoDbName, new UserCredentials(mongoUserName,mongoPassword));
		//return new MongoTemplate(mongo, "testdb");
	}
}
	

