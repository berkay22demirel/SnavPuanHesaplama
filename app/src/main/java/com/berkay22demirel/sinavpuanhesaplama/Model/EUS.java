package com.berkay22demirel.sinavpuanhesaplama.Model;

public class EUS {
    private Long id;
    private String name;
    private Integer eusTrue;
    private Integer eusFalse;
    private Double eusNet;
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

    public Integer getEusTrue() {
        return eusTrue;
    }

    public void setEusTrue(Integer eusTrue) {
        this.eusTrue = eusTrue;
    }

    public Integer getEusFalse() {
        return eusFalse;
    }

    public void setEusFalse(Integer eusFalse) {
        this.eusFalse = eusFalse;
    }

    public Double getEusNet() {
        return eusNet;
    }

    public void setEusNet(Double eusNet) {
        this.eusNet = eusNet;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }
}
