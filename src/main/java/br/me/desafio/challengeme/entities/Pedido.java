package br.me.desafio.challengeme.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty("pedido")
    private String id;

    @OneToMany (mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itens = new ArrayList<>();

    public Pedido() {

    }

    public Pedido(String id) {
        this.id = id;
    }

    public Pedido(String id, List<Item> itens) {
        this.id = id;
        this.itens = itens;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public BigDecimal precoTotal() {
        BigDecimal soma = new BigDecimal(0);
        for (Item i: itens) {
            soma = soma.add(i.getSubTotal());
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
