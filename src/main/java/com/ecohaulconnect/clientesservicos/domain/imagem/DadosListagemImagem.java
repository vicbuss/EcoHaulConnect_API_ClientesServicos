package com.ecohaulconnect.clientesservicos.domain.imagem;

public record DadosListagemImagem(
        String url
) {
    public DadosListagemImagem(Imagem imagem) {
        this(imagem.getUrl());
    }
}
