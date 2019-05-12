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

public class DusActivity extends AppCompatActivity {

    EditText editTextBasicSciencesTrue;
    EditText editTextBasicSciencesFalse;
    EditText editTextBasicSciencesNet;
    EditText editTextClinicalSciencesTrue;
    EditText editTextClinicalSciencesFalse;
    EditText editTextClinicalSciencesNet;
    TextView textViewDUSTime;
    Button buttonCalculate;

    private static String PAGE_TITLE = ExamsEnum.DUS.getTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dus);
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
        editTextBasicSciencesTrue = findViewById(R.id.editTextDUSBasicSciencesTrue);
        editTextBasicSciencesFalse = findViewById(R.id.editTextDUSBasicSciencesFalse);
        editTextBasicSciencesNet = findViewById(R.id.editTextDUSBasicSciencesNet);
        editTextClinicalSciencesTrue = findViewById(R.id.editTextDUSClinicalSciencesTrue);
        editTextClinicalSciencesFalse = findViewById(R.id.editTextDUSClinicalSciencesFalse);
        editTextClinicalSciencesNet = findViewById(R.id.editTextDUSClinicalSciencesNet);
        textViewDUSTime = findViewById(R.id.textViewDUSTime);
        buttonCalculate = findViewById(R.id.buttonDUSCalculate);
    }

    private void provideViews() {
        CommonUtil.provideEditTextTrue(CommonUtil.FOURTY_QUESTIONS, editTextBasicSciencesTrue, editTextBasicSciencesFalse, editTextBasicSciencesNet);
        CommonUtil.provideEditTextFalse(CommonUtil.FOURTY_QUESTIONS, editTextBasicSciencesFalse, editTextBasicSciencesTrue, editTextBasicSciencesNet);
        CommonUtil.provideEditTextTrue(CommonUtil.EIGHTY_QUESTIONS, editTextClinicalSciencesTrue, editTextClinicalSciencesFalse, editTextClinicalSciencesNet);
        CommonUtil.provideEditTextFalse(CommonUtil.EIGHTY_QUESTIONS, editTextClinicalSciencesFalse, editTextClinicalSciencesTrue, editTextClinicalSciencesNet);
        DateTimeUtil.addCountDown(textViewDUSTime, PAGE_TITLE);
    }

    private void setCalculateButtonListener() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int basicSciencesTrue = ConverterUtil.convertToInteger(editTextBasicSciencesTrue.getText().toString());
                int basicSciencesFalse = ConverterUtil.convertToInteger(editTextBasicSciencesFalse.getText().toString());
                int clinicalSciencesTrue = ConverterUtil.convertToInteger(editTextClinicalSciencesTrue.getText().toString());
                int clinicalSciencesFalse = ConverterUtil.convertToInteger(editTextClinicalSciencesFalse.getText().toString());
                double basicSciencesNet = CommonUtil.getNet(basicSciencesTrue, basicSciencesFalse);
                double clinicalSciencesNet = CommonUtil.getNet(clinicalSciencesTrue, clinicalSciencesFalse);
                double result = getResult(basicSciencesNet, clinicalSciencesNet);
                showResultDialog(result);
            }
        });
    }

    private void showResultDialog(double result) {
        final Dialog dialog = new Dialog(DusActivity.this);
        dialog.setContentView(R.layout.dialog_dus);
        TextView textViewResult = dialog.findViewById(R.id.textViewDUSResult);
        textViewResult.setText(String.valueOf(CommonUtil.round(result, 2)));
        setDialogButtonsListener(dialog);
        dialog.show();
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(DusActivity.this)
                .setTitle("UYARI!")
                .setMessage("*Sınav Sonucunuz 2015 katsayılarına göre hesaplanmıştır. Gireceğiniz sınavda ufak farklılıklar gösterebilir.")
                .setPositiveButton(getResources().getText(R.string.button_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setIcon(getResources().getDrawable(R.drawable.alert_dialog_icon))
                .show();
    }

    private double getResult(double basicSciencesNet, double clinicalSciencesNet) {
        return 19.22377 + basicSciencesNet * 0.60299075 + clinicalSciencesNet * 0.415765875;
    }

    private void setDialogButtonsListener(final Dialog dialog) {
        Button buttonCalculate = dialog.findViewById(R.id.buttonDUSDialogCalculate);
        Button buttonSave = dialog.findViewById(R.id.buttonDUSDialogSave);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DusActivity.this, "Sınav Kaydedildi", Toast.LENGTH_SHORT).show();
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
