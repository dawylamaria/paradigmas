import java.util.Scanner;

public class Main {

    static final int MAX_ITENS = 100;
    static String[] codigos = new String[MAX_ITENS];
    static String[] descricoes = new String[MAX_ITENS];
    static double[] valores = new double[MAX_ITENS];
    static double[] acrescimos = new double[MAX_ITENS];
    static double[] descontos = new double[MAX_ITENS];
    static int quantidadeItens = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();
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
        if (quantidadeItens < MAX_ITENS) {
            System.out.print("Código do item: ");
            codigos[quantidadeItens] = scanner.nextLine();

            System.out.print("Descrição do item: ");
            descricoes[quantidadeItens] = scanner.nextLine();

            System.out.print("Valor do item: ");
            valores[quantidadeItens] = scanner.nextDouble();
            scanner.nextLine();

            acrescimos[quantidadeItens] = 0;
            descontos[quantidadeItens] = 0;

            quantidadeItens++;
        } else {
            System.out.println("Carrinho cheio!");
        }
    }

    private static void aplicarAcrescimoItem(Scanner scanner) {
        System.out.print("Código do item: ");
        String codigo = scanner.nextLine();

        System.out.print("Valor do acréscimo: ");
        double acrescimo = scanner.nextDouble();
        scanner.nextLine();

        for (int i = 0; i < quantidadeItens; i++) {
            if (codigos[i].equals(codigo)) {
                acrescimos[i] += acrescimo;
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
        scanner.nextLine();

        for (int i = 0; i < quantidadeItens; i++) {
            if (codigos[i].equals(codigo)) {
                descontos[i] += desconto;
                return;
            }
        }
        System.out.println("Item não encontrado.");
    }

    private static void aplicarAcrescimoTotal(Scanner scanner) {
        System.out.print("Valor do acréscimo total: ");
        double acrescimoTotal = scanner.nextDouble();
        scanner.nextLine();

        if (quantidadeItens > 0) {
            double acrescimoPorItem = acrescimoTotal / quantidadeItens;
            for (int i = 0; i < quantidadeItens; i++) {
                acrescimos[i] += acrescimoPorItem;
            }
            System.out.println("Acréscimo aplicado igualmente entre os itens.");
        } else {
            System.out.println("Carrinho vazio!");
        }
    }

    private static void aplicarDescontoTotal(Scanner scanner) {
        System.out.print("Valor do desconto total: ");
        double descontoTotal = scanner.nextDouble();
        scanner.nextLine();

        if (quantidadeItens > 0) {
            double descontoPorItem = descontoTotal / quantidadeItens;
            for (int i = 0; i < quantidadeItens; i++) {
                descontos[i] += descontoPorItem;
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

        for (int i = 0; i < quantidadeItens; i++) {
            double totalItem = valores[i] + acrescimos[i] - descontos[i];
            System.out.println("Código: " + codigos[i] + " | Descrição: " + descricoes[i] +
                    " | Valor: " + valores[i] + " | Acréscimo: " + acrescimos[i] +
                    " | Desconto: " + descontos[i] + " | Total: " + totalItem);
            totalDesconto += descontos[i];
            totalAcrescimo += acrescimos[i];
            valorTotal += totalItem;
        }

        System.out.println("Desconto total: " + totalDesconto);
        System.out.println("Acréscimo total: " + totalAcrescimo);
        System.out.println("Valor total: " + valorTotal);
    }
}
