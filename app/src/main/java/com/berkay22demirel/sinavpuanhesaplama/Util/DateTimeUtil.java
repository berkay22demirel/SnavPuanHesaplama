package com.berkay22demirel.sinavpuanhesaplama.Util;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.R;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeUtil {

    public static void addCountDown(final TextView textViewTime, String examTitle) {
        Date today = new Date();
        Date nextAlesDate = ExamDateUtil.getNextExamDate(examTitle);
        if (nextAlesDate != null) {
            long time = nextAlesDate.getTime() - today.getTime();
            new CountDownTimer(time, 60000) {

                public void onTick(long millisUntilFinished) {
                    Date date = new Date();
                    date.setTime(millisUntilFinished);
                    long days = TimeUnit.DAYS.convert(millisUntilFinished, TimeUnit.MILLISECONDS);
                    long hours = TimeUnit.HOURS.convert(millisUntilFinished, TimeUnit.MILLISECONDS);
                    long minutes = TimeUnit.MINUTES.convert(millisUntilFinished, TimeUnit.MILLISECONDS);
                    hours = hours % 24;
                    minutes = minutes % 60;
                    textViewTime.setText(days + " Gün " + hours + " Saat " + minutes + " Dakika");
                }

                public void onFinish() {
                    textViewTime.setText("Sınav Zamanı Geldi!");
                }
            }.start();
        } else {
            textViewTime.setText("2019 Yılında bir sınav yok!");
        }
    }
}
