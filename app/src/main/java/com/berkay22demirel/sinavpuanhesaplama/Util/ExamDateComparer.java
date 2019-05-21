package com.berkay22demirel.sinavpuanhesaplama.Util;

import com.berkay22demirel.sinavpuanhesaplama.Model.ExamDate;

import java.util.Comparator;

public class ExamDateComparer implements Comparator<ExamDate> {
    @Override
    public int compare(ExamDate examDate1, ExamDate examDate2) {
        if (examDate1.getExamDate().getTime() > examDate2.getExamDate().getTime()) {
            return 1;
        } else if (examDate1.getExamDate().getTime() < examDate2.getExamDate().getTime()) {
            return -1;
        } else {
            return 0;
        }
    }
}
