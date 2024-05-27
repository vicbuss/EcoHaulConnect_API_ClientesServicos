package com.ecohaulconnect.clientesservicos.domain.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Page<Cliente> findAllByAtivo(Pageable paginacao, boolean isAtivo);

    Cliente findByEmail(String email);
}
