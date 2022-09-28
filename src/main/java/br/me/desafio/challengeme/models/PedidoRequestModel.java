package br.me.desafio.challengeme.models;

import java.util.List;

public class PedidoRequestModel {

    private List<PedidoItemRequestModel> itens;

    public List<PedidoItemRequestModel> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItemRequestModel> itens) {
        this.itens = itens;
    }
}
