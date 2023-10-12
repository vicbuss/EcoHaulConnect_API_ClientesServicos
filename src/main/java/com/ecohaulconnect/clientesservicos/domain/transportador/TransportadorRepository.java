package com.ecohaulconnect.clientesservicos.domain.transportador;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportadorRepository extends JpaRepository<com.ecohaulconnect.clientesservicos.domain.transportador.Transportador, Long> {
    Page<com.ecohaulconnect.clientesservicos.domain.transportador.Transportador> findAllByAtivoTrue(Pageable paginacao);

    Page<com.ecohaulconnect.clientesservicos.domain.transportador.Transportador> findAllByAtivoFalse(Pageable paginacao);
}
