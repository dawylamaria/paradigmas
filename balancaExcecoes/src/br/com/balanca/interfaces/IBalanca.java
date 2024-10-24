package br.com.balanca.interfaces;

import br.com.balanca.exceptions.DiretorioNaoEncontradoException;
import br.com.balanca.exceptions.FalhaExportacaoException;
import br.com.balanca.exceptions.ProdutoInvalidoException;

import java.io.IOException;
import java.util.List;

public interface IBalanca<T> {
    void exportar(List<T> produtos, String pastaArquivoTxt) throws ProdutoInvalidoException, IOException, DiretorioNaoEncontradoException, FalhaExportacaoException;
}
