package br.me.desafio.challengeme.DTO;

import java.math.BigDecimal;

public class ItemDTO {

    private String descricao;
    private BigDecimal precoUnitario;
    private Integer quantidade;

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }



}
