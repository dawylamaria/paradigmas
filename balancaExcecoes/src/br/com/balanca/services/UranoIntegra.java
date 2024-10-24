package br.com.balanca.services;

import br.com.balanca.exceptions.DiretorioNaoEncontradoException;
import br.com.balanca.interfaces.IBalanca;
import br.com.balanca.models.Produto;
import br.com.balanca.exceptions.ProdutoInvalidoException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UranoIntegra implements IBalanca<Produto> {

    @Override
    public void exportar(List<Produto> produtos, String pastaArquivoTxt) throws ProdutoInvalidoException, IOException, DiretorioNaoEncontradoException {
        if (produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("A lista de produtos não pode ser nula ou vazia.");
        }

        File directory = new File(pastaArquivoTxt);
        if (!directory.exists()) {
            throw new DiretorioNaoEncontradoException("Diretório não encontrado: " + pastaArquivoTxt);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pastaArquivoTxt + "/PRODUTOS.TXT"))) {
            for (Produto produto : produtos) {
                if (produto.getCodigo() <= 0) {
                    throw new ProdutoInvalidoException("Código do produto inválido: " + produto.getCodigo());
                }
                String linha = formatarProduto(produto);
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IOException("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    private String formatarProduto(Produto produto) {
        String codigo = String.format("%06d", produto.getCodigo());
        String flag = "*";
        String tipo = "9".equals(produto.getTipo()) ? "0" : "6";
        String descricao = String.format("%-20s", produto.getDescricao());
        String preco = String.format("%09.2f", produto.getValor()).replace(".", ",");

        return codigo + flag + tipo + descricao + preco + "00000D";
    }
}
