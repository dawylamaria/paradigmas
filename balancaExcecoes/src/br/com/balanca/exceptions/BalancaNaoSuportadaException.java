package br.com.balanca.exceptions;

public class BalancaNaoSuportadaException extends Exception {
    public BalancaNaoSuportadaException(String mensagem) {
        super(mensagem);
    }
}
