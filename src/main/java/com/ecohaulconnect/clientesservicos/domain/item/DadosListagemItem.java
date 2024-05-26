package com.ecohaulconnect.clientesservicos.domain.item;

import com.ecohaulconnect.clientesservicos.domain.imagem.DadosListagemImagem;
import com.ecohaulconnect.clientesservicos.domain.imagem.Imagem;

import java.util.ArrayList;
import java.util.List;

public record DadosListagemItem(
        Tipo tipo,
        Integer alturaEmCm,
        Integer larguraEmCm,
        Integer comprimentoEmCm,
        Integer pesoEmGramas,
        String descricao,
        List<DadosListagemImagem> imagens
) {
    private static List<DadosListagemImagem> dadosListagemImagensFromImagens(List<Imagem> imagens) {
        var dadosListagemImagem = new ArrayList<DadosListagemImagem>();
        imagens.forEach(imagem -> dadosListagemImagem.add(new DadosListagemImagem(imagem)));

        return dadosListagemImagem;
    }
    public DadosListagemItem(Item item) {
        this(
                item.getTipo(),
                item.getAlturaEmCm(),
                item.getLarguraEmCm(),
                item.getComprimentoEmCm(),
                item.getPesoEmGramas(),
                item.getDescricao(),
                dadosListagemImagensFromImagens(item.getImagens())
        );
    }
}
