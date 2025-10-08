package com.taskplus.api_pjt.api_pjt.dto.tarefa;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record TarefaAdiarDto(@NotBlank(message = "Não é possível adiar uma tarefa sem Nome!")
                             String nmTarefa,
                             @NotNull(message = "Não é possível adiar uma tarefa, sem uma nova data limite!")
                             @Future(message = "Não é possível adiar uma tarefa, sem uma nova data válida!")
                             LocalDateTime dlTarefa,
                             @Pattern(regexp = "^(Sim|Não)$", message = "O campo ativo deve ser 'Sim' ou 'Não'")
                             String adiado) {
}
