package com.berkay22demirel.sinavpuanhesaplama;

import android.app.Dialog;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Util.CommonUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.ConverterUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.ExamDateUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AlesActivity extends AppCompatActivity {

    private static int MATHS_NUMBER_OF_QUESTIONS = 50;
    private static int TURKISH_NUMBER_OF_QUESTIONS = 50;

    EditText editTextMathsTrue;
    EditText editTextMathsFalse;
    EditText editTextTurkishTrue;
    EditText editTextTurkishFalse;
    Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ales);
        getSupportActionBar().setTitle("ALES Puan Hesapla");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setViewReferences();
        provideSecurityEditText();
        addCountDown();
        setCalculateButtonListener();
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

    private void setViewReferences() {
        editTextMathsTrue = (EditText) findViewById(R.id.editTextALESMathsTrue);
        editTextMathsFalse = (EditText) findViewById(R.id.editTextALESMathsFalse);
        editTextTurkishTrue = (EditText) findViewById(R.id.editTextALESTurkishTrue);
        editTextTurkishFalse = (EditText) findViewById(R.id.editTextALESTurkishFalse);
        buttonCalculate = (Button) findViewById(R.id.buttonALESCalculate);
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

    private void provideSecurityEditText() {
        CommonUtil.setEditTextChangedListener(MATHS_NUMBER_OF_QUESTIONS, editTextMathsTrue, editTextMathsFalse);
        CommonUtil.setEditTextChangedListener(MATHS_NUMBER_OF_QUESTIONS, editTextMathsFalse, editTextMathsTrue);
        CommonUtil.setEditTextChangedListener(TURKISH_NUMBER_OF_QUESTIONS, editTextTurkishTrue, editTextTurkishFalse);
        CommonUtil.setEditTextChangedListener(TURKISH_NUMBER_OF_QUESTIONS, editTextTurkishFalse, editTextTurkishTrue);
    }

    private void setCalculateButtonListener() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mathsTrue = ConverterUtil.convertToInteger(editTextMathsTrue.getText().toString());
                int mathsFalse = ConverterUtil.convertToInteger(editTextMathsFalse.getText().toString());
                int turkishTrue = ConverterUtil.convertToInteger(editTextTurkishTrue.getText().toString());
                int turkishFalse = ConverterUtil.convertToInteger(editTextTurkishFalse.getText().toString());
                double mathsNet = CommonUtil.getNet(mathsTrue, mathsFalse);
                double turkishNet = CommonUtil.getNet(turkishTrue, turkishFalse);
                double numericalResult = getNumericalResult(mathsNet, turkishNet);
                double verbalResult = getVerbalResult(mathsNet, turkishNet);
                double equalWeightResult = getEqualWeightResult(mathsNet, turkishNet);
                showResultDailog(mathsNet, turkishNet, numericalResult, verbalResult, equalWeightResult);
            }
        });
    }

    private void showResultDailog(double mathsNet, double turkishNet, double numericalResult, double verbalResult, double equalWeightResult) {
        final Dialog dialog = new Dialog(AlesActivity.this);
        dialog.setContentView(R.layout.dialog_ales);
        TextView textViewMathsNet = dialog.findViewById(R.id.textViewALESResultMathsNet);
        TextView textViewTurkishNet = dialog.findViewById(R.id.textViewALESResultTurkishNet);
        TextView textViewNumeric = dialog.findViewById(R.id.textViewALESResultNumeric);
        TextView textViewVerbal = dialog.findViewById(R.id.textViewALESResultVerbal);
        TextView textViewEqualWeight = dialog.findViewById(R.id.textViewALESResultEqualWeight);
        textViewMathsNet.setText(String.valueOf(mathsNet) + CommonUtil.HELPER_NET_TEXT);
        textViewTurkishNet.setText(String.valueOf(turkishNet) + CommonUtil.HELPER_NET_TEXT);
        textViewNumeric.setText(String.valueOf(CommonUtil.round(numericalResult, 2)));
        textViewVerbal.setText(String.valueOf(CommonUtil.round(verbalResult, 2)));
        textViewEqualWeight.setText(String.valueOf(CommonUtil.round(equalWeightResult, 2)));
        setDialogButtonsListener(dialog);
        dialog.show();
    }

    private double getNumericalResult(double mathsNet, double turkishNet) {
        //Adayın Ağırlıklı Puanı
        double ap = mathsNet * 0.75 + turkishNet * 0.25;
        //Ağırlıklı Puanların Ortalaması
        double x = 0.75 * 19.07 + 0.25 * 28.85;
        //Ağırlıklı Puanların Standart Sapması
        double s = 0.75 * 13.03 + 0.25 * 9.97;
        //Ağırlıklı Puanların En Büyüğü
        double b = 50.0;
        return 70.0 + (30.0 * (2.0 * (ap - x) - s)) / (2.0 * (b - x) - s);
    }

    private double getVerbalResult(double mathsNet, double turkishNet) {
        //Adayın Ağırlıklı Puanı
        double ap = mathsNet * 0.25 + turkishNet * 0.75;
        //Ağırlıklı Puanların Ortalaması
        double x = 0.25 * 19.07 + 0.75 * 28.85;
        //Ağırlıklı Puanların Standart Sapması
        double s = 0.25 * 13.03 + 0.75 * 9.97;
        //Ağırlıklı Puanların En Büyüğü
        double b = 50.0;
        return 70.0 + (30.0 * (2.0 * (ap - x) - s)) / (2.0 * (b - x) - s);
    }

    private double getEqualWeightResult(double mathsNet, double turkishNet) {
        //Adayın Ağırlıklı Puanı
        double ap = mathsNet * 0.50 + turkishNet * 0.50;
        //Ağırlıklı Puanların Ortalaması
        double x = 0.50 * 19.07 + 0.50 * 28.85;
        //Ağırlıklı Puanların Standart Sapması
        double s = 0.50 * 13.03 + 0.50 * 9.97;
        //Ağırlıklı Puanların En Büyüğü
        double b = 50.0;
        return 70.0 + (30.0 * (2.0 * (ap - x) - s)) / (2.0 * (b - x) - s);
    }

    private void setDialogButtonsListener(final Dialog dialog) {
        Button buttonCalculate = dialog.findViewById(R.id.buttonALESDialogCalculate);
        Button buttonSave = dialog.findViewById(R.id.buttonALESDialogSave);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AlesActivity.this, "Sınav Kaydedildi", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}
