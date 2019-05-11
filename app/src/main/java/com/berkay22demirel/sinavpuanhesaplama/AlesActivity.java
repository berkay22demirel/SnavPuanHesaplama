package com.berkay22demirel.sinavpuanhesaplama;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Util.CommonUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.ConverterUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.DateTimeUtil;

public class AlesActivity extends AppCompatActivity {

    EditText editTextMathsTrue;
    EditText editTextMathsFalse;
    EditText editTextMathsNet;
    EditText editTextTurkishTrue;
    EditText editTextTurkishFalse;
    EditText editTextTurkishNet;
    TextView textViewALESTime;
    Button buttonCalculate;

    private static String PAGE_TITLE = ExamsEnum.ALES.getTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ales);
        getSupportActionBar().setTitle(CommonUtil.getPageTitle(PAGE_TITLE));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setViewReferences();
        provideViews();
        DateTimeUtil.addCountDown(textViewALESTime, PAGE_TITLE);
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
            case R.id.action_alert:
                showAlertDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setViewReferences() {
        editTextMathsTrue = findViewById(R.id.editTextALESMathsTrue);
        editTextMathsFalse = findViewById(R.id.editTextALESMathsFalse);
        editTextMathsNet = findViewById(R.id.editTextALESMathsNet);
        editTextTurkishTrue = findViewById(R.id.editTextALESTurkishTrue);
        editTextTurkishFalse = findViewById(R.id.editTextALESTurkishFalse);
        editTextTurkishNet = findViewById(R.id.editTextALESTurkishNet);
        textViewALESTime = findViewById(R.id.textViewALESTime);
        buttonCalculate = findViewById(R.id.buttonALESCalculate);
    }

    private void provideViews() {
        CommonUtil.provideEditTextTrue(CommonUtil.FIFTY_QUESTIONS, editTextMathsTrue, editTextMathsFalse, editTextMathsNet);
        CommonUtil.provideEditTextFalse(CommonUtil.FIFTY_QUESTIONS, editTextMathsFalse, editTextMathsTrue, editTextMathsNet);
        CommonUtil.provideEditTextTrue(CommonUtil.FIFTY_QUESTIONS, editTextTurkishTrue, editTextTurkishFalse, editTextTurkishNet);
        CommonUtil.provideEditTextFalse(CommonUtil.FIFTY_QUESTIONS, editTextTurkishFalse, editTextTurkishTrue, editTextTurkishNet);
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
        TextView textViewNumeric = dialog.findViewById(R.id.textViewALESResultNumeric);
        TextView textViewVerbal = dialog.findViewById(R.id.textViewALESResultVerbal);
        TextView textViewEqualWeight = dialog.findViewById(R.id.textViewALESResultEqualWeight);
        textViewNumeric.setText(String.valueOf(CommonUtil.round(numericalResult, 2)));
        textViewVerbal.setText(String.valueOf(CommonUtil.round(verbalResult, 2)));
        textViewEqualWeight.setText(String.valueOf(CommonUtil.round(equalWeightResult, 2)));
        setDialogButtonsListener(dialog);
        dialog.show();
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(AlesActivity.this)
                .setTitle("UYARI!")
                .setMessage("*Sınav Sonucunuz 2018 katsayılarına göre hesaplanmıştır. Gireceğiniz sınavda ufak farklılıklar gösterebilir.")
                .setPositiveButton(getResources().getText(R.string.button_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setIcon(getResources().getDrawable(R.drawable.alert_dialog_icon))
                .show();
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
