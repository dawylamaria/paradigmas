
import factory.ImpostoFactory;
import factory.TipoImposto;
import imposto.Imposto;
import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        List<Produto> listaDeItens = new ArrayList<>();

        Imposto impostoICMS = ImpostoFactory.criarImposto(TipoImposto.ICMS);
        Produto produtoICMS = new Produto();
        produtoICMS.setCodigo(1);
        produtoICMS.setDescricao("Milho");
        produtoICMS.setValor(100);
        produtoICMS.setIndustrial(false);
        produtoICMS.setImpostoCalculado(impostoICMS.calcular(produtoICMS));
        listaDeItens.add(produtoICMS);

        Imposto impostoPIS = ImpostoFactory.criarImposto(TipoImposto.PIS);
        Produto produtoPIS = new Produto();
        produtoPIS.setCodigo(2);
        produtoPIS.setDescricao("Alface");
        produtoPIS.setValor(200);
        produtoPIS.setIndustrial(false);
        produtoPIS.setImpostoCalculado(impostoPIS.calcular(produtoPIS));
        listaDeItens.add(produtoPIS);

        Imposto impostoIPI = ImpostoFactory.criarImposto(TipoImposto.IPI);
        Produto produtoIndustrial = new Produto();
        produtoIndustrial.setCodigo(3);
        produtoIndustrial.setDescricao("Máquina Industrial");
        produtoIndustrial.setValor(5000);
        produtoIndustrial.setIndustrial(true);
        produtoIndustrial.setImpostoCalculado(impostoIPI.calcular(produtoIndustrial));
        listaDeItens.add(produtoIndustrial);

        Produto produtoNaoIndustrial = new Produto();
        produtoNaoIndustrial.setCodigo(4);
        produtoNaoIndustrial.setDescricao("Bicicleta");
        produtoNaoIndustrial.setValor(800);
        produtoNaoIndustrial.setIndustrial(false);
        produtoNaoIndustrial.setImpostoCalculado(impostoIPI.calcular(produtoNaoIndustrial));
        listaDeItens.add(produtoNaoIndustrial);

        Imposto impostoISS = ImpostoFactory.criarImposto(TipoImposto.ISS);
        Produto servico = new Produto();
        servico.setCodigo(5);
        servico.setDescricao("Consultoria");
        servico.setValor(300);
        servico.setServico(true);
        servico.setImpostoCalculado(impostoISS.calcular(servico));
        listaDeItens.add(servico);

        double totalValorItens = 0;
        double totalImpostoProdutos = 0;
        double totalImpostoServicos = 0;

        System.out.println("Lista de Itens e Impostos:");
        for (Produto item : listaDeItens) {
            System.out.println(item.getDescricao() + " - Valor: R$" + item.getValor() +
                    " - Imposto: R$" + item.getImpostoCalculado());

            totalValorItens += item.getValor();

            if (item.isServico()) {
                totalImpostoServicos += item.getImpostoCalculado();
            } else {
                totalImpostoProdutos += item.getImpostoCalculado();
            }
        }


        System.out.println("\nTotal dos Itens: R$" + totalValorItens);
        System.out.println("Total Imposto sobre Produtos: R$" + totalImpostoProdutos);
        System.out.println("Total Imposto sobre Serviços: R$" + totalImpostoServicos);
    }
}
