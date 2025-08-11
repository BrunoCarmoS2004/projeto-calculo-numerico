package br.com.tania.calculadoracalculocompleto.models;

public class Resultados {
    private Double xm;
    private Double ym;

    public Resultados(Double xm, Double ym) {
        this.xm = xm;
        this.ym = ym;
    }

    public Resultados() {
    }

    public Double getXm() {
        return xm;
    }

    public void setXm(Double xm) {
        this.xm = xm;
    }

    public Double getYm() {
        return ym;
    }

    public void setYm(Double ym) {
        this.ym = ym;
    }
}
