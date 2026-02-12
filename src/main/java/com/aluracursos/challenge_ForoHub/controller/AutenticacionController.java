package com.aluracursos.challenge_ForoHub.controller;

import com.aluracursos.challenge_ForoHub.domain.usuario.DatosAutenticacion;
import com.aluracursos.challenge_ForoHub.domain.usuario.Usuario;
import com.aluracursos.challenge_ForoHub.infra.security.DatosTokenJWT;
import com.aluracursos.challenge_ForoHub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos){
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.contrasena());
            var autenticacion = manager.authenticate(authenticationToken);

            var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

            return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
