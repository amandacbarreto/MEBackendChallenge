package br.me.desafio.challengeme.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany (mappedBy = "id.pedido")
    private Set<PedidoItem> itens = new HashSet<>();

    public Pedido() {

    }

    public Pedido(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<PedidoItem> getItens() {
        return itens;
    }

    public Double precoTotal() {
        double soma = 0.0;
        for (PedidoItem x: itens) {
            soma += x.getSubTotal();
        }
        return soma;
    }

    public Integer itensTotal() {
        int soma = 0;
        for (PedidoItem x: itens) {
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
