package br.me.desafio.challengeme.DTO;

import java.math.BigDecimal;

public class ItemDTO {

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



}
