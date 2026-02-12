package com.aluracursos.challenge_ForoHub.infra.security;

import com.aluracursos.challenge_ForoHub.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            System.out.println("Se llamo al filter!");

            var tokenJWT = recuperarToken(request);
            if (tokenJWT != null){
                var subject = tokenService.getSubject(tokenJWT);
                System.out.println("Subject: " + subject);
                var usuario = repository.findByLogin(subject);

                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("Usuario logueado!!!");
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            System.err.println("Error en SecurityFilter: " + e.getMessage());
            e.printStackTrace();
            throw e; // Re-lanza la excepción para que el contenedor la maneje
        }
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
