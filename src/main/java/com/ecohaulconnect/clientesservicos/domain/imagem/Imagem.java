package com.ecohaulconnect.clientesservicos.domain.imagem;

import com.ecohaulconnect.clientesservicos.domain.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tb_imagens")
@Entity(name = "imagem")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_imagens")
    @SequenceGenerator(name = "sequence_id_imagens", sequenceName = "SEQ_TB_IMAGENS", allocationSize = 1)
    @Column(name = "id_imagem")
    private Long id;
    @Column(name = "url_imagem")
    private String url;
    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item item;

    public Imagem(DadosDeCadastroImagem dados, Item item) {
        this.url = dados.url();
        this.item = item;
    }

    public void removerItem() {
        this.item = null;
    }
}
