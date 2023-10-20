package com.ecohaulconnect.clientesservicos.domain.servico;

import com.ecohaulconnect.clientesservicos.domain.endereco.DadosCadastroEndereco;
import com.ecohaulconnect.clientesservicos.domain.item.DadosCadastroItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record DadosCadastroServico(
        @NotNull
        BigDecimal valor,
        @Future
        LocalDateTime dataAgendamento,
        @Valid @NotNull
        DadosCadastroEndereco endereco,
        @NotNull
        Long idCliente,
        @NotNull @Valid
        List<DadosCadastroItem> itens
) {
}
