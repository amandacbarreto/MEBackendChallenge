package br.me.desafio.challengeme.DTO;

import br.me.desafio.challengeme.entities.Pedido;

import java.util.List;

public class PedidoDTO {

    private List<PedidoItemDTO> itens;

    public List<PedidoItemDTO> getItens() {
        return itens;
    }

}
