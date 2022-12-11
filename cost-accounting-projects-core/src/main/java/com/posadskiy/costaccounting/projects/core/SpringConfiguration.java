package com.posadskiy.costaccounting.projects.core;

import com.posadskiy.costaccounting.projects.core.mapper.UserMapper;
import com.posadskiy.costaccounting.projects.core.mapper.UserMapperImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableAutoConfiguration
@EnableMongoRepositories
@ComponentScan
public class SpringConfiguration {

    @Bean
    public UserMapper userMapper() {
        return new UserMapperImpl();
    }
}
