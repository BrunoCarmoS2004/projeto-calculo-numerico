package br.com.tania.calculadoracalculocompleto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tania.calculadoracalculocompleto.models.InfosCalculo;
import br.com.tania.calculadoracalculocompleto.models.Intervalo;
import br.com.tania.calculadoracalculocompleto.models.Resultados;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CalculadoraCalculoCompletoBisseccao {
	String formula;

	@Test
	void calculoTesteBase() {
		formula = "x^3 - 9*x + 3";
		calcular();
	}

	private Double formula(double x) {
		Expression exp = new ExpressionBuilder(formula)
				.variables("x")
				.build()
				.setVariable("x", x);
		Double resultado = exp.evaluate();
		return resultado;
	}

	private void calcular() {
		Double precissao = 0.001;
		List<Intervalo> intervalos = calcularIntervalo();
		List<Resultados> resultados = new ArrayList<>();
		for (Intervalo intervalo : intervalos) {
			while (true) {
				Double x1 = intervalo.getDireita();
				Double x2 = intervalo.getEsquerda();
				Double m = (x1 + x2) / 2;
				Double ym = formula(m);
				if (Math.abs(ym) < precissao) {
					resultados.add(new Resultados(m, ym));
					break;
				}
				if (ym < 0) {
					intervalo.setEsquerda(m);
				} else {
					intervalo.setDireita(m);
				}
			}
		}
		for (Resultados resultados2 : resultados) {
			System.out.println(resultados2.getXm() + " | " + resultados2.getYm());
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
				if (valorAtual < 0) {
					intervalos.add(new Intervalo(i, iAntigo));
				} else {
					intervalos.add(new Intervalo(iAntigo, i));
				}
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
