package com.ecohaulconnect.clientesservicos.domain.imagem;

import jakarta.validation.constraints.NotBlank;

public record DadosDeCadastroImagem(
        @NotBlank
        String url
) {
}
