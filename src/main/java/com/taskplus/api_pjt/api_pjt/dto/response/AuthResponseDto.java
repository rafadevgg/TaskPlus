package com.taskplus.api_pjt.api_pjt.dto.response;

public record AuthResponseDto(

        String token,
        String tipo,
        Integer cdUsuario,
        String nome,
        String email

) {

    public AuthResponseDto(String token,
                           Integer cdUsuario,
                           String nome,
                           String email) {

        this(token, "Bearer", cdUsuario, nome, email);

    }

}