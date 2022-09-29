package br.me.desafio.challengeme.DTO;

import java.util.List;

public class PedidoRespostaDTO {

    private Long pedido;
    private List<PedidoItemRespostaDTO> itens;

    public Long getPedido() {
        return pedido;
    }

    public List<PedidoItemRespostaDTO> getItens() {
        return itens;
    }
}
