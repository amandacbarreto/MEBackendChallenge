package br.me.desafio.challengeme.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PedidoDTO {

    @JsonProperty("pedido")
    private String id;
    private List<ItemDTO> itens = new ArrayList<>();

    public String getId() {
        return id;
    }

    public List<ItemDTO> getItens() {
        return itens;
    }

}
