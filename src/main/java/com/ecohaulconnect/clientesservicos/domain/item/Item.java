package com.ecohaulconnect.clientesservicos.domain.item;

import com.ecohaulconnect.clientesservicos.domain.imagem.Imagem;
import com.ecohaulconnect.clientesservicos.domain.servico.Servico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "tb_itens")
@Entity(name = "Item")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_itens")
    @SequenceGenerator(name = "sequence_id_itens", sequenceName = "SEQ_TB_ITENS", allocationSize = 1)
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

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Imagem> imagens = new ArrayList<Imagem>();

    @ManyToOne @JoinColumn(name = "id_servico")
    private Servico servico;

    public Item(DadosCadastroItem dados, Servico servico) {
        this.tipo = dados.tipo();
        this.alturaEmCm = dados.alturaEmCm();
        this.larguraEmCm = dados.larguraEmCm();
        this.comprimentoEmCm = dados.comprimentoEmCm();
        this.pesoEmGramas = dados.pesoEmGramas();
        this.descricao = dados.descricao();
        dados.imagens().forEach(imagem -> this.imagens.add(new Imagem(imagem, this)));
        this.servico = servico;
    }

    public void atualizar(DadosAtualizacaoItem dados) {
       if(dados.tipo() != null) {
           this.tipo = dados.tipo();
       }
       if(dados.alturaEmCm() != null) {
           this.alturaEmCm = dados.alturaEmCm();
       }
       if(dados.larguraEmCm() != null) {
           this.larguraEmCm = dados.larguraEmCm();
       }
       if(dados.comprimentoEmCm() != null) {
           this.comprimentoEmCm = dados.comprimentoEmCm();
       }
       if(dados.pesoEmGramas() != null) {
           this.pesoEmGramas = dados.pesoEmGramas();
       }
       if(dados.descricao() != null) {
           this.descricao = dados.descricao();
       }

       this.imagens.forEach(Imagem::removerItem);
       this.imagens.clear();

       dados.imagens().forEach(imagem -> this.imagens.add(new Imagem(imagem, this)));
    }

    public void removerServico() {
        this.servico = null;
    }
}
