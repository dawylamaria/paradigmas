package br.com.balanca.exceptions;

public class ProdutoInvalidoException extends Exception {
    public ProdutoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
