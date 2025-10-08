package com.taskplus.api_pjt.api_pjt.service;

import com.taskplus.api_pjt.api_pjt.model.TarefaModel;
import com.taskplus.api_pjt.api_pjt.repository.TarefaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public TarefaModel buscarPorId(Integer cdTarefa) {
        return tarefaRepository.findById(cdTarefa)
                .orElseThrow(() -> new RuntimeException("A tarefa não foi encontrada!"));
    }

    @Transactional
    public TarefaModel concluirTarefa(Integer cdTarefa) {
        TarefaModel tarefa = buscarPorId(cdTarefa);
        tarefa.setConcluido("Sim");

        return tarefa;
    }

    @Transactional
    public TarefaModel adiarTarefa(Integer cdTarefa) {
        TarefaModel tarefa = buscarPorId(cdTarefa);
        tarefa.setAdiado("Sim");

        return tarefa;
    }
}
