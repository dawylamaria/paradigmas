public class Produto {
    private String descricao;
    private double precoLata;
    private double precoCaixa;
    private String codigoDeBarras;

    public Produto(String descricao, double precoLata, double precoCaixa, String codigoDeBarras) {
        this.descricao = descricao.length() > 22 ? descricao.substring(0, 22) : descricao;
        this.precoLata = precoLata;
        this.precoCaixa = precoCaixa;
        this.codigoDeBarras = codigoDeBarras;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPrecoLata() {
        return String.format("R$ %.2f", precoLata);
    }

    public String getPrecoCaixa() {
        return String.format("R$ %.2f", precoCaixa);
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }
}