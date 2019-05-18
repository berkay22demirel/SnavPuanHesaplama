package com.berkay22demirel.sinavpuanhesaplama.Model;

public class ALES extends Exam {
    private Integer mathsTrue;
    private Integer mathsFalse;
    private Double mathsNet;
    private Integer turkishTrue;
    private Integer turkishFalse;
    private Double turkishNet;
    private Double numericalResult;
    private Double verbalResult;
    private Double equalWeightResult;

    public Integer getMathsTrue() {
        return mathsTrue;
    }

    public void setMathsTrue(Integer mathsTrue) {
        this.mathsTrue = mathsTrue;
    }

    public Integer getMathsFalse() {
        return mathsFalse;
    }

    public void setMathsFalse(Integer mathsFalse) {
        this.mathsFalse = mathsFalse;
    }

    public Double getMathsNet() {
        return mathsNet;
    }

    public void setMathsNet(Double mathsNet) {
        this.mathsNet = mathsNet;
    }

    public Integer getTurkishTrue() {
        return turkishTrue;
    }

    public void setTurkishTrue(Integer turkishTrue) {
        this.turkishTrue = turkishTrue;
    }

    public Integer getTurkishFalse() {
        return turkishFalse;
    }

    public void setTurkishFalse(Integer turkishFalse) {
        this.turkishFalse = turkishFalse;
    }

    public Double getTurkishNet() {
        return turkishNet;
    }

    public void setTurkishNet(Double turkishNet) {
        this.turkishNet = turkishNet;
    }

    public Double getNumericalResult() {
        return numericalResult;
    }

    public void setNumericalResult(Double numericResult) {
        this.numericalResult = numericResult;
    }

    public Double getVerbalResult() {
        return verbalResult;
    }

    public void setVerbalResult(Double verbalResult) {
        this.verbalResult = verbalResult;
    }

    public Double getEqualWeightResult() {
        return equalWeightResult;
    }

    public void setEqualWeightResult(Double equalWeightResult) {
        this.equalWeightResult = equalWeightResult;
    }
}
