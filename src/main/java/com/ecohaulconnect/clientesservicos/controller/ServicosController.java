package com.ecohaulconnect.clientesservicos.controller;

import com.ecohaulconnect.clientesservicos.domain.cliente.ClienteRepository;
import com.ecohaulconnect.clientesservicos.domain.servico.DadosCadastroServico;
import com.ecohaulconnect.clientesservicos.domain.servico.DadosListagemServico;
import com.ecohaulconnect.clientesservicos.domain.servico.Servico;
import com.ecohaulconnect.clientesservicos.domain.servico.ServicoRepository;
import com.ecohaulconnect.clientesservicos.domain.transportador.TransportadorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/servicos")
public class ServicosController {
    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TransportadorRepository transportadorRepository;

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

    @GetMapping
    public ResponseEntity<Page<DadosListagemServico>> listar(@PageableDefault(size = 10, sort = "dataAgendamento") Pageable paginacao,
                                                             @RequestParam(value = "ativo", defaultValue = "true") boolean isAtivo,
                                                             @RequestParam(value = "idCliente", required = false) Long idCliente,
                                                             @RequestParam(value = "idTransportador", required = false) Long idTransportador)
    {
       Page<DadosListagemServico> page;
       Specification<Servico> specification = Specification.where(((root, query, cb) -> cb.equal(root.get("ativo"), isAtivo)));

       if(idCliente != null) {
           var cliente = clienteRepository.getReferenceById(idCliente);
           specification = specification.and(((root, query, cb) -> cb.equal(root.get("cliente"), cliente)));
       }

       if(idTransportador != null) {
           var transportador = transportadorRepository.getReferenceById(idTransportador);
           specification = specification.and(((root, query, cb) -> cb.equal(root.get("transportador"), transportador)));
       }

        page = servicoRepository.findAll(specification, paginacao).map(DadosListagemServico :: new);
        return ResponseEntity.ok(page);
    }
}
