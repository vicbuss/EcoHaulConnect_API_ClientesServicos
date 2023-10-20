package com.ecohaulconnect.clientesservicos.domain.servico;

import ch.qos.logback.core.testUtil.MockInitialContext;
import com.ecohaulconnect.clientesservicos.domain.cliente.Cliente;
import com.ecohaulconnect.clientesservicos.domain.transportador.Transportador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    Page<Servico> findAll(Specification<Servico> specification, Pageable paginacao);
}
