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
		//formula = "x^4-4*x^3-9*x^2+19*x";
    //precissao = 0.001;
    formula = infosCalculoBiDTO.formula();
    precissao = infosCalculoBiDTO.precissao();
		return calcular();
	}

	private Double formula(double x) {
		Expression exp = new ExpressionBuilder(formula)
				.variables("x")
				.build()
				.setVariable("x", x);
		Double resultado = exp.evaluate();
		return resultado;
	}

	private ResponseEntity<?> calcular() {
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
				if (ym < 0) {
					intervalo.setEsquerda(m);
				} else {
					intervalo.setDireita(m);
				}
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(new CalculoFinalBiDTO(intervalos, resultados));
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
