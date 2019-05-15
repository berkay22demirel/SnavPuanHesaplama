package com.berkay22demirel.sinavpuanhesaplama.Model;

public class DUS {
    private Long id;
    private String name;
    private Integer basicSciencesTrue;
    private Integer basicSciencesFalse;
    private Double basicSciencesNet;
    private Integer clinicalSciencesTrue;
    private Integer clinicalSciencesFalse;
    private Double clinicalSciencesNet;
    private Double result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBasicSciencesTrue() {
        return basicSciencesTrue;
    }

    public void setBasicSciencesTrue(Integer basicSciencesTrue) {
        this.basicSciencesTrue = basicSciencesTrue;
    }

    public Integer getBasicSciencesFalse() {
        return basicSciencesFalse;
    }

    public void setBasicSciencesFalse(Integer basicSciencesFalse) {
        this.basicSciencesFalse = basicSciencesFalse;
    }

    public Double getBasicSciencesNet() {
        return basicSciencesNet;
    }

    public void setBasicSciencesNet(Double basicSciencesNet) {
        this.basicSciencesNet = basicSciencesNet;
    }

    public Integer getClinicalSciencesTrue() {
        return clinicalSciencesTrue;
    }

    public void setClinicalSciencesTrue(Integer clinicalSciencesTrue) {
        this.clinicalSciencesTrue = clinicalSciencesTrue;
    }

    public Integer getClinicalSciencesFalse() {
        return clinicalSciencesFalse;
    }

    public void setClinicalSciencesFalse(Integer clinicalSciencesFalse) {
        this.clinicalSciencesFalse = clinicalSciencesFalse;
    }

    public Double getClinicalSciencesNet() {
        return clinicalSciencesNet;
    }

    public void setClinicalSciencesNet(Double clinicalSciencesNet) {
        this.clinicalSciencesNet = clinicalSciencesNet;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }
}
