package com.ecohaulconnect.clientesservicos.domain.endereco;

public record DadosListagemEndereco(String logradouro, String bairro, String cep, String numero,
                                    String complemento, String cidade, UF uf, Double latitude,
                                    Double longitude) {
    public DadosListagemEndereco(Endereco endereco) {
        this(endereco.getLogradouro(), endereco.getBairro(), endereco.getCep(), endereco.getNumero(),
                endereco.getComplemento(), endereco.getCidade(), endereco.getUf(), endereco.getLatitude(),
                endereco.getLongitude());
    }
}
