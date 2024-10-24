package br.com.balanca.services;

import br.com.balanca.interfaces.IBalanca;
import br.com.balanca.models.Produto;
import br.com.balanca.exceptions.ProdutoInvalidoException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ToledoMGV6 implements IBalanca<Produto> {

    @Override
    public void exportar(List<Produto> produtos, String pastaArquivoTxt) throws ProdutoInvalidoException, IOException {
        if (produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("A lista de produtos não pode ser nula ou vazia.");
        }

        File directory = new File(pastaArquivoTxt);
        if (!directory.exists()) {
            throw new IOException("Diretório não encontrado: " + pastaArquivoTxt);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pastaArquivoTxt + "/ITENSMGV.TXT"))) {
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
        String dept = "01";
        String tipo = "0";
        String codigo = String.format("%06d", produto.getCodigo());
        String preco = String.format("%06d", (int) (produto.getValor() * 100));
        String descricao = String.format("%-50s", produto.getDescricao());

        return dept + tipo + codigo + preco + "000" + descricao +
                "0000000000|01|                                                                      0000000000000000000000000|0000|0||";
    }
}
