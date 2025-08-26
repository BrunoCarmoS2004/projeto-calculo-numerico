package br.com.tania.calculadoracalculocompleto.models.bi;

public class InfosCalculoBi {
    private Double precissao;
    private String formula;

    public InfosCalculoBi() {
    }

    public InfosCalculoBi(Double precissao, String formula) {
        this.precissao = precissao;
        this.formula = formula;
    }

    public Double getPrecissao() {
        return precissao;
    }

    public void setPrecissao(Double precissao) {
        this.precissao = precissao;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

}
