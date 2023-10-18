package com.ecohaulconnect.clientesservicos.domain.transportador;

import com.ecohaulconnect.clientesservicos.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "tb_transportadores")
@Entity(name = "Transportador")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transportador {

    @Id
    @SequenceGenerator(name="transportador",sequenceName="sequence_transportadores",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence_id_transportador")
    @Column(name = "id_transportador")
    private Long id;

    @Column(name = "nm_transportador")
    private String nome;

    @Column(name = "nr_telefone", unique = true)
    private String telefone;

    @Column(name = "nm_email", unique = true)
    private String email;

    @Column(name = "nr_cpf", unique = true)
    private String cpf;

    @Column(name = "nr_cnh", unique = true)
    private String cnh;

    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "ds_senha")
    private String senha;

    @Column(name = "st_ativo")
    private Boolean ativo;

    @ManyToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    public Transportador(DadosCadastroTransportador dadosTransportador) {
        this.ativo = true;
        this.nome = dadosTransportador.nome();
        this.telefone = dadosTransportador.telefone();
        this.email = dadosTransportador.email();
        this.cpf = dadosTransportador.cpf();
        this.cnh = dadosTransportador.cnh();
        this.dataNascimento = dadosTransportador.dataNascimento();
        this.senha = dadosTransportador.senha();
        this.endereco = new Endereco(dadosTransportador.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoTransportador dados) {

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.cpf() != null) {
            this.cpf = dados.cpf();
        }
        if (dados.cnh() != null) {
            this.cnh = dados.cnh();
        }
        if (dados.dataNascimento() != null) {
            this.dataNascimento = dados.dataNascimento();
        }
        if (dados.senha() != null) {
            this.senha = dados.senha();
        }
        if (dados.dadosAtualizacaoEndereco() != null) {
            this.endereco.atualizar(dados.dadosAtualizacaoEndereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}