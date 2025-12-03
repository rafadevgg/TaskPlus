package com.taskplus.api_pjt.api_pjt.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRequestDto(

        @NotBlank(message = "O nome da categoria é obrigatório!")
        String nmCategoria

) {}