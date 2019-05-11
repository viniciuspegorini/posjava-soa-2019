package br.edu.utfpr.pb.posjava3auth.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.edu.utfpr.pb.posjava2common.security.JwtConfig;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtConfig jwtConfig;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.csrf().disable()
		.sessionManagement().sessionCreationPolicy(
				SessionCreationPolicy.STATELESS).and()
		.exceptionHandling()
		.authenticationEntryPoint((req, rsp, e) 
				-> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
		.and()
		.addFilter(
				new JwtUsernameAndPasswordAuthenticationFilter(
						authenticationManager(), jwtConfig))
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
		.anyRequest().authenticated();
			
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder(10));
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
