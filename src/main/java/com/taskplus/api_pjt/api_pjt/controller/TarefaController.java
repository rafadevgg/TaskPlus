package com.taskplus.api_pjt.api_pjt.controller;

import com.taskplus.api_pjt.api_pjt.dto.request.TarefaRequestDto;
import com.taskplus.api_pjt.api_pjt.dto.response.TarefaResponseDto;
import com.taskplus.api_pjt.api_pjt.model.TarefaModel;
import com.taskplus.api_pjt.api_pjt.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaModel> criar(@RequestBody @Valid TarefaRequestDto dto) {
        var tarefa = tarefaService.criarTarefa(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);
    }

    @GetMapping
    public ResponseEntity<List<TarefaModel>> listarTodas() {
        var tarefas = tarefaService.listarTodas();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{cdTarefa}")
    public ResponseEntity<TarefaModel> buscarPorId(@PathVariable Integer cdTarefa) {
        var tarefa = tarefaService.buscarPorId(cdTarefa);
        return ResponseEntity.ok(tarefa);
    }

    @PutMapping("/{cdTarefa}")
    public ResponseEntity<TarefaModel> atualizar(
            @PathVariable Integer cdTarefa,
            @RequestBody @Valid TarefaRequestDto dto) {
        var tarefa = tarefaService.atualizarTarefa(cdTarefa, dto);
        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping("/{cdTarefa}")
    public ResponseEntity<Void> deletar(@PathVariable Integer cdTarefa) {
        tarefaService.deletarTarefa(cdTarefa);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/concluir/{cdTarefa}")
    public ResponseEntity<TarefaModel> concluir(@PathVariable Integer cdTarefa) {
        var tarefa = tarefaService.concluirTarefa(cdTarefa);
        return ResponseEntity.ok(tarefa);
    }

    @PutMapping("/adiar/{cdTarefa}")
    public ResponseEntity<TarefaModel> adiar(@PathVariable Integer cdTarefa) {
        var tarefa = tarefaService.adiarTarefa(cdTarefa);
        return ResponseEntity.ok(tarefa);
    }

    @PutMapping("/{cdTarefa}/categoria/{cdCategoria}")
    public ResponseEntity<TarefaModel> atribuirCategoria(
            @PathVariable Integer cdTarefa,
            @PathVariable Integer cdCategoria) {
        var tarefa = tarefaService.atribuirCategoria(cdTarefa, cdCategoria);
        return ResponseEntity.ok(tarefa);
    }
}