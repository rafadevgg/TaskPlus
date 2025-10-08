package com.taskplus.api_pjt.api_pjt.controller;

import com.taskplus.api_pjt.api_pjt.model.TarefaModel;
import com.taskplus.api_pjt.api_pjt.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tarefas")

public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PutMapping("/concluir/{cdTarefa}")
    public ResponseEntity<TarefaModel> concluir(@PathVariable Integer cdTarefa) {

        var tarefa = tarefaService.concluirTarefa(cdTarefa);
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }

    @PutMapping("/adiar/{cdTarefa}")
    public ResponseEntity<TarefaModel> adiar(@PathVariable Integer cdTarefa) {

        var tarefa = tarefaService.adiarTarefa(cdTarefa);
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }
}
