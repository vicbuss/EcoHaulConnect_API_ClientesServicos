package com.ecohaulconnect.clientesservicos.domain.servico;

import com.ecohaulconnect.clientesservicos.domain.endereco.DadosListagemEndereco;
import com.ecohaulconnect.clientesservicos.domain.item.DadosListagemItem;
import com.ecohaulconnect.clientesservicos.domain.item.Item;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record DadosListagemServico(
        Long id,
        Long idCliente,
        Long idTransportador,
        BigDecimal valor,
        LocalDateTime dataAgendamento,
        String descricao,
        DadosListagemEndereco endereco,
        List<DadosListagemItem> itens,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {

    private static List<DadosListagemItem> dadosListagemItensFromItens (List<Item> itens) {
        var dadosListagemItem = new ArrayList<DadosListagemItem>();
        itens.forEach(item -> dadosListagemItem.add(new DadosListagemItem(item)));

        return dadosListagemItem;
    }
    public DadosListagemServico(Servico servico) {
        this(
                servico.getId(),
                servico.getCliente().getId(),
                servico.getTransportador() != null ? servico.getTransportador().getId() : null,
                servico.getValor(),
                servico.getDataAgendamento(),
                servico.getDescricao(),
                new DadosListagemEndereco(servico.getEndereco()),
                dadosListagemItensFromItens(servico.getItens()),
                servico.getDataCriacao(),
                servico.getDataAtualizacao()
                );
    }
}
