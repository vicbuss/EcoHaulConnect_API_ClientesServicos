package com.ecohaulconnect.clientesservicos.domain.transportador;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportadorRepository extends JpaRepository<com.ecohaulconnect.clientesservicos.domain.transportador.Transportador, Long> {
    Page<Transportador> findAllByAtivo(Pageable paginacao, boolean isAtivo);
}
