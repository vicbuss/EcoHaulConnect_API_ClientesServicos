package com.ecohaulconnect.clientesservicos.domain.item;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoItem(
        @NotNull
        Long id,
       Tipo tipo,
       Integer alturaEmCm,
       Integer larguraEmCm,
       Integer comprimentoEmCm,
       Integer pesoEmGramas,
       String descricao
) {
}
