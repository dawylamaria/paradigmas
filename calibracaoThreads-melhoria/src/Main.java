import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        String nome_arquivo = "C://Users//dawyl//OneDrive//Documentos//paradigmas//calibracaoThreads-melhoria//calibracao.txt";
        int num_linhas = contar_linhas_arquivo(nome_arquivo);
        CountDownLatch latch = new CountDownLatch(num_linhas);
        double[] resultados = new double[num_linhas];

        ExecutorService executor = Executors.newFixedThreadPool(10);

        try (BufferedReader reader = new BufferedReader(new FileReader(nome_arquivo))) {
            String linha;
            int[] index = {0};

            while ((linha = reader.readLine()) != null) {
                final String linha_final = linha;
                final int posicao = index[0];
                index[0]++;

                executor.submit(() -> {
                    try {
                        double valor = calcular_valor_calibracao(linha_final);
                        resultados[posicao] = valor;
                    } finally {
                        System.out.println("Thread " + Thread.currentThread().getName()
                                + " concluiu a tarefa. Restam " + (latch.getCount() - 1) + " tarefas.");
                        latch.countDown();
                    }
                });
            }

            System.out.println("Aguardando a conclusão de todas as tarefas...");
            latch.await();

            double soma_total = 0;
            for (double valor : resultados) {
                soma_total += valor;
            }

            System.out.println("Todas as tarefas foram concluídas!");
            System.out.println("Soma total dos valores de calibração: " + soma_total);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    public static int contar_linhas_arquivo(String nome_arquivo) {
        int num_linhas = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(nome_arquivo))) {
            while (reader.readLine() != null) {
                num_linhas++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num_linhas;
    }

    public static double calcular_valor_calibracao(String linha) {
        char primeiro_digito = '0';
        char ultimo_digito = '0';

        for (char c : linha.toCharArray()) {
            if (Character.isDigit(c)) {
                if (primeiro_digito == '0') {
                    primeiro_digito = c;
                }
                ultimo_digito = c;
            }
        }

        if (primeiro_digito != '0' && ultimo_digito != '0') {
            return (Character.getNumericValue(primeiro_digito) * 10) + Character.getNumericValue(ultimo_digito);
        }
        return 0;
    }
}
