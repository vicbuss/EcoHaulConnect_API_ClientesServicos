package com.ecohaulconnect.clientesservicos.domain.cliente;

import com.ecohaulconnect.clientesservicos.domain.endereco.DadosListagemEndereco;

import java.time.LocalDate;

public record DadosListagemCliente(Long id, String nome, String telefone, String email, String cpf,
                                   LocalDate dataNascimento, DadosListagemEndereco endereco, boolean ativo) {
    public DadosListagemCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getEmail(), cliente.getCpf(),
                cliente.getDataNascimento(), new DadosListagemEndereco(cliente.getEndereco()), cliente.isAtivo());
    }
}
