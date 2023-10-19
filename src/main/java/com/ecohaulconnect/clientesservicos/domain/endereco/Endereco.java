package com.ecohaulconnect.clientesservicos.domain.endereco;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_enderecos")
@Entity(name = "endereco")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_enderecos")
    @SequenceGenerator(name = "sequence_id_enderecos", sequenceName = "sequence_enderecos")
    @Column(name = "id_endereco")
    private Long id;

    @Column(name = "nm_rua")
    private String logradouro;

    @Column(name = "nm_bairro")
    private String bairro;

    @Column(name = "nr_cep")
    private String cep;

    @Column(name = "nr_numero")
    private String numero;

    @Column(name = "nm_complemento")
    private String complemento;

    @Column(name = "nm_cidade")
    private String cidade;

    @Column(name= "ds_uf")
    @Enumerated(EnumType.STRING)
    private UF uf;

    @Column(name = "nr_lat")
    private Double latitude;

    @Column(name = "nr_long")
    private Double longitude;

    @Column(name = "st_ativo")
    private boolean ativo;

    public Endereco(DadosCadastroEndereco dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
        this.ativo = true;

        // Tirei as coordenadas dos dados de cadastro pq elas não virão da requisição post
        // Deixei as coordenadas abaixo como placeholder até implementarmos a classe que
        // acessa a api de geolocalização
        this.latitude = -23.550164466;
        this.longitude = -46.633664132;
    }

    public Endereco atualizar(DadosAtualizacaoEndereco dados) {
        if(dados.logradouro() != null) {
            this.logradouro = dados.logradouro();
        }
        if(dados.bairro() != null) {
            this.bairro = dados.bairro();
        }
        if(dados.cep() != null) {
            this.cep = dados.cep();
        }
        if(dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
        if(dados.uf() != null) {
            this.uf = dados.uf();
        }
        this.complemento = dados.complemento();
        this.numero = dados.numero();

        // O método de atualizar também precisa chamar a api de geolocalização

        this.latitude = -23.650164466;
        this.longitude = -46.733664132;

        return this;
    }

    public void desativar() {
        this.ativo = false;
    }
}
