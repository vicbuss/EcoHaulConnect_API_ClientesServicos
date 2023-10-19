package com.ecohaulconnect.clientesservicos.domain.servico;

import com.ecohaulconnect.clientesservicos.domain.cliente.Cliente;
import com.ecohaulconnect.clientesservicos.domain.endereco.Endereco;
import com.ecohaulconnect.clientesservicos.domain.item.Item;
import com.ecohaulconnect.clientesservicos.domain.transportador.Transportador;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne @JoinColumn(name = "id_transportador", nullable = false)
    private Transportador transportador;

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL)
    private List<Item> itens = new ArrayList<Item>();

    @Column(name = "is_active")
    private boolean ativo;

    @Column(name = "dt_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "dt_atualizacao")
    private LocalDateTime dataAtualizacao;
}
