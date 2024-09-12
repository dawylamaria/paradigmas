import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static class Item {
        String codigo;
        String descricao;
        double valor;
        double acrescimo;
        double desconto;

        Item(String codigo, String descricao, double valor) {
            this.codigo = codigo;
            this.descricao = descricao;
            this.valor = valor;
            this.acrescimo = 0;
            this.desconto = 0;
        }

        double getTotal() {
            return valor + acrescimo - desconto;
        }

        void imprimirItem() {
            System.out.println("Código: " + codigo + " | Descrição: " + descricao +
                    " | Valor: " + valor + " | Acréscimo: " + acrescimo +
                    " | Desconto: " + desconto + " | Total: " + getTotal());
        }
    }

    private static final List<Item> carrinho = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            executarAcao(scanner, opcao);
        } while (opcao != 6);

        System.out.println("Venda finalizada!");
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\nMenu:");
        System.out.println("1 - Inserir item ao carrinho");
        System.out.println("2 - Acréscimo de item");
        System.out.println("3 - Desconto de item");
        System.out.println("4 - Acréscimo total");
        System.out.println("5 - Desconto total");
        System.out.println("6 - Finalizar venda");
        System.out.print("Escolha uma opção: ");
    }

    private static void executarAcao(Scanner scanner, int opcao) {
        switch (opcao) {
            case 1 -> inserirItem(scanner);
            case 2 -> aplicarAcrescimoItem(scanner);
            case 3 -> aplicarDescontoItem(scanner);
            case 4 -> aplicarAcrescimoTotal(scanner);
            case 5 -> aplicarDescontoTotal(scanner);
            case 6 -> finalizarVenda();
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void inserirItem(Scanner scanner) {
        System.out.print("Código do item: ");
        String codigo = scanner.nextLine();

        System.out.print("Descrição do item: ");
        String descricao = scanner.nextLine();

        System.out.print("Valor do item: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer

        carrinho.add(new Item(codigo, descricao, valor));
    }

    private static void aplicarAcrescimoItem(Scanner scanner) {
        System.out.print("Código do item: ");
        String codigo = scanner.nextLine();

        System.out.print("Valor do acréscimo: ");
        double acrescimo = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer

        for (Item item : carrinho) {
            if (item.codigo.equals(codigo)) {
                item.acrescimo += acrescimo;
                return;
            }
        }
        System.out.println("Item não encontrado.");
    }

    private static void aplicarDescontoItem(Scanner scanner) {
        System.out.print("Código do item: ");
        String codigo = scanner.nextLine();

        System.out.print("Valor do desconto: ");
        double desconto = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer

        for (Item item : carrinho) {
            if (item.codigo.equals(codigo)) {
                item.desconto += desconto;
                return;
            }
        }
        System.out.println("Item não encontrado.");
    }

    private static void aplicarAcrescimoTotal(Scanner scanner) {
        System.out.print("Valor do acréscimo total: ");
        double acrescimoTotal = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer

        int quantidadeItens = carrinho.size();
        if (quantidadeItens > 0) {
            double acrescimoPorItem = acrescimoTotal / quantidadeItens;
            for (Item item : carrinho) {
                item.acrescimo += acrescimoPorItem;
            }
            System.out.println("Acréscimo aplicado igualmente entre os itens.");
        } else {
            System.out.println("Carrinho vazio!");
        }
    }

    private static void aplicarDescontoTotal(Scanner scanner) {
        System.out.print("Valor do desconto total: ");
        double descontoTotal = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer

        int quantidadeItens = carrinho.size();
        if (quantidadeItens > 0) {
            double descontoPorItem = descontoTotal / quantidadeItens;
            for (Item item : carrinho) {
                item.desconto += descontoPorItem;
            }
            System.out.println("Desconto aplicado igualmente entre os itens.");
        } else {
            System.out.println("Carrinho vazio!");
        }
    }

    private static void finalizarVenda() {
        double totalDesconto = 0;
        double totalAcrescimo = 0;
        double valorTotal = 0;

        for (Item item : carrinho) {
            item.imprimirItem();
            totalDesconto += item.desconto;
            totalAcrescimo += item.acrescimo;
            valorTotal += item.getTotal();
        }

        System.out.println("Desconto total: " + totalDesconto);
        System.out.println("Acréscimo total: " + totalAcrescimo);
        System.out.println("Valor total: " + valorTotal);
    }
}
