package com.ecohaulconnect.clientesservicos.domain.item;

public record DadosListagemItem(
        Tipo tipo,
        Integer alturaEmCm,
        Integer larguraEmCm,
        Integer comprimentoEmCm,
        Integer pesoEmGramas,
        String descricao
) {
    public DadosListagemItem(Item item) {
        this(
                item.getTipo(),
                item.getAlturaEmCm(),
                item.getLarguraEmCm(),
                item.getComprimentoEmCm(),
                item.getPesoEmGramas(),
                item.getDescricao()
        );
    }
}
