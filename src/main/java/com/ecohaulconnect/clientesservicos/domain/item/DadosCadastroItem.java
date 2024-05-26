package com.ecohaulconnect.clientesservicos.domain.item;

import com.ecohaulconnect.clientesservicos.domain.imagem.DadosDeCadastroImagem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroItem (
        @NotNull
        Tipo tipo,
        Integer alturaEmCm,
        Integer larguraEmCm,
        Integer comprimentoEmCm,
        Integer pesoEmGramas,
        @NotBlank
        String descricao,
        @NotNull @Valid
        List<DadosDeCadastroImagem> imagens
) {}
