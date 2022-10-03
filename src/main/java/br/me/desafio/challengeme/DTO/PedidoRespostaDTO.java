package br.me.desafio.challengeme.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class PedidoRespostaDTO {

    @JsonProperty("pedido")
    private String id;
    private Set<ItemRespostaDTO> itens;

    public PedidoRespostaDTO(String id, Set<ItemRespostaDTO> itens) {
        this.id = id;
        this.itens = itens;
    }

    public String getId() {
        return id;
    }

    public Set<ItemRespostaDTO> getItens() {
        return itens;
    }


}
