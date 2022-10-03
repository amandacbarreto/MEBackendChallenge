package br.me.desafio.challengeme.entities;

import br.me.desafio.challengeme.DTO.StatusRespostaDTO;
import br.me.desafio.challengeme.enums.StatusPedido;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Status  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer itensAprovados;
    private BigDecimal valorAprovado;
    private Pedido pedido;
    private StatusPedido statusInformado;
    private Set<StatusPedido> status  = new HashSet<>();

    public Status(Integer itensAprovados, BigDecimal valorAprovado, Pedido pedido, StatusPedido statusInformado) {
        this.id = id;
        this.itensAprovados = itensAprovados;
        this.valorAprovado = valorAprovado;
        this.pedido = pedido;
        this.statusInformado = statusInformado;
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

    public StatusPedido getStatusInformado() {
        return statusInformado;
    }

    public void setStatusInformado(StatusPedido statusInformado) {
        this.statusInformado = statusInformado;
    }

    public Set<StatusPedido> getStatus() {
        return status;
    }

    public void setStatus(Set<StatusPedido> status) {
        this.status = status;
    }


    public Set<StatusPedido> checkStatus(StatusPedido statusPedido){
        status.clear();
        BigDecimal valorPedido = pedido.precoTotal();
        Integer itensPedido = pedido.itensTotal();
        System.out.println("Itens do Pedido: " + itensPedido);
        System.out.println("Itens aprovados: " + itensAprovados);
        System.out.println("Valor do pedido: " + valorPedido);
        System.out.println("Valor aprovado: " + valorAprovado);
        if (statusPedido.equals(StatusPedido.APROVADO)){
            if(itensPedido != itensAprovados){
                StatusPedido statusTemp = (itensPedido>itensAprovados) ? StatusPedido.APROVADO_QTD_A_MENOR: StatusPedido.APROVADO_QTD_A_MAIOR;
                status.add(statusTemp);
            }
            if(valorPedido.compareTo(valorAprovado) != 0){
                StatusPedido statusTemp = (valorPedido.compareTo(valorAprovado) > 0) ? StatusPedido.APROVADO_VALOR_A_MENOR: StatusPedido.APROVADO_VALOR_A_MAIOR;
                status.add(statusTemp);
            }
            if(status.isEmpty()){
                status.add(StatusPedido.APROVADO);
            }
        } else {
            status.add(StatusPedido.REPROVADO);
        }

        return status;
    }
    public StatusRespostaDTO convertToStatusRespostaDTO() {
        return new StatusRespostaDTO(pedido.getId(), status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status statusInformado = (Status) o;
        return Objects.equals(id, statusInformado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
