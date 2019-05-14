package com.berkay22demirel.sinavpuanhesaplama.Model;

public class YDS {
    private Integer id;
    private String name;
    private Integer languageTrue;
    private Integer languageFalse;
    private Double languageNet;
    private Double result;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLanguageTrue() {
        return languageTrue;
    }

    public void setLanguageTrue(Integer languageTrue) {
        this.languageTrue = languageTrue;
    }

    public Integer getLanguageFalse() {
        return languageFalse;
    }

    public void setLanguageFalse(Integer languageFalse) {
        this.languageFalse = languageFalse;
    }

    public Double getLanguageNet() {
        return languageNet;
    }

    public void setLanguageNet(Double languageNet) {
        this.languageNet = languageNet;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }
}
