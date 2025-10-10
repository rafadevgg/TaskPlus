package com.taskplus.api_pjt.api_pjt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Categorias")
@Table(name = "Categorias")
@NoArgsConstructor
@AllArgsConstructor

public class CategoriaModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cdCategoria;

    private String nmCategoria;
}
