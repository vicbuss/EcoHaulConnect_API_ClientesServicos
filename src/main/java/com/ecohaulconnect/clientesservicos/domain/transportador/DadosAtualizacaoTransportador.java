package com.ecohaulconnect.clientesservicos.domain.transportador;

import com.ecohaulconnect.clientesservicos.domain.endereco.DadosAtualizacaoEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record DadosAtualizacaoTransportador(
        String nome,
        String telefone,
        String email,
        String cpf,
        String cnh,
        LocalDate dataNascimento,
        String senha,
        String status,
        Integer raioDeServicoEmKm,
        DadosAtualizacaoEndereco dadosAtualizacaoEndereco) {
}
