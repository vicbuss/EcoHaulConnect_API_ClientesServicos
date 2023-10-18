package com.ecohaulconnect.clientesservicos.controller;

import com.ecohaulconnect.clientesservicos.domain.cliente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemCliente> cadastrar (@RequestBody @Valid DadosCadastroCliente dados,
                                                           UriComponentsBuilder uriBuillder) {
        var cliente = new Cliente(dados);

        repository.save(cliente);

        var uri = uriBuillder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemCliente(cliente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCliente>> listar (@PageableDefault(size = 10, sort = {"nome"})
                                                              Pageable paginacao, @RequestParam(value = "ativo",
                                                                defaultValue = "true") boolean isAtivo) {

        var page = repository.findAllByAtivo(paginacao, isAtivo).map(DadosListagemCliente::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemCliente> listarPorId (@PathVariable Long id) {
        var cliente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemCliente(cliente));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosListagemCliente> atualizar (@PathVariable Long id,
                                                           @RequestBody @Valid DadosAtualizacaoCliente dados) {
       var cliente = repository.getReferenceById(id);

       cliente.atualizar(dados);

       return ResponseEntity.ok(new DadosListagemCliente(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover (@PathVariable Long id) {
        var cliente = repository.getReferenceById(id);

        cliente.desativar();

        return ResponseEntity.noContent().build();
    }
}

