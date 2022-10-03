package br.me.desafio.challengeme.DTO;

import br.me.desafio.challengeme.enums.StatusPedido;
import java.util.Set;

public class StatusRespostaDTO {

    private String id;
    private Set<StatusPedido> status;

    public StatusRespostaDTO(String id, Set<StatusPedido> status) {
        this.id = id;
        this.status = status;
    }

    public StatusRespostaDTO(Set<StatusPedido> status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<StatusPedido> getStatus() {
        return status;
    }

    public void setStatus(Set<StatusPedido> status) {
        this.status = status;
    }
}