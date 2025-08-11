package br.com.tania.calculadoracalculocompleto;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tania.calculadoracalculocompleto.models.InfosCalculo;
import br.com.tania.calculadoracalculocompleto.models.Intervalo;

@SpringBootTest
public class SalvoCodigoAntigo {
    @Test
    void calculoTesteBase() {
        calcular();
    }

    // Math NÃO É BIBLIOTECA EXTERNA!!! NÃO TEM COMO FAZER ELEVADO EM DOUBLE SEM
    // ISSO, A NÃO SER QUE EU USE UM FOR...
    private Double formula(double x) {
        return Double.valueOf((Math.pow(x, 3)) - 9 * x + 3);
    }

    private void calcular() {
        Double valorAtual = 0.0;
        Double valorAntigo = 0.0;
        Double iAntigo = 0.0;
        List<Intervalo> intervalos = new ArrayList<>();
        for (Double i = 1000.0; i > -1000.0; i--) {
            valorAtual = formula(i);
            if (Math.signum(valorAtual) != Math.signum(valorAntigo)) {
                intervalos.add(new Intervalo(i, iAntigo));
            }
            valorAntigo = valorAtual;
            iAntigo = i;
        }
        intervalos.removeFirst();
        for (Intervalo intervalo : intervalos) {
            System.out.println("|" + intervalo.getEsquerda() + " " + intervalo.getDireita() + "|\n");
        }
    }
}
