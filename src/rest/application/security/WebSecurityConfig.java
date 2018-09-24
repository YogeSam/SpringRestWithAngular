package rest.application.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.inMemoryAuthentication()
        .withUser("admin").password("admin123").roles("USER");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
          .antMatchers("/login*").anonymous()
          .antMatchers("/Book/*").permitAll()
          .antMatchers("/Book*").permitAll()
          .anyRequest().authenticated()
          .and()
          .formLogin()
          .loginPage("/login.jsp")
          .loginProcessingUrl("/signin")
          .defaultSuccessUrl("/view/Book")
          .failureUrl("/login.jsp?error=true")
          .and()
          .logout().logoutSuccessUrl("/login.jsp")
          .and()
          .csrf().disable(); // Disable CSRF support;
    }
   
}