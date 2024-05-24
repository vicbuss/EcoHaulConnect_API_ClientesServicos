package com.ecohaulconnect.clientesservicos.domain.item;

import com.ecohaulconnect.clientesservicos.domain.imagem.DadosDeCadastroImagem;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosAtualizacaoItem(
        @NotNull
        Long id,
        Tipo tipo,
        Integer alturaEmCm,
        Integer larguraEmCm,
        Integer comprimentoEmCm,
        Integer pesoEmGramas,
        String descricao,
        @NotNull
        List<DadosDeCadastroImagem> imagens
) {
}
