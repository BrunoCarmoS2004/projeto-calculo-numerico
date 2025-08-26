package br.com.tania.calculadoracalculocompleto.models.bi;

public class IntervaloBi {
    private Double esquerda;
    private Double direita;

    public IntervaloBi(Double esquerda, Double direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    public Double getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Double esquerda) {
        this.esquerda = esquerda;
    }

    public Double getDireita() {
        return direita;
    }

    public void setDireita(Double direita) {
        this.direita = direita;
    }
}
