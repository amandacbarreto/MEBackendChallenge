package br.me.desafio.challengeme.DTO;

import java.util.List;

public class PedidoRespostaDTO {

    private Long pedido;
    private List<PedidoItemRespostaDTO> itens;

    public PedidoRespostaDTO(Long pedido, List<PedidoItemRespostaDTO> itens) {
        this.pedido = pedido;
        this.itens = itens;
    }

    public Long getPedido() {
        return pedido;
    }

    public List<PedidoItemRespostaDTO> getItens() {
        return itens;
    }


}
