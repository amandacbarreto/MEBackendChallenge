package br.me.desafio.challengeme.entities;

import br.me.desafio.challengeme.DTO.ItemRespostaDTO;
import br.me.desafio.challengeme.DTO.PedidoRespostaDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @OneToMany (mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Item> itens = new HashSet<>();

    public Pedido() {

    }

    public Pedido(String id) {
        this.id = id;
    }

    public Pedido(String id, Set<Item> itens) {
        this.id = id;
        this.itens = itens;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Item> getItens() {
        return itens;
    }

    public void setItens(Set<Item> itens) {
        this.itens = itens;
    }

    public BigDecimal precoTotal() {
        BigDecimal soma = new BigDecimal(0);
        for (Item x: itens) {
            soma = soma.add(x.getSubTotal());
        }
        return soma;
    }

    public Integer itensTotal() {
        int soma = 0;
        for (Item x: itens) {
            soma += x.getQuantidade();
        }
        return soma;
    }

    public PedidoRespostaDTO convertToPedidoRespostaDTO() {
        Set<ItemRespostaDTO> pedidoItens = new HashSet<>();
        for (Item x: getItens()) {
            pedidoItens.add(x.convertToItemRespostaDTO());
        }
        return new PedidoRespostaDTO(id, pedidoItens);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
