package sls.transferenciaeletronica.api.configuracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import sls.transferenciaeletronica.api.filtro.JwtAuthFilter;
import sls.transferenciaeletronica.core.seguranca.servico.JwtServico;
import sls.transferenciaeletronica.core.seguranca.servico.UsuarioServico;

@EnableWebSecurity
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter {	
	
	@Autowired	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioServico usuarioServico;
	
	@Autowired
	private JwtServico jwtServico;
	
	@Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtServico, usuarioServico);
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.passwordEncoder(this.passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
        .authorizeRequests()
            .antMatchers("/transferencia-eletronica-api/api/transferencia/**")
                .hasAnyRole("USER", "ADMIN")
            .antMatchers("/transferencia-eletronica-api/api/administrativo/**")
                .hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.POST, "/transferencia-eletronica-api/api/public/**")
                .permitAll()
            .antMatchers(HttpMethod.POST, "/transferencia-eletronica-api/api/usuario/**")
                .permitAll()
            .anyRequest().authenticated()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .addFilterBefore( jwtFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
