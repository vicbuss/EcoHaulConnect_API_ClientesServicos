package com.ecohaulconnect.clientesservicos.controller;

import com.ecohaulconnect.clientesservicos.domain.servico.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servicos")
public class ServicosController {
    @Autowired
    private ServicoRepository repository;
}
