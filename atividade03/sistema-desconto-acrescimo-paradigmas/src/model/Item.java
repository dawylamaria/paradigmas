package model;

public class Item {
    private String codigo;
    private String descricao;
    private double valor;
    private double acrescimo = 0;
    private double desconto = 0;

    public Item(String codigo, String descricao, double valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getTotal() {
        return valor + acrescimo - desconto;
    }

    public void aplicarAcrescimo(double acrescimo) {
        this.acrescimo += acrescimo;
    }

    public void aplicarDesconto(double desconto) {
        this.desconto += desconto;
    }

    public void imprimirItem() {
        System.out.println("Código: " + codigo + " | Descrição: " + descricao +
                " | Valor: " + valor + " | Acréscimo: " + acrescimo +
                " | Desconto: " + desconto + " | Total: " + getTotal());
    }
}
