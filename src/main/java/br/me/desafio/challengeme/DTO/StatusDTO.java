package br.me.desafio.challengeme.DTO;

import br.me.desafio.challengeme.enums.StatusPedido;

import java.math.BigDecimal;


public class StatusDTO {

    private Integer itensAprovados;
    private BigDecimal valorAprovado;
    private Long pedido;
    private StatusPedido status;

    public StatusDTO(Integer itensAprovados, BigDecimal valorAprovado, Long pedido, StatusPedido status) {
        this.itensAprovados = itensAprovados;
        this.valorAprovado = valorAprovado;
        this.pedido = pedido;
        this.status = status;
    }

    public Integer getItensAprovados() {
        return itensAprovados;
    }

    public void setItensAprovados(Integer itensAprovados) {
        this.itensAprovados = itensAprovados;
    }

    public BigDecimal getValorAprovado() {
        return valorAprovado;
    }

    public void setValorAprovado(BigDecimal valorAprovado) {
        this.valorAprovado = valorAprovado;
    }

    public Long getPedido() {
        return pedido;
    }

    public void setPedido(Long pedido) {
        this.pedido = pedido;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}
