package com.ecohaulconnect.clientesservicos.domain.transportador;


import com.ecohaulconnect.clientesservicos.domain.endereco.DadosListagemEndereco;

import java.time.LocalDate;

public record DadosListagemTransportador(
        Long id,
        String nome,
        String telefone,
        String email,
        String Cpf,
        String cnh,
        LocalDate dataNascimento,
        String senha,
        Boolean ativo,
        DadosListagemEndereco endereco) {

    public DadosListagemTransportador(com.ecohaulconnect.clientesservicos.domain.transportador.Transportador transportador){
        this(transportador.getId(),
                transportador.getNome(),
                transportador.getTelefone(),
                transportador.getEmail(),
                transportador.getCpf(),
                transportador.getCnh(),
                transportador.getDataNascimento(),
                transportador.getSenha(),
                transportador.getAtivo(),
                new DadosListagemEndereco(transportador.getEndereco()));
    }
}
