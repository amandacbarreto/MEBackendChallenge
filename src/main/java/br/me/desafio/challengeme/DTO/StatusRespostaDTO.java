package br.me.desafio.challengeme.DTO;

import br.me.desafio.challengeme.enums.StatusPedido;
import java.util.Set;

public class StatusRespostaDTO {

    private Long pedido;
    private Set<StatusPedido> status;

    public StatusRespostaDTO(Long pedido, Set<StatusPedido> status) {
        this.pedido = pedido;
        this.status = status;
    }

    public StatusRespostaDTO(Set<StatusPedido> status) {
        this.status = status;
    }

    public Long getPedido() {
        return pedido;
    }

    public void setPedido(Long pedido) {
        this.pedido = pedido;
    }

    public Set<StatusPedido> getStatus() {
        return status;
    }

    public void setStatus(Set<StatusPedido> status) {
        this.status = status;
    }
}