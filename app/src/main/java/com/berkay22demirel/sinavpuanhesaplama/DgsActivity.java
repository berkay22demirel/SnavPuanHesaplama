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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Util.CommonUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.ConverterUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.DateTimeUtil;

public class DgsActivity extends AppCompatActivity {

    EditText editTextNumericalTrue;
    EditText editTextNumericalFalse;
    EditText editTextNumericalNet;
    EditText editTextVerbalTrue;
    EditText editTextVerbalFalse;
    EditText editTextVerbalNet;
    EditText editTextAssociateDegreeSuccessGrade;
    TextView textViewDGSTime;
    CheckBox checkBoxBeforeResult;
    Button buttonCalculate;
    private static String PAGE_TITLE = ExamsEnum.DGS.getTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dgs);
        getSupportActionBar().setTitle(CommonUtil.getPageTitle(PAGE_TITLE));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setViewReferences();
        provideViews();
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
        editTextNumericalTrue = findViewById(R.id.editTextDGSNumericalTrue);
        editTextNumericalFalse = findViewById(R.id.editTextDGSNumericalFalse);
        editTextNumericalNet = findViewById(R.id.editTextDGSNumericalNet);
        editTextVerbalTrue = findViewById(R.id.editTextDGSVerbalTrue);
        editTextVerbalFalse = findViewById(R.id.editTextDGSVerbalFalse);
        editTextVerbalNet = findViewById(R.id.editTextDGSVerbalNet);
        editTextAssociateDegreeSuccessGrade = findViewById(R.id.editTextDGSAssociateDegreeSuccessGrade);
        textViewDGSTime = findViewById(R.id.textViewDGSTime);
        checkBoxBeforeResult = findViewById(R.id.checkBoxDGSBeforeResult);
        buttonCalculate = findViewById(R.id.buttonDGSCalculate);
    }

    private void provideViews() {
        CommonUtil.provideEditTextTrue(CommonUtil.SIXTY_QUESTIONS, editTextNumericalTrue, editTextNumericalFalse, editTextNumericalNet);
        CommonUtil.provideEditTextFalse(CommonUtil.SIXTY_QUESTIONS, editTextNumericalFalse, editTextNumericalTrue, editTextNumericalNet);
        CommonUtil.provideEditTextTrue(CommonUtil.SIXTY_QUESTIONS, editTextVerbalTrue, editTextVerbalFalse, editTextVerbalNet);
        CommonUtil.provideEditTextFalse(CommonUtil.SIXTY_QUESTIONS, editTextVerbalFalse, editTextVerbalTrue, editTextVerbalNet);
        CommonUtil.setEditTextMaxValue(editTextAssociateDegreeSuccessGrade, 100);
        DateTimeUtil.addCountDown(textViewDGSTime, PAGE_TITLE);
    }

    private void setCalculateButtonListener() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numericalTrue = ConverterUtil.convertToInteger(editTextNumericalTrue.getText().toString());
                int numericalFalse = ConverterUtil.convertToInteger(editTextNumericalFalse.getText().toString());
                int verbalTrue = ConverterUtil.convertToInteger(editTextVerbalTrue.getText().toString());
                int verbalFalse = ConverterUtil.convertToInteger(editTextVerbalFalse.getText().toString());
                int associateDegreeSuccessGrade = ConverterUtil.convertToInteger(editTextAssociateDegreeSuccessGrade.getText().toString());
                boolean beforeResult = checkBoxBeforeResult.isChecked();
                double numericalNet = CommonUtil.getNet(numericalTrue, numericalFalse);
                double verbalNet = CommonUtil.getNet(verbalTrue, verbalFalse);
                double numerical = getNumerical(numericalNet);
                double verbal = getVerbal(verbalNet);
                double numericalResult = getNumericalResult(numerical, verbal, associateDegreeSuccessGrade, beforeResult);
                double verbalResult = getVerbalResult(numerical, verbal, associateDegreeSuccessGrade, beforeResult);
                double equalWeightResult = getEqualWeightResult(numerical, verbal, associateDegreeSuccessGrade, beforeResult);
                showResultDialog(numericalResult, verbalResult, equalWeightResult);
            }
        });
    }

    private void showResultDialog(double numericalResult, double verbalResult, double equalWeightResult) {
        final Dialog dialog = new Dialog(DgsActivity.this);
        dialog.setContentView(R.layout.dialog_dgs);
        TextView textViewResultNumeric = dialog.findViewById(R.id.textViewDGSResultNumeric);
        TextView textViewResultVerbal = dialog.findViewById(R.id.textViewDGSResultVerbal);
        TextView textViewResultEqualWeight = dialog.findViewById(R.id.textViewDGSResultEqualWeight);
        textViewResultNumeric.setText(String.valueOf(CommonUtil.round(numericalResult, 2)));
        textViewResultVerbal.setText(String.valueOf(CommonUtil.round(verbalResult, 2)));
        textViewResultEqualWeight.setText(String.valueOf(CommonUtil.round(equalWeightResult, 2)));
        setDialogButtonsListener(dialog);
        dialog.show();
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(DgsActivity.this)
                .setTitle("UYARI!")
                .setMessage("*Sınav Sonucunuz 2017 katsayılarına göre hesaplanmıştır. Gireceğiniz sınavda ufak farklılıklar gösterebilir.")
                .setPositiveButton(getResources().getText(R.string.button_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setIcon(getResources().getDrawable(R.drawable.alert_dialog_icon))
                .show();
    }

    private double getNumericalResult(double numerical, double verbal, double associateDegreeSuccessGrade, boolean beforeResult) {
        double factor;
        if (beforeResult) {
            factor = 0.45;
        } else {
            factor = 0.6;
        }
        return numerical * 3.0 + verbal * 0.6 + associateDegreeSuccessGrade * 0.8 * factor;
    }

    private double getVerbalResult(double numerical, double verbal, double associateDegreeSuccessGrade, boolean beforeResult) {
        double factor;
        if (beforeResult) {
            factor = 0.45;
        } else {
            factor = 0.6;
        }
        return numerical * 0.6 + verbal * 3.0 + associateDegreeSuccessGrade * 0.8 * factor;
    }

    private double getEqualWeightResult(double numerical, double verbal, double associateDegreeSuccessGrade, boolean beforeResult) {
        double factor;
        if (beforeResult) {
            factor = 0.45;
        } else {
            factor = 0.6;
        }
        return numerical * 1.8 + verbal * 1.8 + associateDegreeSuccessGrade * 0.8 * factor;
    }

    private double getNumerical(double numericalNet) {
        double testMean = 7.58;
        double testStandardDeviation = 9.45;
        return 50.0 + 10.0 * ((numericalNet - testMean) / testStandardDeviation);
    }

    private double getVerbal(double verbalNetNet) {
        double testMean = 23.91;
        double testStandardDeviation = 13.59;
        return 50.0 + 10.0 * ((verbalNetNet - testMean) / testStandardDeviation);
    }

    private void setDialogButtonsListener(final Dialog dialog) {
        Button buttonCalculate = dialog.findViewById(R.id.buttonDGSDialogCalculate);
        Button buttonSave = dialog.findViewById(R.id.buttonDGSDialogSave);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DgsActivity.this, "Sınav Kaydedildi", Toast.LENGTH_SHORT).show();
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
