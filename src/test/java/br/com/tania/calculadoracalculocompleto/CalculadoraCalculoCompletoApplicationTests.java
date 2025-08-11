package br.com.tania.calculadoracalculocompleto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tania.calculadoracalculocompleto.models.InfosCalculo;
import br.com.tania.calculadoracalculocompleto.models.Intervalo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CalculadoraCalculoCompletoApplicationTests {

	@Test
	void calculoTesteBase() {
		List<Integer> intervalos;
		System.out.println("""
				Digite uma formula usando
				x -> Variavel.
				^ -> Elevado.
				num -> Numeros.
				+ -> Soma.
				- -> Subtração.
				* -> Vezes
				Exemplo: x^3-(-9)*x+3
				""");
		calcular(new InfosCalculo());
	}

	// Math NÃO É BIBLIOTECA EXTERNA!!! NÃO TEM COMO FAZER ELEVADO EM DOUBLE SEM
	// ISSO, A NÃO SER QUE EU USE UM FOR...
	private Double formula(double x) {
		return Double.valueOf(Math.pow(x, 3) - 9 * x + 3);
	}

	private void calcular(InfosCalculo calculo) {
		// Ex: precissao = 0,001
		Double precissao = calculo.getPrecissao();
		// String formula = calculo.formula();
		Double erro = Double.valueOf(0);

		List<Intervalo> intervalos = calcularIntervalo();
		for (Intervalo intervalo : intervalos) {
			while (true) {
				Double x1 = intervalo.getEsquerda();
				Double x2 = intervalo.getDireita();
				Double y1 = formula(x1);
				Double y2 = formula(x2);
				Double m = (x1 + x2) / 2;
				Double ym = formula(m);

				if (precissao.compareTo(Math.abs(ym)) > 0) {
					System.out.println("Finalizou uma");
					break;
				}
				if (ym < 0) {
					intervalo.setDireita(m);
				}
			}
		}
	}

	private List<Intervalo> calcularIntervalo() {
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
		return intervalos;
	}
}
