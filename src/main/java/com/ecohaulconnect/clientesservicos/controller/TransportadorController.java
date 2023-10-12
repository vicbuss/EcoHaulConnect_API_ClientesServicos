package com.ecohaulconnect.clientesservicos.controller;

import com.ecohaulconnect.clientesservicos.domain.transportador.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transportadores")
public class TransportadorController {

    @Autowired
    private TransportadorRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroTransportador dadosTransportador) {
        repository.save(new Transportador(dadosTransportador));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemTransportador> listarPorId (@PathVariable Long id) {
        var transportador = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemTransportador(transportador));
    }

    @GetMapping("/listartodos")
    public Page<DadosListagemTransportador> listarTodos(Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemTransportador::new);

    }

    @GetMapping("/listarativos")
    public Page<DadosListagemTransportador> listarAtivos(Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemTransportador::new);

    }

    @GetMapping("/listarinativos")
    public Page<DadosListagemTransportador> listarInativos(Pageable paginacao){
        return repository.findAllByAtivoFalse(paginacao).map(DadosListagemTransportador::new);

    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoTransportador dados){
        var transportador = repository.getReferenceById(dados.id());

        transportador.atualizarInformacoes(dados);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){

        var transportador = repository.getReferenceById(id);

        transportador.excluir();
    }
}