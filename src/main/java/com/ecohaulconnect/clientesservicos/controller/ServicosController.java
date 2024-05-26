package com.ecohaulconnect.clientesservicos.controller;

import com.ecohaulconnect.clientesservicos.domain.cliente.ClienteRepository;
import com.ecohaulconnect.clientesservicos.domain.endereco.CalculoDeProximidadeService;
import com.ecohaulconnect.clientesservicos.domain.exceptions.ActiveServiceException;
import com.ecohaulconnect.clientesservicos.domain.servico.*;
import com.ecohaulconnect.clientesservicos.domain.transportador.TransportadorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

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

    @GetMapping("/disponiveis")
     public ResponseEntity<Page<DadosListagemServico>> listarDisponiveisPorTransportador (
            @PageableDefault(size = 10, sort = "dataAgendamento") Pageable paginacao,
            @RequestParam(value = "idTransportador") Long idTransportador) {

        var transportador = transportadorRepository.getReferenceById(idTransportador);

        Specification<Servico> specification = Specification.where((root, query, cb) -> cb.and(
                cb.equal(root.get("ativo"), true), cb.isNull(root.get("transportador"))));

        Page<Servico> servicosPage = servicoRepository.findAll(specification, paginacao);

        List<Servico> filteredServicosList = servicosPage.stream()
                .filter(servico -> CalculoDeProximidadeService.filtrarServicosDentroDaArea(servico, transportador))
                .toList();

        PageRequest pageRequest = PageRequest.of(servicosPage.getPageable().getPageNumber(),
                servicosPage.getPageable().getPageSize(), servicosPage.getPageable().getSort());

        var page = new PageImpl<>(filteredServicosList, pageRequest, servicosPage.getTotalElements())
                .map(DadosListagemServico :: new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemServico> detalhar(@PathVariable Long id) {
       var servico = servicoRepository.getReferenceById(id);

       return ResponseEntity.ok(new DadosListagemServico(servico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosListagemServico> atualizar (@PathVariable Long id, @RequestBody DadosAtualizacaoServico dados)
            throws ActiveServiceException {

        var servico = servicoRepository.getReferenceById(id);

        servico.atualizar(dados);

        return ResponseEntity.ok(new DadosListagemServico(servico));
    }

    @PatchMapping("/aceitar/{id}")
    @Transactional
    public ResponseEntity<DadosListagemServico> aceitar (@PathVariable Long id, @RequestBody DadosAtivacaoServico dados)
            throws ActiveServiceException {

        var servico = servicoRepository.getReferenceById(id);

        var transportador = transportadorRepository.getReferenceById(dados.idTransportador());

        servico.aceitar(transportador);

        return ResponseEntity.ok(new DadosListagemServico(servico));
    }

    @PatchMapping("/cancelar/{id}")
    @Transactional
    public ResponseEntity<DadosListagemServico> cancelar (@PathVariable Long id) {
        var servico = servicoRepository.getReferenceById(id);

        servico.cancelar();

        return ResponseEntity.ok(new DadosListagemServico(servico));
    }

    @PatchMapping("/remover/{id}")
    @Transactional
    public ResponseEntity<Void> removerServico (@PathVariable Long id) {
       var servico = servicoRepository.getReferenceById(id);

       servico.desativar();

       return ResponseEntity.noContent().build();
    }
}
