package br.com.tania.calculadoracalculocompleto;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalculadoraCalculoGauss {
    @Test
    void calcular() {
        Double[][] matriz = {
                { 3.0, 2.0, 4.0, 7.0 },
                { 1.0, 1.0, 2.0, 9.0 },
                { 4.0, 3.0, -2.0, 3.0 },
                { 3.0, 3.0, 8.0, -2.0}
                
        };
        Double[] vetor = { 2.0, 1.4, 4.5, 7.8 };

        double[] solucao = resolver(matriz, vetor);
        System.out.println(Arrays.toString(solucao));
    }

    private double[] resolver(Double[][] matrixBody, Double[] vetor) {
        int tamanho = matrixBody.length;
        double[][] matrix = new double[tamanho][tamanho];
        double[] resultados = new double[tamanho];

        for (int linha = 0; linha < tamanho; linha++) {
            for (int coluna = 0; coluna < tamanho; coluna++) {
                matrix[linha][coluna] = matrixBody[linha][coluna];
            }
            resultados[linha] = vetor[linha];
        }

        for (int pivo = 0; pivo < tamanho - 1; pivo++) {
            for (int linha = pivo + 1; linha < tamanho; linha++) {
                double m = matrix[linha][pivo] / matrix[pivo][pivo];
                for (int coluna = pivo; coluna < tamanho; coluna++) {
                    matrix[linha][coluna] -= m * matrix[pivo][coluna];
                }
                resultados[linha] -= m * resultados[pivo];
            }
        }

        double[] solucao = new double[tamanho];
        for (int linha = tamanho - 1; linha >= 0; linha--) {
            double soma = 0.0;
            for (int coluna = linha + 1; coluna < tamanho; coluna++) {
                soma += matrix[linha][coluna] * solucao[coluna];
            }
            solucao[linha] = (resultados[linha] - soma) / matrix[linha][linha];
        }

        return solucao;
    }
}