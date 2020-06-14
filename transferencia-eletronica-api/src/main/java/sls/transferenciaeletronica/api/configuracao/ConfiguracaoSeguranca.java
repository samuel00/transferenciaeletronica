package sls.transferenciaeletronica.api.configuracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import sls.transferenciaeletronica.api.filtro.JwtAuthFilter;
import sls.transferenciaeletronica.core.seguranca.servico.UsuarioServico;

@EnableWebSecurity
@Configuration
@ComponentScan(basePackages = "sls.transferenciaeletronica")
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioServico usuarioServico;

	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.usuarioServico).passwordEncoder(this.passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
			.antMatchers("/api/transferencia")
				.hasAnyRole("USER", "ADMIN")
			.antMatchers("/api/administrativo/**")
				.hasAnyRole("USER", "ADMIN")
			.antMatchers(HttpMethod.GET, "/api/public/**")
				.permitAll()
			.antMatchers(HttpMethod.POST, "/api/usuario/**")
				.permitAll()
			.anyRequest().authenticated()
			.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.addFilterBefore(this.jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
