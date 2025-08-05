package br.com.tania.calculadoracalculocompleto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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


	}
	//Math NÃO É BIBLIOTECA EXTERNA!!! NÃO TEM COMO FAZER ELEVADO EM DOUBLE SEM ISSO, A NÃO SER QUE EU USE UM FOR...
	private double formula(double x){
		return Math.pow(x,3)-9*x+3;
	}

	private List<Integer> calcular(List<Integer> intervalo){
		
	}

}
