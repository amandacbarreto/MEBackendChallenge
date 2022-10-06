package br.me.desafio.challengeme.dto;

import br.me.desafio.challengeme.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class StatusRespostaDTO {

    @JsonProperty("pedido")
    private String id;
    private List<StatusPedido> status = new ArrayList<>();

    public StatusRespostaDTO(String id, List<StatusPedido> status) {
        this.id = id;
        this.status = status;
    }

    public StatusRespostaDTO(List<StatusPedido> status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<StatusPedido> getStatus() {
        return status;
    }

    public void setStatus(List<StatusPedido> status) {
        this.status = status;
    }
}