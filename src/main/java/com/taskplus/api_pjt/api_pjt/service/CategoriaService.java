package com.taskplus.api_pjt.api_pjt.service;

import com.taskplus.api_pjt.api_pjt.dto.request.CategoriaRequestDto;
import com.taskplus.api_pjt.api_pjt.model.CategoriaModel;
import com.taskplus.api_pjt.api_pjt.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
    public CategoriaModel criarCategoria(CategoriaRequestDto dto) {
        CategoriaModel categoria = new CategoriaModel();
        categoria.setNmCategoria(dto.nmCategoria());
        return categoriaRepository.save(categoria);
    }

    public List<CategoriaModel> listarTodas() {
        return categoriaRepository.findAll();
    }

    public CategoriaModel buscarPorId(Integer cdCategoria) {
        return categoriaRepository.findById(cdCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
    }

    @Transactional
    public CategoriaModel atualizarCategoria(Integer cdCategoria, CategoriaRequestDto dto) {
        CategoriaModel categoria = buscarPorId(cdCategoria);
        categoria.setNmCategoria(dto.nmCategoria());
        return categoriaRepository.save(categoria);
    }

    @Transactional
    public void deletarCategoria(Integer cdCategoria) {
        CategoriaModel categoria = buscarPorId(cdCategoria);
        categoriaRepository.delete(categoria);
    }
}