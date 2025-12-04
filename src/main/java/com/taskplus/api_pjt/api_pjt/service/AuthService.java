package com.taskplus.api_pjt.api_pjt.service;

import com.taskplus.api_pjt.api_pjt.dto.request.UsuarioLoginDto;
import com.taskplus.api_pjt.api_pjt.dto.request.UsuarioRegisterDto;
import com.taskplus.api_pjt.api_pjt.dto.response.AuthResponseDto;
import com.taskplus.api_pjt.api_pjt.model.UsuarioModel;
import com.taskplus.api_pjt.api_pjt.repository.UsuarioRepository;
import com.taskplus.api_pjt.api_pjt.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponseDto registro(UsuarioRegisterDto dto) {

        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("Email já cadastrado!");
        }

        UsuarioModel usuario = new UsuarioModel();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));

        usuario = usuarioRepository.save(usuario);

        String token = jwtService.generateToken(usuario);

        return new AuthResponseDto(token, usuario.getCdUsuario(), usuario.getNome(), usuario.getEmail());

    }

    public AuthResponseDto login(UsuarioLoginDto dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.senha())
        );

        UsuarioModel usuario = usuarioRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        String token = jwtService.generateToken(usuario);

        return new AuthResponseDto(token, usuario.getCdUsuario(), usuario.getNome(), usuario.getEmail());

    }
}