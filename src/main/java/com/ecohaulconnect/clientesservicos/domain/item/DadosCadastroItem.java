package com.ecohaulconnect.clientesservicos.domain.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroItem (
        @NotNull
        Tipo tipo,
        Integer alturaEmCm,
        Integer larguraEmCm,
        Integer comprimentoEmCm,
        Integer pesoEmGramas,
        @NotBlank
        String descricao
) {}
