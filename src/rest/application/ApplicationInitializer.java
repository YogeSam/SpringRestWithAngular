package rest.application;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import rest.application.security.WebConfig;
import rest.application.security.WebSecurityConfig;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	 @Override
	 protected Class < ? > [] getRootConfigClasses() {
	  return new Class[] {
	    WebSecurityConfig.class
	  };
	 }

	 @Override
	 protected Class < ? > [] getServletConfigClasses() {
		 return new Class[] {
				   WebConfig.class
				  };
	 }

	 @Override
	 protected String[] getServletMappings() {
	  return new String[] {
	   "/"
	  };
	 }
}