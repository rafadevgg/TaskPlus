package com.taskplus.api_pjt.api_pjt.service;

import com.taskplus.api_pjt.api_pjt.dto.request.TarefaRequestDto;
import com.taskplus.api_pjt.api_pjt.model.CategoriaModel;
import com.taskplus.api_pjt.api_pjt.model.TarefaModel;
import com.taskplus.api_pjt.api_pjt.model.enums.Status;
import com.taskplus.api_pjt.api_pjt.repository.CategoriaRepository;
import com.taskplus.api_pjt.api_pjt.repository.TarefaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
    public TarefaModel criarTarefa(TarefaRequestDto dto) {
        TarefaModel tarefa = new TarefaModel();
        tarefa.setNmTarefa(dto.nmTarefa());
        tarefa.setStTarefa(Status.PENDENTE);
        tarefa.setDcTarefa(LocalDateTime.now());
        tarefa.setDlTarefa(dto.dlTarefa());
        tarefa.setConcluido("Não");
        tarefa.setAdiado("Não");

        if (dto.cdCategoria() != null) {
            CategoriaModel categoria = categoriaRepository.findById(dto.cdCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
            tarefa.setCategoria(categoria);
        }

        return tarefaRepository.save(tarefa);
    }

    public List<TarefaModel> listarTodas() {
        return tarefaRepository.findAll();
    }

    public TarefaModel buscarPorId(Integer cdTarefa) {
        return tarefaRepository.findById(cdTarefa)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));
    }

    @Transactional
    public TarefaModel atualizarTarefa(Integer cdTarefa, TarefaRequestDto dto) {
        TarefaModel tarefa = buscarPorId(cdTarefa);
        tarefa.setNmTarefa(dto.nmTarefa());
        tarefa.setDlTarefa(dto.dlTarefa());

        if (dto.cdCategoria() != null) {
            CategoriaModel categoria = categoriaRepository.findById(dto.cdCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
            tarefa.setCategoria(categoria);
        }

        return tarefaRepository.save(tarefa);
    }

    @Transactional
    public void deletarTarefa(Integer cdTarefa) {
        TarefaModel tarefa = buscarPorId(cdTarefa);
        tarefaRepository.delete(tarefa);
    }

    @Transactional
    public TarefaModel concluirTarefa(Integer cdTarefa) {
        TarefaModel tarefa = buscarPorId(cdTarefa);
        tarefa.setConcluido("Sim");
        tarefa.setStTarefa(Status.CONCLUÍDA);
        return tarefaRepository.save(tarefa);
    }

    @Transactional
    public TarefaModel adiarTarefa(Integer cdTarefa) {
        TarefaModel tarefa = buscarPorId(cdTarefa);
        tarefa.setAdiado("Sim");
        return tarefaRepository.save(tarefa);
    }

    @Transactional
    public TarefaModel atribuirCategoria(Integer cdTarefa, Integer cdCategoria) {
        TarefaModel tarefa = buscarPorId(cdTarefa);
        CategoriaModel categoria = categoriaRepository.findById(cdCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
        tarefa.setCategoria(categoria);
        return tarefaRepository.save(tarefa);
    }
}