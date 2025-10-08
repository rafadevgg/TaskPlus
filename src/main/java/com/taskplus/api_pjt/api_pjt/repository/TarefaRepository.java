package com.taskplus.api_pjt.api_pjt.repository;

import com.taskplus.api_pjt.api_pjt.model.TarefaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TarefaRepository extends JpaRepository<TarefaModel, Integer> {

    Optional<TarefaModel> findByCdTarefa (Integer cdTarefa);
    
}
