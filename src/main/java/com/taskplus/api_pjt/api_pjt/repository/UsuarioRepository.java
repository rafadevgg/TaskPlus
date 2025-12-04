package com.taskplus.api_pjt.api_pjt.repository;

import com.taskplus.api_pjt.api_pjt.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {

    Optional<UsuarioModel> findByEmail(String email);

    boolean existsByEmail(String email);

}