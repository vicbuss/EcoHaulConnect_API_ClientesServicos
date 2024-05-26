package com.ecohaulconnect.clientesservicos.domain.transportador;

import com.ecohaulconnect.clientesservicos.domain.endereco.DadosCadastroEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record DadosCadastroTransportador(
        @NotBlank
        String nome,
        @NotBlank
        String telefone,
        @NotBlank
        @Email
        String email,
        @NotBlank @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotBlank
        String cnh,
        @Past
        LocalDate dataNascimento,
        @NotBlank
        String senha,
        @NotNull @Positive
        Integer raioDeServicoEmKm,
        @NotNull
        @Valid
        DadosCadastroEndereco endereco ) {
}
