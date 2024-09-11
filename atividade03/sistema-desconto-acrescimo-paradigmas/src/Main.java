
import model.Carrinho;
import service.GerenciadorDePrecos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Carrinho carrinho = new Carrinho();
        GerenciadorDePrecos gerenciador = new GerenciadorDePrecos(scanner, carrinho);

        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();
            gerenciador.executarAcao(opcao);
        } while (opcao != 6);

        System.out.println("Venda finalizada!");
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
}
