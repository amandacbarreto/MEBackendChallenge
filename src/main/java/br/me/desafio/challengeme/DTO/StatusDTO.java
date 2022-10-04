package br.me.desafio.challengeme.DTO;

import br.me.desafio.challengeme.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;


public class StatusDTO {

    private StatusPedido status;
    private Integer itensAprovados;
    private BigDecimal valorAprovado;
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
