import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        String nomeArquivo = "C://Users//dawyl//OneDrive//Documentos//IHC//calibracao.txt.txt";
        ExecutorService executor = Executors.newFixedThreadPool(10); // 10 threads

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            double somaTotal = 0;

            while ((linha = reader.readLine()) != null) {
                final String linhaFinal = linha;
                Future<Double> futuro = executor.submit(() -> calcularValorCalibracao(linhaFinal));
                somaTotal += futuro.get();
            }

            System.out.println("Soma total dos valores de calibração: " + somaTotal);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    public static double calcularValorCalibracao(String linha) {
        char primeiroDigito = '0';
        char ultimoDigito = '0';

        for (char c : linha.toCharArray()) {
            if (Character.isDigit(c)) {
                if (primeiroDigito == '0') {
                    primeiroDigito = c;
                }
                ultimoDigito = c;
            }
        }

        if (primeiroDigito != '0' && ultimoDigito != '0') {
            return (Character.getNumericValue(primeiroDigito) * 10) + Character.getNumericValue(ultimoDigito);
        }
        return 0;
    }
}
