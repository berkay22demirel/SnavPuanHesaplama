package com.berkay22demirel.sinavpuanhesaplama.Enum;

import com.berkay22demirel.sinavpuanhesaplama.AlesActivity;
import com.berkay22demirel.sinavpuanhesaplama.DgsActivity;
import com.berkay22demirel.sinavpuanhesaplama.DusActivity;
import com.berkay22demirel.sinavpuanhesaplama.EkpssActivity;
import com.berkay22demirel.sinavpuanhesaplama.EusActivity;
import com.berkay22demirel.sinavpuanhesaplama.KpssActivity;
import com.berkay22demirel.sinavpuanhesaplama.Model.ExamType;
import com.berkay22demirel.sinavpuanhesaplama.TusActivity;
import com.berkay22demirel.sinavpuanhesaplama.Util.CommonUtil;
import com.berkay22demirel.sinavpuanhesaplama.YdsActivity;
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
    EUS(9, "EUS", "Eczacılıkta Uzmanlık Sınavı", EusActivity.class);

    private int id;
    private String title;
    private String description;
    private Class activityReferance;
    private static List<ExamType> examsList;

    ExamsEnum(int id, String title, String description, Class activityReferance) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.activityReferance = activityReferance;
    }

    public static List<ExamType> getExamsList() {
        if (examsList != null && !examsList.isEmpty()) {
            return examsList;
        }
        examsList = new ArrayList<>();
        for (ExamsEnum examsEnum : ExamsEnum.values()) {
            ExamType examType = new ExamType();
            examType.setId(examsEnum.getId());
            examType.setTitle(examsEnum.getTitle());
            examType.setDescription(examsEnum.getDescription());
            examType.setActivityReferance(examsEnum.getActivityReferance());
            examsList.add(examType);
        }
        return examsList;
    }

    public static String getName(Integer id) {
        if (id != null) {
            for (ExamsEnum examsEnum : ExamsEnum.values()) {
                if (id.compareTo(examsEnum.getId()) == 0) {
                    return examsEnum.getTitle();
                }
            }
        }
        return CommonUtil.EMPTY_STRING;
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
