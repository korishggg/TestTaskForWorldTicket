package net.worldticket.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import net.worldticket.SpringWebservicesTestCaseApplication;

@Configuration
@ComponentScan(basePackageClasses = SpringWebservicesTestCaseApplication.class)
public class ApplicationConfiguration {}