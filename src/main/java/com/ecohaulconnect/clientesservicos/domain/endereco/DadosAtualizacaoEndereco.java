package com.ecohaulconnect.clientesservicos.domain.endereco;

import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoEndereco(
        String logradouro,
        String bairro,
        @Pattern(regexp = "\\d{8}$")
        String cep,
        String cidade,
        UF uf,
        String complemento,
        String numero
) {
}
