package rest.application.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "rest.controller", "rest.application.security", "view.controller" })
public class WebConfig extends WebMvcConfigurerAdapter {
   
   //resolve jsps
	@Override
   public void configureViewResolvers(ViewResolverRegistry registry) {
      registry.jsp().prefix("/singlepage/").suffix(".jsp");
   }
	
	//resolve htmls 
	@Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/singlepage/**").addResourceLocations("/singlepage/");
	}	

   @Override
   public void addViewControllers(ViewControllerRegistry registry) {
	   registry.addViewController("/login").setViewName("../login");
   }
}