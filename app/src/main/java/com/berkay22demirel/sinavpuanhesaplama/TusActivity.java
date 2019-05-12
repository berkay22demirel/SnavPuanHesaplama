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

public class TusActivity extends AppCompatActivity {

    EditText editTextBasicMedicineSciencesTrue;
    EditText editTextBasicMedicineSciencesFalse;
    EditText editTextBasicMedicineSciencesNet;
    EditText editTextClinicalMedicineSciencesTrue;
    EditText editTextClinicalMedicineSciencesFalse;
    EditText editTextClinicalMedicineSciencesNet;
    TextView textViewTUSTime;
    Button buttonCalculate;

    private static String PAGE_TITLE = ExamsEnum.TUS.getTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tus);
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
        editTextBasicMedicineSciencesTrue = findViewById(R.id.editTextTUSBasicMedicineSciencesTrue);
        editTextBasicMedicineSciencesFalse = findViewById(R.id.editTextTUSBasicMedicineSciencesFalse);
        editTextBasicMedicineSciencesNet = findViewById(R.id.editTextTUSBasicMedicineSciencesNet);
        editTextClinicalMedicineSciencesTrue = findViewById(R.id.editTextTUSClinicalMedicineSciencesTrue);
        editTextClinicalMedicineSciencesFalse = findViewById(R.id.editTextTUSClinicalMedicineSciencesFalse);
        editTextClinicalMedicineSciencesNet = findViewById(R.id.editTextTUSClinicalMedicineSciencesNet);
        textViewTUSTime = findViewById(R.id.textViewTUSTime);
        buttonCalculate = findViewById(R.id.buttonTUSCalculate);
    }

    private void provideViews() {
        CommonUtil.provideEditTextTrue(CommonUtil.HUNDRED_TWENTY_QUESTIONS, editTextBasicMedicineSciencesTrue, editTextBasicMedicineSciencesFalse, editTextBasicMedicineSciencesNet);
        CommonUtil.provideEditTextFalse(CommonUtil.HUNDRED_TWENTY_QUESTIONS, editTextBasicMedicineSciencesFalse, editTextBasicMedicineSciencesTrue, editTextBasicMedicineSciencesNet);
        CommonUtil.provideEditTextTrue(CommonUtil.HUNDRED_TWENTY_QUESTIONS, editTextClinicalMedicineSciencesTrue, editTextClinicalMedicineSciencesFalse, editTextClinicalMedicineSciencesNet);
        CommonUtil.provideEditTextFalse(CommonUtil.HUNDRED_TWENTY_QUESTIONS, editTextClinicalMedicineSciencesFalse, editTextClinicalMedicineSciencesTrue, editTextClinicalMedicineSciencesNet);
        DateTimeUtil.addCountDown(textViewTUSTime, PAGE_TITLE);
    }

    private void setCalculateButtonListener() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int basicMedicineSciencesTrue = ConverterUtil.convertToInteger(editTextBasicMedicineSciencesTrue.getText().toString());
                int basicMedicineSciencesFalse = ConverterUtil.convertToInteger(editTextBasicMedicineSciencesFalse.getText().toString());
                int clinicalMedicineSciencesTrue = ConverterUtil.convertToInteger(editTextClinicalMedicineSciencesTrue.getText().toString());
                int clinicalMedicineSciencesFalse = ConverterUtil.convertToInteger(editTextClinicalMedicineSciencesFalse.getText().toString());
                double basicMedicineSciencesNet = CommonUtil.getNet(basicMedicineSciencesTrue, basicMedicineSciencesFalse);
                double clinicalMedicineSciencesNet = CommonUtil.getNet(clinicalMedicineSciencesTrue, clinicalMedicineSciencesFalse);
                double graduateMedicineKPoint = getGraduateMedicineKPoint(basicMedicineSciencesNet, clinicalMedicineSciencesNet);
                double graduateMedicineTPoint = getGraduateMedicineTPoint(basicMedicineSciencesNet, clinicalMedicineSciencesNet);
                double graduateMedicineAPoint = getGraduateMedicineAPoint(clinicalMedicineSciencesNet);
                double notGraduateMedicineTPoint = getNotGraduateMedicineTPoint(basicMedicineSciencesNet);
                showResultDialog(graduateMedicineKPoint, graduateMedicineTPoint, graduateMedicineAPoint, notGraduateMedicineTPoint);
            }
        });
    }

    private void showResultDialog(double graduateMedicineKPoint, double graduateMedicineTPoint, double graduateMedicineAPoint, double notGraduateMedicineTPoint) {
        final Dialog dialog = new Dialog(TusActivity.this);
        dialog.setContentView(R.layout.dialog_tus);
        TextView textViewGraduateMedicineKPoint = dialog.findViewById(R.id.textViewTUSResultGraduateMedicineKPoint);
        TextView textViewGraduateMedicineTPoint = dialog.findViewById(R.id.textViewTUSResultGraduateMedicineTPoint);
        TextView textViewGraduateMedicineAPoint = dialog.findViewById(R.id.textViewTUSResultGraduateMedicineAPoint);
        TextView textViewNotGraduateMedicineTPoint = dialog.findViewById(R.id.textViewTUSResultNotGraduateMedicineTPoint);
        textViewGraduateMedicineKPoint.setText(String.valueOf(CommonUtil.round(graduateMedicineKPoint, 2)));
        textViewGraduateMedicineTPoint.setText(String.valueOf(CommonUtil.round(graduateMedicineTPoint, 2)));
        textViewGraduateMedicineAPoint.setText(String.valueOf(CommonUtil.round(graduateMedicineAPoint, 2)));
        textViewNotGraduateMedicineTPoint.setText(String.valueOf(CommonUtil.round(notGraduateMedicineTPoint, 2)));
        setDialogButtonsListener(dialog);
        dialog.show();
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(TusActivity.this)
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

    private double getGraduateMedicineKPoint(double basicMedicineSciencesNet, double clinicalMedicineSciencesNet) {
        return (18.06980 + clinicalMedicineSciencesNet * 0.60489) * 0.5 + (28.27491 + basicMedicineSciencesNet * 0.42438) * 0.5;
    }

    private double getGraduateMedicineTPoint(double basicMedicineSciencesNet, double clinicalMedicineSciencesNet) {
        return (18.06980 + clinicalMedicineSciencesNet * 0.60489) * 0.3 + (28.27491 + basicMedicineSciencesNet * 0.42438) * 0.7;
    }

    private double getGraduateMedicineAPoint(double clinicalMedicineSciencesNet) {
        return 18.06980 + clinicalMedicineSciencesNet * 0.60489;
    }

    private double getNotGraduateMedicineTPoint(double basicMedicineSciencesNet) {
        return 28.27491 + basicMedicineSciencesNet * 0.42438;
    }

    private void setDialogButtonsListener(final Dialog dialog) {
        Button buttonCalculate = dialog.findViewById(R.id.buttonTUSDialogCalculate);
        Button buttonSave = dialog.findViewById(R.id.buttonTUSDialogSave);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TusActivity.this, "Sınav Kaydedildi", Toast.LENGTH_SHORT).show();
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
