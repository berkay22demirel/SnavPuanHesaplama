package com.berkay22demirel.sinavpuanhesaplama.Model;

import com.berkay22demirel.sinavpuanhesaplama.Enum.EkpssTypeEnum;

public class EKPSS {
    private Integer id;
    private String name;
    private Integer editTextGeneralAbilityTrue;
    private Integer editTextGeneralAbilityFalse;
    private Double editTextGeneralAbilityNet;
    private Integer editTextGeneralKnowledgeTrue;
    private Integer editTextGeneralKnowledgeFalse;
    private Double editTextGeneralKnowledgeNet;
    private EkpssTypeEnum ekpssType;
    private Double result;

    public Integer getEditTextGeneralAbilityTrue() {
        return editTextGeneralAbilityTrue;
    }

    public void setEditTextGeneralAbilityTrue(Integer editTextGeneralAbilityTrue) {
        this.editTextGeneralAbilityTrue = editTextGeneralAbilityTrue;
    }

    public Integer getEditTextGeneralAbilityFalse() {
        return editTextGeneralAbilityFalse;
    }

    public void setEditTextGeneralAbilityFalse(Integer editTextGeneralAbilityFalse) {
        this.editTextGeneralAbilityFalse = editTextGeneralAbilityFalse;
    }

    public Double getEditTextGeneralAbilityNet() {
        return editTextGeneralAbilityNet;
    }

    public void setEditTextGeneralAbilityNet(Double editTextGeneralAbilityNet) {
        this.editTextGeneralAbilityNet = editTextGeneralAbilityNet;
    }

    public Integer getEditTextGeneralKnowledgeTrue() {
        return editTextGeneralKnowledgeTrue;
    }

    public void setEditTextGeneralKnowledgeTrue(Integer editTextGeneralKnowledgeTrue) {
        this.editTextGeneralKnowledgeTrue = editTextGeneralKnowledgeTrue;
    }

    public Integer getEditTextGeneralKnowledgeFalse() {
        return editTextGeneralKnowledgeFalse;
    }

    public void setEditTextGeneralKnowledgeFalse(Integer editTextGeneralKnowledgeFalse) {
        this.editTextGeneralKnowledgeFalse = editTextGeneralKnowledgeFalse;
    }

    public Double getEditTextGeneralKnowledgeNet() {
        return editTextGeneralKnowledgeNet;
    }

    public void setEditTextGeneralKnowledgeNet(Double editTextGeneralKnowledgeNet) {
        this.editTextGeneralKnowledgeNet = editTextGeneralKnowledgeNet;
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
