package com.taskplus.api_pjt.api_pjt.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record TarefaRequestDto(

        @NotBlank(message = "O nome da tarefa é obrigatório!")
        String nmTarefa,

        @NotNull(message = "A data limite é obrigatória!")
        @Future(message = "A data limite deve ser futura!")
        LocalDateTime dlTarefa,

        Integer cdCategoria

) {}