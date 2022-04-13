package com.idea.api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.idea.api")
@EntityScan("com.idea.api")
@ComponentScan("com.idea.api")
public class IdeaApiAutoConfiguration {
}
