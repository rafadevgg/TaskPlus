package com.taskplus.api_pjt.api_pjt.dto.tarefa;

import jakarta.validation.constraints.*;

public record TarefaConcluirDto(@NotBlank(message = "Não é possível concluir uma tarefa sem Nome!")
                                String nmTarefa,
                                @Pattern(regexp = "^(Sim|Não)$", message = "O campo ativo deve ser 'Sim' ou 'Não'")
                                String concluido) {
}
