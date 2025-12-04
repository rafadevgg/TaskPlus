package com.taskplus.api_pjt.api_pjt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taskplus.api_pjt.api_pjt.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tarefa")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class TarefaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cdTarefa;

    private String nmTarefa;

    private String concluido = "Não";

    private String adiado = "Não";

    @Enumerated(EnumType.STRING)
    private Status stTarefa;

    @CreationTimestamp
    private LocalDateTime dcTarefa;

    private LocalDateTime dlTarefa;

    @ManyToOne
    @JoinColumn(name = "cd_categoria")
    private CategoriaModel categoria;

    @ManyToOne
    @JoinColumn(name = "cd_usuario", nullable = false)
    @JsonIgnore
    private UsuarioModel usuario;
}