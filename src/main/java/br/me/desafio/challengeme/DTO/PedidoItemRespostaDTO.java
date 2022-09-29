package br.me.desafio.challengeme.DTO;

public class PedidoItemRespostaDTO {

    private String descricao;
    private Double precoUnitario;
    private Integer qtd;

    public String getDescricao() {
        return descricao;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public Integer getQtd() {
        return qtd;
    }

    public PedidoItemRespostaDTO(String descricao, Double precoUnitario, Integer qtd) {
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.qtd = qtd;
    }
}
