package com.berkay22demirel.sinavpuanhesaplama.Model;

public class ExamType {
    private int id;
    private String title;
    private String description;
    private Class activityReferance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Class getActivityReferance() {
        return activityReferance;
    }

    public void setActivityReferance(Class activityReferance) {
        this.activityReferance = activityReferance;
    }
}
