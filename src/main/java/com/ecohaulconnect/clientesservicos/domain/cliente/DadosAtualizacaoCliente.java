package com.ecohaulconnect.clientesservicos.domain.cliente;

import com.ecohaulconnect.clientesservicos.domain.endereco.DadosAtualizacaoEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record DadosAtualizacaoCliente(
        String nome,
        String telefone,
        @Email
        String email,
        @Pattern(regexp = "\\d{11}$")
        String cpf,
        @Past
        LocalDate dataNascimento,
        String senha,
        @Valid
        DadosAtualizacaoEndereco endereco
) {
}
