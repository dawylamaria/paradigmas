package br.com.balanca.services;

import br.com.balanca.interfaces.IBalanca;
import br.com.balanca.models.Produto;
import br.com.balanca.exceptions.ProdutoInvalidoException;
import br.com.balanca.exceptions.FalhaExportacaoException;
import br.com.balanca.exceptions.DiretorioNaoEncontradoException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FilizolaSmart implements IBalanca<Produto> {

    @Override
    public void exportar(List<Produto> produtos, String pastaArquivoTxt) throws ProdutoInvalidoException, IOException, DiretorioNaoEncontradoException, FalhaExportacaoException {
        if (produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("A lista de produtos não pode ser nula ou vazia.");
        }

        File directory = new File(pastaArquivoTxt);
        if (!directory.exists()) {
            throw new DiretorioNaoEncontradoException("Diretório não encontrado: " + pastaArquivoTxt);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pastaArquivoTxt + "/CADTXT.TXT"))) {
            for (Produto produto : produtos) {
                if (produto.getCodigo() <= 0) {
                    throw new ProdutoInvalidoException("Código do produto inválido: " + produto.getCodigo());
                }
                String linha = formatarProduto(produto);
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new FalhaExportacaoException("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    private String formatarProduto(Produto produto) {
        String codigo = String.format("%06d", produto.getCodigo());
        String tipo = "9".equals(produto.getTipo()) ? "P" : "U";
        String descricao = String.format("%-22s", produto.getDescricao());
        String preco = String.format("%07d", (int) (produto.getValor() * 100));

        return codigo + tipo + descricao + preco + "000";
    }
}
