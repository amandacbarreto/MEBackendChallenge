package br.me.desafio.challengeme.DTO;

import java.util.List;
import java.util.Set;

public class PedidoRespostaDTO {

    private Long pedido;
    private Set<PedidoItemRespostaDTO> itens;

    public PedidoRespostaDTO(Long pedido, Set<PedidoItemRespostaDTO> itens) {
        this.pedido = pedido;
        this.itens = itens;
    }

    public Long getPedido() {
        return pedido;
    }

    public Set<PedidoItemRespostaDTO> getItens() {
        return itens;
    }


}
