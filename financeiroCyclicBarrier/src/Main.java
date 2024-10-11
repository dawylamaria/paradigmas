import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        String despesasFile = "C://Users//dawyl//OneDrive//Documentos//paradigmas//financeiroCyclicBarrier//data//despesas.csv";
        String receitasFile = "C://Users//dawyl//OneDrive//Documentos//paradigmas//financeiroCyclicBarrier//data//receitas.csv";
        String provisoesFile = "C://Users//dawyl//OneDrive//Documentos//paradigmas//financeiroCyclicBarrier//data//provisao.csv";

        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("Todas as planilhas foram processadas.");
        });

        Map<String, Double> totais = new ConcurrentHashMap<>();

        Thread despesasThread = new Thread(new ProcessaFinanceiro(despesasFile, "DESPESA", barrier, totais));
        Thread receitasThread = new Thread(new ProcessaFinanceiro(receitasFile, "RECEITA", barrier, totais));
        Thread provisoesThread = new Thread(new ProcessaFinanceiro(provisoesFile, "PROVISÃO", barrier, totais));

        despesasThread.start();
        receitasThread.start();
        provisoesThread.start();

        try {
            despesasThread.join();
            receitasThread.join();
            provisoesThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double totalReceitas = totais.getOrDefault("RECEITA", 0.0);
        double totalDespesas = totais.getOrDefault("DESPESA", 0.0);
        double totalPrevisoes = totais.getOrDefault("PROVISÃO", 0.0);

        double resultadoFinal = totalReceitas - totalDespesas;

        System.out.println("Total de receitas: " + totalReceitas);
        System.out.println("Total de despesas: " + totalDespesas);
        System.out.println("Total de provisões: " + totalPrevisoes);
        System.out.println("Resultado final (receitas - despesas): " + resultadoFinal);
    }
}

class ProcessaFinanceiro implements Runnable {
    private String filePath;
    private String tipo;
    private CyclicBarrier barrier;
    private Map<String, Double> totais;

    public ProcessaFinanceiro(String filePath, String tipo, CyclicBarrier barrier, Map<String, Double> totais) {
        this.filePath = filePath;
        this.tipo = tipo;
        this.barrier = barrier;
        this.totais = totais;
    }

    @Override
    public void run() {
        try {
            double somaTotal = Files.lines(Paths.get(filePath))
                    .skip(1) // Ignorar a primeira linha (cabeçalho)
                    .filter(linha -> linha.trim().length() > 0)
                    .map(linha -> linha.split(","))
                    .filter(linha -> linha.length > 1 && isNumero(linha[1]))
                    .mapToDouble(linha -> Double.parseDouble(linha[1].replace("\"", "").trim()))
                    .sum();

            totais.put(tipo, somaTotal);

            System.out.println("Total de " + tipo.toLowerCase() + ": " + somaTotal);


            barrier.await();

        } catch (IOException | InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private boolean isNumero(String str) {
        try {
            Double.parseDouble(str.replace("\"", "").trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}