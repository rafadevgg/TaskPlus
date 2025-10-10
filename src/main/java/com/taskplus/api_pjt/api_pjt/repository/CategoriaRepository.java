package com.taskplus.api_pjt.api_pjt.repository;

import com.taskplus.api_pjt.api_pjt.model.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Integer> {

    Optional<CategoriaModel> findByCdCategoria(Integer cdCategoria);
}
