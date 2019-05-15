package com.berkay22demirel.sinavpuanhesaplama.Model;

import com.berkay22demirel.sinavpuanhesaplama.Enum.EkpssTypeEnum;

public class EKPSS {
    private Long id;
    private String name;
    private Integer generalAbilityTrue;
    private Integer generalAbilityFalse;
    private Double generalAbilityNet;
    private Integer generalKnowledgeTrue;
    private Integer generalKnowledgeFalse;
    private Double generalKnowledgeNet;
    private EkpssTypeEnum ekpssType;
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

    public Integer getGeneralAbilityTrue() {
        return generalAbilityTrue;
    }

    public void setGeneralAbilityTrue(Integer generalAbilityTrue) {
        this.generalAbilityTrue = generalAbilityTrue;
    }

    public Integer getGeneralAbilityFalse() {
        return generalAbilityFalse;
    }

    public void setGeneralAbilityFalse(Integer generalAbilityFalse) {
        this.generalAbilityFalse = generalAbilityFalse;
    }

    public Double getGeneralAbilityNet() {
        return generalAbilityNet;
    }

    public void setGeneralAbilityNet(Double generalAbilityNet) {
        this.generalAbilityNet = generalAbilityNet;
    }

    public Integer getGeneralKnowledgeTrue() {
        return generalKnowledgeTrue;
    }

    public void setGeneralKnowledgeTrue(Integer generalKnowledgeTrue) {
        this.generalKnowledgeTrue = generalKnowledgeTrue;
    }

    public Integer getGeneralKnowledgeFalse() {
        return generalKnowledgeFalse;
    }

    public void setGeneralKnowledgeFalse(Integer generalKnowledgeFalse) {
        this.generalKnowledgeFalse = generalKnowledgeFalse;
    }

    public Double getGeneralKnowledgeNet() {
        return generalKnowledgeNet;
    }

    public void setGeneralKnowledgeNet(Double generalKnowledgeNet) {
        this.generalKnowledgeNet = generalKnowledgeNet;
    }

    public EkpssTypeEnum getEkpssType() {
        return ekpssType;
    }

    public void setEkpssType(EkpssTypeEnum ekpssType) {
        this.ekpssType = ekpssType;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }
}
