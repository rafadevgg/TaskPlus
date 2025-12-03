package com.taskplus.api_pjt.api_pjt.dto.response;

import com.taskplus.api_pjt.api_pjt.model.enums.Status;
import java.time.LocalDateTime;

public record TarefaResponseDto(

        Integer cdTarefa,
        String nmTarefa,
        Status stTarefa,
        String concluido,
        String adiado,
        LocalDateTime dcTarefa,
        LocalDateTime dlTarefa,
        String nmCategoria

) {}