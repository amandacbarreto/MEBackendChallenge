package br.me.desafio.challengeme.entities;

import br.me.desafio.challengeme.DTO.PedidoItemRespostaDTO;
import br.me.desafio.challengeme.entities.pk.PedidoItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tb_pedido_item")
public class PedidoItem  implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PedidoItemPK id = new PedidoItemPK();

    private Integer quantidade;
    private BigDecimal preco;

    public PedidoItem() {

    }

    public PedidoItem(Pedido pedido, Item item, Integer quantidade, BigDecimal preco) {
        id.setPedido(pedido);
        id.setItem(item);
        this.quantidade = quantidade;
        this.preco = preco;
    }

    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }

    public void setPedido(Pedido pedido){
        id.setPedido(pedido);
    }

    public Item getItem() {
        return id.getItem();
    }

    public void setItem(Item item){
        id.setItem(item);
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getSubTotal() {
        BigDecimal qtd = new BigDecimal(quantidade);
        return preco.multiply(qtd);
    }

    public PedidoItemRespostaDTO convertToPedidoItemRespostaDTO() {
        return new PedidoItemRespostaDTO(getItem().getDescricao(), getItem().getPrecoUnitario(), quantidade );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoItem that = (PedidoItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
