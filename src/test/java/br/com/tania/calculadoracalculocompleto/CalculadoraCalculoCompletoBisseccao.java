package br.com.tania.calculadoracalculocompleto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tania.calculadoracalculocompleto.models.bi.CalculoFinalBiDTO;
import br.com.tania.calculadoracalculocompleto.models.bi.InfosCalculoBiDTO;
import br.com.tania.calculadoracalculocompleto.models.bi.IntervaloBi;
import br.com.tania.calculadoracalculocompleto.models.bi.ResultadosBi;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CalculadoraCalculoCompletoBisseccao {
	String formula;
	Double precissao;

	@Test
	void calculoTesteBase() {
		formula = "x^4-4*x^3-9*x^2+19*x";
		precissao = 0.001;
		calcular(precissao);
	}

	private Double formula(double x) {
		Expression exp = new ExpressionBuilder(formula)
				.variables("x")
				.build()
				.setVariable("x", x);
		Double resultado = exp.evaluate();
		return resultado;
	}

	private CalculoFinalBiDTO calcular(Double precissao) {
		List<IntervaloBi> intervalos = calcularIntervalo();
		List<ResultadosBi> resultados = new ArrayList<>();
		for (IntervaloBi intervalo : intervalos) {
			while (true) {
				Double x1 = intervalo.getDireita();
				Double x2 = intervalo.getEsquerda();
				Double m = (x1 + x2) / 2;
				Double ym = formula(m);
				Double erro = x1 - x2;
				if (erro < precissao) {
					resultados.add(new ResultadosBi(m, erro));
					break;
				}
				if (formula(x1) * ym < 0) {
					intervalo.setEsquerda(m);
				} else {
					intervalo.setDireita(m);
				}
			}
		}
		return new CalculoFinalBiDTO(intervalos, resultados);
	}

	private List<IntervaloBi> calcularIntervalo() {
		Double valorAtual = 0.0;
		Double valorAntigo = 0.0;
		Double iAntigo = 0.0;
		List<IntervaloBi> intervalos = new ArrayList<>();
		for (Double i = 1000.0; i > -1000.0; i--) {
			valorAtual = formula(i);
			if (Math.signum(valorAtual) != Math.signum(valorAntigo)) {
				if (valorAtual < 0) {
					intervalos.add(new IntervaloBi(i, iAntigo));
				} else {
					intervalos.add(new IntervaloBi(iAntigo, i));
				}
			}
			valorAntigo = valorAtual;
			iAntigo = i;
		}
		intervalos.removeFirst();
		for (IntervaloBi intervalo : intervalos) {
			System.out.println("|" + intervalo.getEsquerda() + " " + intervalo.getDireita() + "|\n");
		}
		return intervalos;
	}
}
