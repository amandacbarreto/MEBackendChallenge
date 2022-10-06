package br.me.desafio.challengeme.entities;

import br.me.desafio.challengeme.dto.StatusRespostaDTO;
import br.me.desafio.challengeme.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Status  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer itensAprovados;
    private BigDecimal valorAprovado;
    private Pedido pedido;
    private StatusPedido statusPedido;

    @JsonProperty("status")
    private List<StatusPedido> statusList;

    public Status(Integer itensAprovados, BigDecimal valorAprovado, Pedido pedido) {
        this.itensAprovados = itensAprovados;
        this.valorAprovado = valorAprovado;
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getItensAprovados() {
        return itensAprovados;
    }

    public void setItensAprovados(Integer itensAprovados) {
        this.itensAprovados = itensAprovados;
    }

    public BigDecimal getValorAprovado() {
        return valorAprovado;
    }

    public void setValorAprovado(BigDecimal valorAprovado) {
        this.valorAprovado = valorAprovado;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public List<StatusPedido> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<StatusPedido> statusList) {
        this.statusList = statusList;
    }


    public void checkStatus(StatusPedido statusPedido){
        List<StatusPedido> list = new ArrayList<>();
        if (statusPedido.equals(StatusPedido.APROVADO)){
            this.checkValor().ifPresent(list::add);
            this.checkItens().ifPresent(list::add);
            if (list.isEmpty()) {
                list.add(StatusPedido.APROVADO);
            }
        } else {
            list.add(StatusPedido.REPROVADO);
        }
        this.setStatusList(list);
    }
    public Optional<StatusPedido> checkValor(){
        int priceComparison = pedido.precoTotal().compareTo(valorAprovado);
        if(priceComparison != 0){
            return (priceComparison > 0) ? Optional.of(StatusPedido.APROVADO_VALOR_A_MENOR): Optional.of(StatusPedido.APROVADO_VALOR_A_MAIOR);
        }
        return Optional.empty();
    }

    public Optional<StatusPedido> checkItens(){
        int itemComparison = pedido.itensTotal().compareTo(itensAprovados);
        if(itemComparison != 0){
            return (itemComparison > 0) ? Optional.of(StatusPedido.APROVADO_QTD_A_MENOR): Optional.of(StatusPedido.APROVADO_QTD_A_MAIOR);
        }
        return Optional.empty();
    }

    public StatusRespostaDTO convertToDTO() {
        return new StatusRespostaDTO(pedido.getId(), statusList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return Objects.equals(id, status.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
