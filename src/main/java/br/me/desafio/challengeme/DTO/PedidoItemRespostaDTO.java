package br.me.desafio.challengeme.DTO;

import java.math.BigDecimal;

public class PedidoItemRespostaDTO {

    private String descricao;
    private BigDecimal precoUnitario;
    private Integer qtd;

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public Integer getQtd() {
        return qtd;
    }

    public PedidoItemRespostaDTO(String descricao, BigDecimal precoUnitario, Integer qtd) {
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.qtd = qtd;
    }
}
