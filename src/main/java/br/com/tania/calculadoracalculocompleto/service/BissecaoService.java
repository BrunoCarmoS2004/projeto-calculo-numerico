package br.com.tania.calculadoracalculocompleto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.tania.calculadoracalculocompleto.models.bi.CalculoFinalBiDTO;
import br.com.tania.calculadoracalculocompleto.models.bi.InfosCalculoBiDTO;
import br.com.tania.calculadoracalculocompleto.models.bi.IntervaloBi;
import br.com.tania.calculadoracalculocompleto.models.bi.ResultadosBi;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@Service
public class BissecaoService {
	String formula;
	Double precissao;

	public ResponseEntity<?> calculoBissecao(InfosCalculoBiDTO infosCalculoBiDTO) {
		formula = infosCalculoBiDTO.formula();
		precissao = infosCalculoBiDTO.precissao();
		return calcular();
	}

	private Double formula(double x) {
		Expression exp = new ExpressionBuilder(formula)
				.variables("x")
				.build()
				.setVariable("x", x);
		return exp.evaluate();
	}

	private ResponseEntity<?> calcular() {
		List<IntervaloBi> intervalos = calcularIntervalo();
		List<ResultadosBi> resultados = new ArrayList<>();

		for (IntervaloBi intervalo : intervalos) {
			while (true) {
				double x1 = intervalo.getEsquerda();
				double x2 = intervalo.getDireita();
				double m = (x1 + x2) / 2.0;
				double yx1 = formula(x1);
				double ym = formula(m);
				double erro = Math.abs(x2 - x1);

				if (erro <= precissao) {
					resultados.add(new ResultadosBi(m, erro));
					break;
				}
				if (ym == 0.0) {
					resultados.add(new ResultadosBi(m, 0.0));
					break;
				}

				if (yx1 * ym < 0) {
					intervalo.setDireita(m);
				} else {
					intervalo.setEsquerda(m);
				}
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(new CalculoFinalBiDTO(intervalos, resultados));
	}

	private List<IntervaloBi> calcularIntervalo() {
		double iAntigo = -1000.0;
		double valorAntigo = formula(iAntigo);

		List<IntervaloBi> intervalos = new ArrayList<>();
		for (double i = iAntigo + 1.0; i <= 1000.0; i += 1.0) {
			double valorAtual = formula(i);
			if (Math.signum(valorAtual) != Math.signum(valorAntigo)) {
				double esquerda = Math.min(iAntigo, i);
				double direita = Math.max(iAntigo, i);
				intervalos.add(new IntervaloBi(esquerda, direita));
			}

			iAntigo = i;
			valorAntigo = valorAtual;
		}
		return intervalos;
	}
}
