package com.ecohaulconnect.clientesservicos.domain.item;

import com.ecohaulconnect.clientesservicos.domain.servico.Servico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tb_itens")
@Entity(name = "Item")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_itens")
    @SequenceGenerator(name = "sequence_id_servicos", sequenceName = "sq_servicos", allocationSize = 1)
    @Column(name = "id_item")
    private Long id;

    @Column(name = "tp_item") @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column(name = "nr_altura")
    private Integer alturaEmCm;

    @Column(name = "nr_largura")
    private Integer larguraEmCm;

    @Column(name = "nr_comprimento")
    private Integer comprimentoEmCm;

    @Column(name = "nr_peso")
    private Integer pesoEmGramas;

    @Column(name = "ds_descricao")
    private String descricao;

    @ManyToOne @JoinColumn(name = "id_servico", nullable = false)
    private Servico servico;
}
