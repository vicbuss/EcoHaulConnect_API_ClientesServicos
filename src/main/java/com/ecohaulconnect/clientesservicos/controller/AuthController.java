package com.ecohaulconnect.clientesservicos.controller;

import com.ecohaulconnect.clientesservicos.domain.usuario.DadosAutenticacao;
import com.ecohaulconnect.clientesservicos.domain.usuario.DadosListagemUsuario;
import com.ecohaulconnect.clientesservicos.domain.usuario.Usuario;
import com.ecohaulconnect.clientesservicos.domain.usuario.UsuarioRepository;
import com.ecohaulconnect.clientesservicos.infra.security.DadosTokenJWT;
import com.ecohaulconnect.clientesservicos.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;
    @PostMapping("/login")
    public ResponseEntity<DadosTokenJWT> login(@RequestBody @Valid DadosAutenticacao dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(token);
        var jwt = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<DadosListagemUsuario> cadastrar(
            @RequestBody @Valid DadosAutenticacao dados,
            UriComponentsBuilder uriBuilder
    ) {
        var encodedPwd = encoder.encode(dados.senha());
        var usuario = new Usuario(dados.login(), encodedPwd);

        repository.save(usuario);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemUsuario(usuario));
    }
}
