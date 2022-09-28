package br.me.desafio.challengeme.enums;

public enum StatusPedido {
    APROVADO(1),
    APROVADO_VALOR_A_MENOR(2),
    APROVADO_VALOR_A_MAIOR(3),
    APROVADO_QTD_A_MAIOR(4),
    APROVADO_QTD_A_MENOR(5),
    REPROVADO(6),
    CODIGO_PEDIDO_INVALIDO(7);

    private int code;

    private StatusPedido (int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static StatusPedido valueOf (int code) {
        for (StatusPedido value: StatusPedido.values()){
            if (value.getCode() == code) return value;
        }
        throw new IllegalArgumentException("Código StatusPedido inválido");
    }
}
