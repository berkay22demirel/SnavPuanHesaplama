package com.berkay22demirel.sinavpuanhesaplama.Util;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExamDateUtil {

    private static List<Date> alesDateList;
    private static List<Date> dgsDateList;
    private static List<Date> dusDateList;
    private static List<Date> ekpssDateList;
    private static List<Date> eusDateList;
    private static List<Date> kpssDateList;
    private static List<Date> tusDateList;
    private static List<Date> ydsDateList;
    private static List<Date> ydusDateList;
    private static List<Date> yksDateList;

    public static Date getNextExamDate(String examName) {
        Date today = new Date();
        List<Date> examDateList = getExamDateList(examName);
        if (ValidatorUtil.isValidList(examDateList)) {
            for (Date examDate : examDateList) {
                if (examDate.getTime() > today.getTime()) {
                    return examDate;
                }
            }
        }
        return null;
    }

    private static List<Date> getExamDateList(String examName) {
        if (examName != null) {
            if (ExamsEnum.ALES.getTitle().equals(examName)) {
                return getAlesDateList();
            } else if (ExamsEnum.DGS.getTitle().equals(examName)) {
                return getDgsDateList();
            } else if (ExamsEnum.DUS.getTitle().equals(examName)) {
                return getDusDateList();
            } else if (ExamsEnum.EKPSS.getTitle().equals(examName)) {
                return getEkpssDateList();
            } else if (ExamsEnum.EUS.getTitle().equals(examName)) {
                return getEusDateList();
            } else if (ExamsEnum.KPSS.getTitle().equals(examName)) {
                return getKpssDateList();
            } else if (ExamsEnum.TUS.getTitle().equals(examName)) {
                return getTusDateList();
            } else if (ExamsEnum.YDS.getTitle().equals(examName)) {
                return getYdsDateList();
            } else if (ExamsEnum.YDUS.getTitle().equals(examName)) {
                return getYdusDateList();
            } else if (ExamsEnum.YKS.getTitle().equals(examName)) {
                return getYksDateList();
            }
        }
        return null;
    }

    private static List<Date> getAlesDateList() {
        if (ValidatorUtil.isValidList(alesDateList)) {
            return alesDateList;
        }
        alesDateList = new ArrayList<>();
        alesDateList.add(getDate(2019, 4, 5, 10, 15));
        alesDateList.add(getDate(2019, 8, 22, 10, 15));
        alesDateList.add(getDate(2019, 10, 17, 10, 15));
        return alesDateList;
    }

    private static List<Date> getDgsDateList() {
        if (ValidatorUtil.isValidList(dgsDateList)) {
            return dgsDateList;
        }
        dgsDateList = new ArrayList<>();
        dgsDateList.add(getDate(2019, 5, 30, 10, 15));
        return dgsDateList;
    }

    private static List<Date> getDusDateList() {
        if (ValidatorUtil.isValidList(dusDateList)) {
            return dusDateList;
        }
        dusDateList = new ArrayList<>();
        dusDateList.add(getDate(2019, 8, 29, 10, 15));
        dusDateList.add(getDate(2019, 11, 22, 10, 15));
        return dusDateList;
    }

    private static List<Date> getEkpssDateList() {
        if (ValidatorUtil.isValidList(ekpssDateList)) {
            return ekpssDateList;
        }
        ekpssDateList = new ArrayList<>();
        return ekpssDateList;
    }

    private static List<Date> getEusDateList() {
        if (ValidatorUtil.isValidList(eusDateList)) {
            return eusDateList;
        }
        eusDateList = new ArrayList<>();
        eusDateList.add(getDate(2019, 8, 21, 10, 15));
        return eusDateList;
    }

    private static List<Date> getKpssDateList() {
        if (ValidatorUtil.isValidList(kpssDateList)) {
            return kpssDateList;
        }
        kpssDateList = new ArrayList<>();
        kpssDateList.add(getDate(2019, 6, 14, 10, 15));
        kpssDateList.add(getDate(2019, 6, 20, 10, 15));
        kpssDateList.add(getDate(2019, 6, 21, 10, 15));
        kpssDateList.add(getDate(2019, 6, 28, 10, 15));
        return kpssDateList;
    }

    private static List<Date> getTusDateList() {
        if (ValidatorUtil.isValidList(tusDateList)) {
            return tusDateList;
        }
        tusDateList = new ArrayList<>();
        tusDateList.add(getDate(2019, 8, 1, 10, 15));
        return tusDateList;
    }

    private static List<Date> getYdsDateList() {
        if (ValidatorUtil.isValidList(ydsDateList)) {
            return ydsDateList;
        }
        ydsDateList = new ArrayList<>();
        ydsDateList.add(getDate(2019, 8, 8, 10, 15));
        ydsDateList.add(getDate(2019, 11, 1, 10, 15));
        return ydsDateList;
    }

    private static List<Date> getYdusDateList() {
        if (ValidatorUtil.isValidList(ydusDateList)) {
            return ydusDateList;
        }
        ydusDateList = new ArrayList<>();
        ydusDateList.add(getDate(2019, 11, 22, 10, 15));
        return ydusDateList;
    }

    private static List<Date> getYksDateList() {
        if (ValidatorUtil.isValidList(yksDateList)) {
            return yksDateList;
        }
        yksDateList = new ArrayList<>();
        yksDateList.add(getDate(2019, 5, 15, 10, 15));
        yksDateList.add(getDate(2019, 5, 16, 10, 15));
        yksDateList.add(getDate(2019, 5, 16, 15, 45));
        return yksDateList;
    }

    private static Date getDate(int year, int month, int day, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute);
        return calendar.getTime();
    }
}
