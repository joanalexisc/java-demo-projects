package com.castillo.services.agenda.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.castillo.services")
public class AgendaConfiguration {
	public AgendaConfiguration() {

	}
}
