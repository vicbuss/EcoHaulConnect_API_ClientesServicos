package com.ecohaulconnect.clientesservicos.domain.imagem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoImagem(
        @NotNull Long id, @NotBlank String url) {
}
