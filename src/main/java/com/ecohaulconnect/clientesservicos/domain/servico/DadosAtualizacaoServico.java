package com.ecohaulconnect.clientesservicos.domain.servico;

import com.ecohaulconnect.clientesservicos.domain.endereco.DadosAtualizacaoEndereco;
import com.ecohaulconnect.clientesservicos.domain.item.DadosAtualizacaoItem;
import com.ecohaulconnect.clientesservicos.domain.item.DadosCadastroItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record DadosAtualizacaoServico(
       @NotNull @Positive
       BigDecimal valor,
       @NotNull @Future
       LocalDateTime dataAgendamento,
       @NotNull @Valid
       DadosAtualizacaoEndereco endereco,
       @NotNull
       List<DadosCadastroItem> itens
) {
}
