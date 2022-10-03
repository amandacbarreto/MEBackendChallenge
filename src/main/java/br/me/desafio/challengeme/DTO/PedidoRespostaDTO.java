package br.me.desafio.challengeme.DTO;

import java.util.Set;

public class PedidoRespostaDTO {

    private Long pedido;
    private Set<ItemRespostaDTO> itens;

    public PedidoRespostaDTO(Long pedido, Set<ItemRespostaDTO> itens) {
        this.pedido = pedido;
        this.itens = itens;
    }

    public Long getPedido() {
        return pedido;
    }

    public Set<ItemRespostaDTO> getItens() {
        return itens;
    }


}
