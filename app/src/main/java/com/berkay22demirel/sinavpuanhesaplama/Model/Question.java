package com.berkay22demirel.sinavpuanhesaplama.Model;

public class Question {
    private Integer questionTrue;
    private Integer questionFalse;
    private Double questionNet;

    public Integer getQuestionTrue() {
        return questionTrue;
    }

    public void setQuestionTrue(Integer questionTrue) {
        this.questionTrue = questionTrue;
    }

    public Integer getQuestionFalse() {
        return questionFalse;
    }

    public void setQuestionFalse(Integer questionFalse) {
        this.questionFalse = questionFalse;
    }

    public Double getQuestionNet() {
        return questionNet;
    }

    public void setQuestionNet(Double questionNet) {
        this.questionNet = questionNet;
    }
}
