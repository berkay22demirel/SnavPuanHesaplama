package com.berkay22demirel.sinavpuanhesaplama.Model;

public class Exam {
    private Long id;
    private String name;
    private Integer examSubType;

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

    public Integer getExamSubType() {
        return examSubType;
    }

    public void setExamSubType(Integer examSubType) {
        this.examSubType = examSubType;
    }
}
