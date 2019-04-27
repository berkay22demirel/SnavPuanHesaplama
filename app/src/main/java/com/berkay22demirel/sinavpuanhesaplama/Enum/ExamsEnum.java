package com.berkay22demirel.sinavpuanhesaplama.Enum;

import com.berkay22demirel.sinavpuanhesaplama.AlesActivity;
import com.berkay22demirel.sinavpuanhesaplama.DgsActivity;
import com.berkay22demirel.sinavpuanhesaplama.DusActivity;
import com.berkay22demirel.sinavpuanhesaplama.EkpssActivity;
import com.berkay22demirel.sinavpuanhesaplama.EusActivity;
import com.berkay22demirel.sinavpuanhesaplama.KpssActivity;
import com.berkay22demirel.sinavpuanhesaplama.Model.Exam;
import com.berkay22demirel.sinavpuanhesaplama.TusActivity;
import com.berkay22demirel.sinavpuanhesaplama.YdsActivity;
import com.berkay22demirel.sinavpuanhesaplama.YdusActivity;
import com.berkay22demirel.sinavpuanhesaplama.YksActivity;

import java.util.ArrayList;
import java.util.List;

public enum ExamsEnum {
    ALES(1, "ALES", "Akademik Personel ve Lisansüstü Eğitim Sınavı", AlesActivity.class),
    KPSS(2, "KPSS", "Kamu Personel Seçme Sınavı", KpssActivity.class),
    YKS(3, "YKS", "Yükseköğretim Kurumları Sınavı", YksActivity.class),
    YDS(4, "YDS", "Yabancı Dil Sınavı", YdsActivity.class),
    DGS(5, "DGS", "Dikey Geçiş Sınavı", DgsActivity.class),
    EKPSS(6, "EKPSS", "Engelli Kamu Personeli Seçme Sınavı", EkpssActivity.class),
    DUS(7, "DUS", "Diş Hekimliği Uzmanlık Sınavı", DusActivity.class),
    TUS(8, "TUS", "Tıpta Uzmanlık Sınavı", TusActivity.class),
    YDUS(9, "YDUS", "Tıpta Yan Dal Uzmanlık Sınavı", YdusActivity.class),
    EUS(10, "EUS", "Eczacılıkta Uzmanlık Sınavı", EusActivity.class);

    private int id;
    private String title;
    private String description;
    private Class activityReferance;
    private static List<Exam> examsList;

    ExamsEnum(int id, String title, String description, Class activityReferance) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.activityReferance = activityReferance;
    }

    public static List<Exam> getExamsList() {
        if (examsList != null && !examsList.isEmpty()) {
            return examsList;
        }
        examsList = new ArrayList<>();
        for (ExamsEnum examsEnum : ExamsEnum.values()) {
            Exam exam = new Exam();
            exam.setId(examsEnum.getId());
            exam.setTitle(examsEnum.getTitle());
            exam.setDescription(examsEnum.getDescription());
            exam.setActivityReferance(examsEnum.getActivityReferance());
            examsList.add(exam);
        }
        return examsList;
    }

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
