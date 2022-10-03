package br.me.desafio.challengeme.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class PedidoRespostaDTO {

    @JsonProperty("pedido")
    private Long id;
    private Set<ItemRespostaDTO> itens;

    public PedidoRespostaDTO(Long id, Set<ItemRespostaDTO> itens) {
        this.id = id;
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public Set<ItemRespostaDTO> getItens() {
        return itens;
    }


}
