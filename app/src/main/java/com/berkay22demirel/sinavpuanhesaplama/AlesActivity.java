package com.berkay22demirel.sinavpuanhesaplama;

import android.icu.text.RelativeDateTimeFormatter;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Util.ExamDateUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.ValidatorUtil;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AlesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ales);
        getSupportActionBar().setTitle("ALES Puan Hesapla");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addCountDown();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

    private void addCountDown() {
        Date today = new Date();
        Date nextAlesDate = ExamDateUtil.getNextExamDate(ExamsEnum.ALES.getTitle());
        if (nextAlesDate != null) {
            Long time = nextAlesDate.getTime() - today.getTime();
            new CountDownTimer(time, 60000) {
                TextView textViewALESTime = findViewById(R.id.textViewALESTime);

                public void onTick(long millisUntilFinished) {
                    Date date = new Date();
                    date.setTime(millisUntilFinished);
                    long days = TimeUnit.DAYS.convert(millisUntilFinished, TimeUnit.MILLISECONDS);
                    long hours = TimeUnit.HOURS.convert(millisUntilFinished, TimeUnit.MILLISECONDS);
                    long minutes = TimeUnit.MINUTES.convert(millisUntilFinished, TimeUnit.MILLISECONDS);
                    hours = hours % 24;
                    minutes = minutes % 60;
                    textViewALESTime.setText(days + " Gün " + hours + " Saat " + minutes + " Dakika");
                }

                public void onFinish() {
                    textViewALESTime.setText("Sınav Zamanı Geldi!");
                }
            }.start();
        } else {
            TextView textViewALESTime = findViewById(R.id.textViewALESTime);
            textViewALESTime.setVisibility(View.INVISIBLE);
        }
    }


}
