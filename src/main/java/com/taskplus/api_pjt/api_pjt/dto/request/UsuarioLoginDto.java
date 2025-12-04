package com.taskplus.api_pjt.api_pjt.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginDto(

        @NotBlank(message = "O email é obrigatório!")
        @Email(message = "Email inválido!")
        String email,

        @NotBlank(message = "A senha é obrigatória!")
        String senha

) {}