package com.ecohaulconnect.clientesservicos.domain.cliente;

import com.ecohaulconnect.clientesservicos.domain.endereco.Endereco;
import com.ecohaulconnect.clientesservicos.domain.servico.Servico;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "tb_clientes")
@Entity(name = "cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_clientes")
    @SequenceGenerator(name = "sequence_id_clientes", sequenceName = "sequence_clientes", allocationSize = 1)
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "nm_cliente")
    private String nome;

    @Column(name = "nr_telefone", unique = true)
    private String telefone;

    @Column(name = "nm_email", unique = true)
    private String email;

    @Column(name = "nr_cpf", unique = true)
    private String cpf;

    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "ds_senha")
    private String senha;

    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @Column(name = "st_ativo")
    private boolean ativo;

    @OneToMany(mappedBy = "cliente")
    private List<Servico> servicos = new ArrayList<Servico>();

    public Cliente(DadosCadastroCliente dados) {
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.senha = dados.senha();
        this.dataNascimento = dados.dataNascimento();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void atualizar(DadosAtualizacaoCliente dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if(dados.email() != null) {
            this.email = dados.email();
        }
        if(dados.cpf() != null) {
            this.cpf = dados.cpf();
        }
        if(dados.dataNascimento() != null) {
            this.dataNascimento = dados.dataNascimento();
        }
        if(dados.senha() != null) {
            this.senha = dados.senha();
        }
        if(dados.endereco() != null) {
            this.endereco = this.endereco.atualizar(dados.endereco());
        }
    }

    public void desativar() {
        this.ativo = false;
        this.endereco.desativar();
    }
}
