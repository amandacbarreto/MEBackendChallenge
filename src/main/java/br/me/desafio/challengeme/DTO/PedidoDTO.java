package br.me.desafio.challengeme.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

public class PedidoDTO {

    @JsonProperty("pedido")
    private String id;
    private Set<ItemDTO> itens;

    public String getId() {
        return id;
    }

    public Set<ItemDTO> getItens() {
        return itens;
    }

}
