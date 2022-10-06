package br.me.desafio.challengeme.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ItemDTO {

    @NotNull(message = "Informe a descrição do item.")
    private String descricao;

    @NotNull(message = "Informe o preço unitário do item.")
    @Positive(message = "O preço unitário deve ser maior que zero.")
    private BigDecimal precoUnitario;

    @NotNull(message = "Informe a quantidade do item.")
    @Positive(message = "A quantidade deve ser maior que zero.")
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
