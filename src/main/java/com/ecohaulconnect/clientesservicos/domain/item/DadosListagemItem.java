package com.ecohaulconnect.clientesservicos.domain.item;

public record DadosListagemItem(
        Long id,
        Tipo tipo,
        Integer alturaEmCm,
        Integer larguraEmCm,
        Integer comprimentoEmCm,
        Integer pesoEmGramas,
        String descricao
) {
    public DadosListagemItem(Item item) {
        this(
                item.getId(),
                item.getTipo(),
                item.getAlturaEmCm(),
                item.getLarguraEmCm(),
                item.getComprimentoEmCm(),
                item.getPesoEmGramas(),
                item.getDescricao()
        );
    }
}
