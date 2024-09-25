public class Main {
    public static void main(String[] args) {
        Produto produto = new Produto("CERVEJA LAGER HEINEKEN", 6.00, 35.00, "78936683");

        GeradorDeEtiquetas gerador = new GeradorDeEtiquetas(produto);

        String etiquetaZPL = gerador.gerarEtiqueta();
        System.out.println(etiquetaZPL);
    }
}