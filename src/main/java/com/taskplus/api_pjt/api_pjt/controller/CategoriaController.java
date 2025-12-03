package com.taskplus.api_pjt.api_pjt.controller;

import com.taskplus.api_pjt.api_pjt.dto.request.CategoriaRequestDto;
import com.taskplus.api_pjt.api_pjt.model.CategoriaModel;
import com.taskplus.api_pjt.api_pjt.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaModel> criar(@RequestBody @Valid CategoriaRequestDto dto) {
        var categoria = categoriaService.criarCategoria(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaModel>> listarTodas() {
        var categorias = categoriaService.listarTodas();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{cdCategoria}")
    public ResponseEntity<CategoriaModel> buscarPorId(@PathVariable Integer cdCategoria) {
        var categoria = categoriaService.buscarPorId(cdCategoria);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{cdCategoria}")
    public ResponseEntity<CategoriaModel> atualizar(
            @PathVariable Integer cdCategoria,
            @RequestBody @Valid CategoriaRequestDto dto) {
        var categoria = categoriaService.atualizarCategoria(cdCategoria, dto);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{cdCategoria}")
    public ResponseEntity<Void> deletar(@PathVariable Integer cdCategoria) {
        categoriaService.deletarCategoria(cdCategoria);
        return ResponseEntity.noContent().build();
    }
}