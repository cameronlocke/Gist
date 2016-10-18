package com.thegist;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

@SpringBootApplication
@RestController
public class OverloadApplication {

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	@RequestMapping("/resource")
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Welcome to Overload World");
		return model;
	}

	public static void main(String[] args) {
		SpringApplication.run(OverloadApplication.class, args);
	}

	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
		        http
		            .authorizeRequests()
		                .antMatchers("/resources/**").permitAll() 
		                .anyRequest().authenticated()
		                .and()
		            .formLogin()
		                .loginPage("/login")
		                .permitAll()
		                .and()
		            .logout()                                    
		                .permitAll();
		    }
			
/*			// @formatter:off
			
			http
				.httpBasic().and()
				.logout().and()
				.authorizeRequests()
					.antMatchers("*", "/login", "/login.html", "/signup", "/registration.html", "/").permitAll().anyRequest()
					.authenticated().and()
					.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
			
//			.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
//			.csrf().csrfTokenRepository(csrfTokenRepository());
			// @formatter:on
		}
		*/
	
	}
			 
	
	public class CsrfHeaderFilter extends OncePerRequestFilter {
		  @Override
		  protected void doFilterInternal(HttpServletRequest request,
		      HttpServletResponse response, FilterChain filterChain)
		      throws ServletException, IOException {
		    CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
		        .getName());
		    if (csrf != null) {
		      Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
		      String token = csrf.getToken();
		      if (cookie==null || token!=null && !token.equals(cookie.getValue())) {
		        cookie = new Cookie("XSRF-TOKEN", token);
		        //TODO This should be set to set to context path.
		        cookie.setPath("/");
		        response.addCookie(cookie);
		      }
		    }
		    filterChain.doFilter(request, response);
		  }
		}

}

