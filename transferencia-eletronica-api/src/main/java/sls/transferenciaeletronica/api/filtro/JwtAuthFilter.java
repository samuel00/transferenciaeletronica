package sls.transferenciaeletronica.api.filtro;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import sls.transferenciaeletronica.core.seguranca.servico.JwtServico;
import sls.transferenciaeletronica.core.seguranca.servico.UsuarioServico;

@Configuration
public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtServico jwtServico;
    
    private UsuarioServico usuarioServico;
    
    @Autowired
    public JwtAuthFilter( JwtServico jwtService, UsuarioServico usuarioService ) {
        this.jwtServico = jwtService;
        this.usuarioServico = usuarioService;
    }

    @Override
    protected void doFilterInternal( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
    		FilterChain filterChain) throws ServletException, IOException {

        String authorization = httpServletRequest.getHeader("Authorization");

        if( authorization != null && authorization.startsWith("Bearer")){
            String token = authorization.split(" ")[1];
            boolean isValid = jwtServico.tokenValido(token);

            if(isValid){
                String loginUsuario = jwtServico.obterLoginUsuario(token);
                UserDetails userDetail = usuarioServico.loadUserByUsername(loginUsuario);
                UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(userDetail,null, userDetail.getAuthorities());
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(user);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
