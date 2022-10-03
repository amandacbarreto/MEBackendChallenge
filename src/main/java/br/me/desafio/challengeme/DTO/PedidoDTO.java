package br.me.desafio.challengeme.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

public class PedidoDTO {

    @JsonProperty("pedido")
    private Long id;
    private Set<ItemDTO> itens;

    public Long getId() {
        return id;
    }

    public Set<ItemDTO> getItens() {
        return itens;
    }

}
