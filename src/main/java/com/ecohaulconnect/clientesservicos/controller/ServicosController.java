package com.ecohaulconnect.clientesservicos.controller;

import com.ecohaulconnect.clientesservicos.domain.cliente.ClienteRepository;
import com.ecohaulconnect.clientesservicos.domain.servico.DadosCadastroServico;
import com.ecohaulconnect.clientesservicos.domain.servico.DadosListagemServico;
import com.ecohaulconnect.clientesservicos.domain.servico.Servico;
import com.ecohaulconnect.clientesservicos.domain.servico.ServicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/servicos")
public class ServicosController {
    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemServico> criarServico(@RequestBody @Valid DadosCadastroServico dados,
                                                             UriComponentsBuilder uriBuilder) {
        var cliente = clienteRepository.getReferenceById(dados.idCliente());

        var servico = new Servico(dados, cliente);

        cliente.addServico(servico);

        servicoRepository.save(servico);

        var uri = uriBuilder.path("/servicos/{id}").buildAndExpand(servico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemServico(servico));
    }
}
