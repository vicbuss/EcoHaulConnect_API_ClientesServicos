package com.ecohaulconnect.clientesservicos.controller;

import com.ecohaulconnect.clientesservicos.domain.transportador.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("transportadores")
public class TransportadorController {

    @Autowired
    private TransportadorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemTransportador> cadastrar(@RequestBody @Valid DadosCadastroTransportador dadosTransportador,
                                                                UriComponentsBuilder uriBuilder) {
        var transportador = new Transportador(dadosTransportador);

        repository.save(transportador);

        var uri = uriBuilder.path("/transportadores/{id}").buildAndExpand(transportador.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemTransportador(transportador));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemTransportador> listarPorId (@PathVariable Long id) {
        var transportador = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemTransportador(transportador));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTransportador>> listarTodos(@PageableDefault(size = 10, sort = {"nome"})
                                                              Pageable paginacao, @RequestParam(value = "ativo",
                                                                defaultValue = "true") boolean isAtivo){

        var page = repository.findAllByAtivo(paginacao, isAtivo).map(DadosListagemTransportador::new);
        return ResponseEntity.ok(page);

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosListagemTransportador> atualizar(@PathVariable Long id,
                                                                @RequestBody @Valid DadosAtualizacaoTransportador dados){
        var transportador = repository.getReferenceById(id);

        transportador.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosListagemTransportador(transportador));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){

        var transportador = repository.getReferenceById(id);

        transportador.excluir();
    }
}