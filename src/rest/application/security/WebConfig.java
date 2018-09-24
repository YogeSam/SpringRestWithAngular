package rest.application.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "rest.controller", "rest.application.security", "view.controller" })
public class WebConfig extends WebMvcConfigurerAdapter {
   
	@Override
   public void configureViewResolvers(ViewResolverRegistry registry) {
      registry.jsp().prefix("/singlepage/").suffix(".jsp");
   }

   @Override
   public void addViewControllers(ViewControllerRegistry registry) {
	   registry.addViewController("/login").setViewName("../login");
   }
}