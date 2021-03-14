package com.customerking;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CustomerkingApplication implements ServletContextInitializer,WebMvcConfigurer{

	@Autowired
	ServletContext servletContext;
	
	public static void main(String[] args) {
		SpringApplication.run(CustomerkingApplication.class, args);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		 try {
	            final Properties prop = new Properties();
	            prop.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
	            servletContext.setInitParameter("resourceURL", prop.getProperty("resource.url"));
	        }
	        catch (Throwable throwable) {
	            throw new ServletException(throwable);
	        }
		
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void applicationReady() throws Exception {
		final Properties prop = new Properties();
		prop.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
        servletContext.setAttribute("resourceURL", prop.getProperty("resource.url"));
	}

//	@Override
//	public void setServletContext(ServletContext servletContext) {
//		         this.servletContext = servletContext;
//
//		
//	}
}
