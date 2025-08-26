package br.com.tania.calculadoracalculocompleto.models.bi;

public class ResultadosBi {
    private Double xm;
    private Double ym;

    public ResultadosBi(Double xm, Double ym) {
        this.xm = xm;
        this.ym = ym;
    }

    public ResultadosBi() {
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
