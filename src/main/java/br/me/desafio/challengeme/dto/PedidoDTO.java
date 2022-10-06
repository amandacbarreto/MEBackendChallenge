package br.me.desafio.challengeme.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class PedidoDTO {

    @NotNull(message = "Informe o código do pedido.")
    @JsonProperty("pedido")
    private String id;

    @NotEmpty(message = "Informe ao menos um item.")
    private List< @Valid ItemDTO> itens = new ArrayList<>();

    public String getId() {
        return id;
    }

    public List<ItemDTO> getItens() {
        return itens;
    }

}
