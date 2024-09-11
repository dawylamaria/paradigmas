package service;

import model.Carrinho;
import model.Item;

import java.util.Scanner;

public class GerenciadorDePrecos {
    private final Scanner scanner;
    private final Carrinho carrinho;

    public GerenciadorDePrecos(Scanner scanner, Carrinho carrinho) {
        this.scanner = scanner;
        this.carrinho = carrinho;
    }

    public void executarAcao(int opcao) {
        switch (opcao) {
            case 1 -> inserirItem();
            case 2 -> aplicarAcrescimoItem();
            case 3 -> aplicarDescontoItem();
            case 4 -> aplicarAcrescimoTotal();
            case 5 -> aplicarDescontoTotal();
            case 6 -> finalizarVenda();
            default -> System.out.println("Opção inválida!");
        }
    }

    private void inserirItem() {
        System.out.print("Código do item: ");
        String codigo = scanner.nextLine();

        System.out.print("Descrição do item: ");
        String descricao = scanner.nextLine();

        System.out.print("Valor do item: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        carrinho.adicionarItem(new Item(codigo, descricao, valor));
    }

    private void aplicarAcrescimoItem() {
        System.out.print("Código do item: ");
        String codigo = scanner.nextLine();

        System.out.print("Valor do acréscimo: ");
        double acrescimo = scanner.nextDouble();
        scanner.nextLine(); 

        Item item = carrinho.buscarItemPorCodigo(codigo);
        if (item != null) {
            item.aplicarAcrescimo(acrescimo);
        } else {
            System.out.println("Item não encontrado.");
        }
    }

    private void aplicarDescontoItem() {
        System.out.print("Código do item: ");
        String codigo = scanner.nextLine();

        System.out.print("Valor do desconto: ");
        double desconto = scanner.nextDouble();
        scanner.nextLine();

        Item item = carrinho.buscarItemPorCodigo(codigo);
        if (item != null) {
            item.aplicarDesconto(desconto);
        } else {
            System.out.println("Item não encontrado.");
        }
    }

    private void aplicarAcrescimoTotal() {
        System.out.print("Valor do acréscimo total: ");
        double acrescimoTotal = scanner.nextDouble();
        scanner.nextLine();

        int quantidadeItens = carrinho.getItens().size();
        if (quantidadeItens > 0) {
            double acrescimoPorItem = acrescimoTotal / quantidadeItens;
            for (Item item : carrinho.getItens()) {
                item.aplicarAcrescimo(acrescimoPorItem);
            }
            System.out.println("Acréscimo aplicado igualmente entre os itens.");
        } else {
            System.out.println("Carrinho vazio!");
        }
    }

    private void aplicarDescontoTotal() {
        System.out.print("Valor do desconto total: ");
        double descontoTotal = scanner.nextDouble();
        scanner.nextLine();

        int quantidadeItens = carrinho.getItens().size();
        if (quantidadeItens > 0) {
            double descontoPorItem = descontoTotal / quantidadeItens;
            for (Item item : carrinho.getItens()) {
                item.aplicarDesconto(descontoPorItem);
            }
            System.out.println("Desconto aplicado igualmente entre os itens.");
        } else {
            System.out.println("Carrinho vazio!");
        }
    }

    private void finalizarVenda() {
        carrinho.imprimirItens();
        double total = carrinho.getItens().stream().mapToDouble(Item::getTotal).sum();
        System.out.println("Valor total da venda: " + total);
    }
}
