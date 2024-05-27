package com.ecohaulconnect.clientesservicos.domain.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosListagemEmail(
        @Email
        @NotBlank
        String email
) {
}
