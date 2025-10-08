package com.taskplus.api_pjt.api_pjt.dto.tarefa;

import com.taskplus.api_pjt.api_pjt.model.enums.Status;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record TarefaDto(@NotBlank(message = "Não é possível salvar uma tarefa sem Nome!")
                        String nmTarefa,
                        @NotBlank(message = "Não é possível salvar uma tarefa sem Status!")
                        Status stTarefa,
                        @NotNull(message = "Favor informe a data de criação da tarefa!")
                        @Future(message = "Informar uma data válida!")
                        LocalDateTime dcTarefa,
                        @NotNull(message = "Favor informe a data de finalização da tarefa!")
                        @Future(message = "Favor uma data válida!")
                        LocalDateTime dlTarefa) {
}
