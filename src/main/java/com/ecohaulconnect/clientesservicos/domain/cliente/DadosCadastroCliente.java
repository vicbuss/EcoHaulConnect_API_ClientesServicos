package com.ecohaulconnect.clientesservicos.domain.cliente;

import com.ecohaulconnect.clientesservicos.domain.endereco.DadosCadastroEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record DadosCadastroCliente(
        @NotBlank
        String nome,
        @NotBlank @Pattern(regexp = "^\\d{10}$")
        String telefone,
        @NotBlank @Email
        String email,
        @NotBlank @Pattern(regexp = "^\\d{11}$")
        String cpf,
        @Past
        LocalDate dataNascimento,
        @NotNull @Valid
        DadosCadastroEndereco endereco
) {
}
