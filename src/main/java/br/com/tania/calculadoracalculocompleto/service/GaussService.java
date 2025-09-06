package br.com.tania.calculadoracalculocompleto.service;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.tania.calculadoracalculocompleto.models.gauss.InfosCalculoGauss;
import br.com.tania.calculadoracalculocompleto.models.gauss.ResultadosGauss;

@Service
public class GaussService {
   public ResponseEntity<?> calcularGauss(InfosCalculoGauss infosCalculoGauss) {
      Double[] vector;    
      Double[][] matrix;
      matrix = infosCalculoGauss.matix();

      vector = infosCalculoGauss.vector();
      Double[] solucao = resolver(matrix, vector);
      return ResponseEntity.status(HttpStatus.OK).body(new ResultadosGauss(solucao));
    }

    private Double[] resolver(Double[][] matrixBody, Double[] vetor) {
        int tamanho = matrixBody.length;
        Double[][] matrix = new Double[tamanho][tamanho];
        Double[] resultados = new Double[tamanho];

        for (int linha = 0; linha < tamanho; linha++) {
            for (int coluna = 0; coluna < tamanho; coluna++) {
                matrix[linha][coluna] = matrixBody[linha][coluna];
            }
            resultados[linha] = vetor[linha];
        }

        for (int pivo = 0; pivo < tamanho - 1; pivo++) {
            for (int linha = pivo + 1; linha < tamanho; linha++) {
              Double m = matrix[linha][pivo] / matrix[pivo][pivo];
                for (int coluna = pivo; coluna < tamanho; coluna++) {
                    matrix[linha][coluna] -= m * matrix[pivo][coluna];
                }
                resultados[linha] -= m * resultados[pivo];
            }
        }

        Double[] solucao = new Double[tamanho];
        for (int linha = tamanho - 1; linha >= 0; linha--) {
          Double soma = 0.0;
            for (int coluna = linha + 1; coluna < tamanho; coluna++) {
                soma += matrix[linha][coluna] * solucao[coluna];
            }
            solucao[linha] = (resultados[linha] - soma) / matrix[linha][linha];
        }

        return solucao;
    }
}
