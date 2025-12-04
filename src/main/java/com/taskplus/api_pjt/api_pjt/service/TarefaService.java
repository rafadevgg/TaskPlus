package com.taskplus.api_pjt.api_pjt.service;

import com.taskplus.api_pjt.api_pjt.dto.request.TarefaRequestDto;
import com.taskplus.api_pjt.api_pjt.model.CategoriaModel;
import com.taskplus.api_pjt.api_pjt.model.TarefaModel;
import com.taskplus.api_pjt.api_pjt.model.UsuarioModel;
import com.taskplus.api_pjt.api_pjt.model.enums.Status;
import com.taskplus.api_pjt.api_pjt.repository.CategoriaRepository;
import com.taskplus.api_pjt.api_pjt.repository.TarefaRepository;
import com.taskplus.api_pjt.api_pjt.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private UsuarioModel getUsuarioAutenticado() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

    }

    @Transactional
    public TarefaModel criarTarefa(TarefaRequestDto dto) {

        UsuarioModel usuario = getUsuarioAutenticado();

        TarefaModel tarefa = new TarefaModel();
        tarefa.setNmTarefa(dto.nmTarefa());
        tarefa.setStTarefa(Status.PENDENTE);
        tarefa.setDcTarefa(LocalDateTime.now());
        tarefa.setDlTarefa(dto.dlTarefa());
        tarefa.setConcluido("Não");
        tarefa.setAdiado("Não");
        tarefa.setUsuario(usuario);

        if (dto.cdCategoria() != null) {
            CategoriaModel categoria = categoriaRepository.findById(dto.cdCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
            tarefa.setCategoria(categoria);
        }

        return tarefaRepository.save(tarefa);

    }

    public List<TarefaModel> listarTodas() {

        UsuarioModel usuario = getUsuarioAutenticado();

        return tarefaRepository.findByUsuarioCdUsuario(usuario.getCdUsuario());

    }

    public TarefaModel buscarPorId(Integer cdTarefa) {

        UsuarioModel usuario = getUsuarioAutenticado();

        return tarefaRepository.findByCdTarefaAndUsuarioCdUsuario(cdTarefa, usuario.getCdUsuario())
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