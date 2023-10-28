package com.ecohaulconnect.clientesservicos.domain.servico;

import com.ecohaulconnect.clientesservicos.domain.cliente.Cliente;
import com.ecohaulconnect.clientesservicos.domain.endereco.Endereco;
import com.ecohaulconnect.clientesservicos.domain.exceptions.ActiveServiceException;
import com.ecohaulconnect.clientesservicos.domain.item.Item;
import com.ecohaulconnect.clientesservicos.domain.transportador.Transportador;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "tb_servicos")
@Entity(name = "Servico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_servicos")
    @SequenceGenerator(name = "sequence_id_servicos", sequenceName = "sq_servicos", allocationSize = 1)
    @Column(name = "id_servico")
    private Long id;

    @Column(name = "nr_valor")
    private BigDecimal valor;

    @Column(name = "dt_agendamento")
    private LocalDateTime dataAgendamento;

    @Column(name = "cd_confirmacao")
    private String codigoConfirmacao;

    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @ManyToOne @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne @JoinColumn(name = "id_transportador")
    private Transportador transportador;

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL)
    private List<Item> itens = new ArrayList<Item>();

    @Column(name = "is_active")
    private boolean ativo;

    @Column(name = "dt_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "dt_atualizacao")
    private LocalDateTime dataAtualizacao;

    public Servico(DadosCadastroServico dados, Cliente cliente) {
        this.valor = dados.valor();
        this.dataAgendamento = dados.dataAgendamento();
        this.endereco = new Endereco(dados.endereco());
        dados.itens().forEach(item -> this.itens.add(new Item(item, this)));
        this.cliente = cliente;

        this.ativo = true;

        var creationDate = LocalDateTime.now();
        this.dataCriacao = creationDate;
        this.dataAtualizacao = creationDate;
    }

    public void atualizar(DadosAtualizacaoServico dados) throws ActiveServiceException {
        if (this.transportador != null) {
            throw new ActiveServiceException("Serviço ativo não pode ser modificado");
        }

        this.valor = dados.valor();

        this.dataAgendamento = dados.dataAgendamento();

        this.endereco = endereco.atualizar(dados.endereco());

        this.dataAtualizacao = LocalDateTime.now();

        // Para possibilitar a inserção de novos itens e a remoção de outros,
        // primeiro removemos os itens antigos da lista
        // para depois adiciionar os itens novos

       this.itens.forEach(Item::removerServico);
       this.itens.clear();

       dados.itens().forEach(item -> this.itens.add(new Item(item, this)));

    }

    public void aceitar(Transportador transportador) throws ActiveServiceException {
        if(this.transportador != null) {
            throw new ActiveServiceException("Este serviço já foi aceito por um transportador");
        }

        this.transportador = transportador;

        this.dataAtualizacao = LocalDateTime.now();
    }

    public void cancelar() {
        this.transportador = null;

        this.dataAtualizacao = LocalDateTime.now();
    }

    public void desativar() {
        this.ativo = false;

        this.dataAtualizacao = LocalDateTime.now();
    }
}
