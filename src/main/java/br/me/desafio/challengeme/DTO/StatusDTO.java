package br.me.desafio.challengeme.DTO;

import br.me.desafio.challengeme.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;


public class StatusDTO {

    @NotNull(message = "Informe o Status do Pedido (APROVADO ou REPROVADO).")
    private StatusPedido status;

    @NotNull(message = "Informe a quantidade de itens aprovados.")
    @PositiveOrZero(message = "A quantidade de itens aprovados deve ser maior ou igual a zero.")
    private Integer itensAprovados;

    @NotNull(message = "Informe a quantidade de itens aprovados.")
    @PositiveOrZero(message = "A quantidade de itens aprovados deve ser maior ou igual a zero.")
    private BigDecimal valorAprovado;

    @NotNull(message = "Informe o código do pedido.")
    @JsonProperty("pedido")
    private String id;
    

    public StatusDTO(Integer itensAprovados, BigDecimal valorAprovado, String id, StatusPedido status) {
        this.itensAprovados = itensAprovados;
        this.valorAprovado = valorAprovado;
        this.id = id;
        this.status = status;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public Integer getItensAprovados() {
        return itensAprovados;
    }

    public BigDecimal getValorAprovado() {
        return valorAprovado;
    }

    public String getId() {
        return id;
    }
}
