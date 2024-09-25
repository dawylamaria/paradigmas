public class GeradorDeEtiquetas {
    private Produto produto;

    public GeradorDeEtiquetas(Produto produto) {
        this.produto = produto;
    }

    public String gerarEtiqueta() {
        return "^XA\n" +
                "^CF0,60\n" +
                "^FO50,50^FD" + produto.getDescricao() + "^FS\n" +
                "^CFA,50\n" +
                "^FO50,200^FDLata^FS\n" +
                "^FO400,200^FD" + produto.getPrecoLata() + "^FS\n" +
                "^FO50,280^FDCaixa^FS\n" +
                "^FO400,280^FD" + produto.getPrecoCaixa() + "^FS\n" +
                "^BY5,2,270\n" +
                "^FO100,450^BC^FD" + produto.getCodigoDeBarras() + "^FS\n" +
                "^XZ";
    }
}
