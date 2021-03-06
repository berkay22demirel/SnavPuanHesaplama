package com.berkay22demirel.sinavpuanhesaplama;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.berkay22demirel.sinavpuanhesaplama.Database.DatabaseManager;
import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Model.TUS;
import com.berkay22demirel.sinavpuanhesaplama.Service.TusService;
import com.berkay22demirel.sinavpuanhesaplama.Util.AdUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.CommonUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.ConverterUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.DateTimeUtil;
import com.google.android.gms.ads.AdView;

public class TusActivity extends AppCompatActivity {

    TusService tusService;
    EditText editTextBasicMedicineSciencesTrue;
    EditText editTextBasicMedicineSciencesFalse;
    EditText editTextBasicMedicineSciencesNet;
    EditText editTextClinicalMedicineSciencesTrue;
    EditText editTextClinicalMedicineSciencesFalse;
    EditText editTextClinicalMedicineSciencesNet;
    TextView textViewTUSTime;
    Button buttonCalculate;
    AdView adView;

    private static String PAGE_TITLE = ExamsEnum.TUS.getTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tus);
        getSupportActionBar().setTitle(CommonUtil.getPageTitle(PAGE_TITLE));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tusService = new TusService(this);
        setViewReferences();
        provideViews();
        setViewListeners();
        AdUtil.showAd(adView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exam_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_alert:
                showAlertDialog();
                break;
            case R.id.action_settings:
                Intent intentSettings = new Intent(TusActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
                break;
            case R.id.action_info:
                Intent intentInfo = new Intent(TusActivity.this, InfoActivity.class);
                startActivity(intentInfo);
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
        adView = findViewById(R.id.adViewTUS);
    }

    private void provideViews() {
        CommonUtil.provideEditTextTrue(CommonUtil.HUNDRED_TWENTY_QUESTIONS, editTextBasicMedicineSciencesTrue, editTextBasicMedicineSciencesFalse, editTextBasicMedicineSciencesNet);
        CommonUtil.provideEditTextFalse(CommonUtil.HUNDRED_TWENTY_QUESTIONS, editTextBasicMedicineSciencesFalse, editTextBasicMedicineSciencesTrue, editTextBasicMedicineSciencesNet);
        CommonUtil.provideEditTextTrue(CommonUtil.HUNDRED_TWENTY_QUESTIONS, editTextClinicalMedicineSciencesTrue, editTextClinicalMedicineSciencesFalse, editTextClinicalMedicineSciencesNet);
        CommonUtil.provideEditTextFalse(CommonUtil.HUNDRED_TWENTY_QUESTIONS, editTextClinicalMedicineSciencesFalse, editTextClinicalMedicineSciencesTrue, editTextClinicalMedicineSciencesNet);
        DateTimeUtil.addCountDown(textViewTUSTime, PAGE_TITLE);
    }

    private TUS getTus() {
        TUS tus = new TUS();
        tus.setBasicMedicineSciencesTrue(ConverterUtil.convertToInteger(editTextBasicMedicineSciencesTrue.getText().toString()));
        tus.setBasicMedicineSciencesFalse(ConverterUtil.convertToInteger(editTextBasicMedicineSciencesFalse.getText().toString()));
        tus.setBasicMedicineSciencesNet(CommonUtil.getNet(tus.getBasicMedicineSciencesTrue(), tus.getBasicMedicineSciencesFalse()));
        tus.setClinicalMedicineSciencesTrue(ConverterUtil.convertToInteger(editTextClinicalMedicineSciencesTrue.getText().toString()));
        tus.setClinicalMedicineSciencesFalse(ConverterUtil.convertToInteger(editTextClinicalMedicineSciencesFalse.getText().toString()));
        tus.setClinicalMedicineSciencesNet(CommonUtil.getNet(tus.getClinicalMedicineSciencesTrue(), tus.getClinicalMedicineSciencesFalse()));
        tus.setGraduateMedicineTPoint(CommonUtil.round(tusService.getGraduateMedicineTPoint(tus.getBasicMedicineSciencesNet(), tus.getClinicalMedicineSciencesNet()), 2));
        tus.setGraduateMedicineKPoint(CommonUtil.round(tusService.getGraduateMedicineKPoint(tus.getBasicMedicineSciencesNet(), tus.getClinicalMedicineSciencesNet()), 2));
        tus.setGraduateMedicineAPoint(CommonUtil.round(tusService.getGraduateMedicineAPoint(tus.getClinicalMedicineSciencesNet()), 2));
        tus.setNotGraduateMedicineTPoint(CommonUtil.round(tusService.getNotGraduateMedicineTPoint(tus.getBasicMedicineSciencesNet()), 2));
        tus.setExamType(ExamsEnum.TUS.getId());
        return tus;
    }

    private void setViewListeners() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResultDialog(getTus());
            }
        });
    }

    private void showResultDialog(TUS tus) {
        final Dialog dialog = new Dialog(TusActivity.this);
        dialog.setContentView(R.layout.dialog_tus);
        TextView textViewGraduateMedicineKPoint = dialog.findViewById(R.id.textViewTUSResultGraduateMedicineKPoint);
        TextView textViewGraduateMedicineTPoint = dialog.findViewById(R.id.textViewTUSResultGraduateMedicineTPoint);
        TextView textViewGraduateMedicineAPoint = dialog.findViewById(R.id.textViewTUSResultGraduateMedicineAPoint);
        TextView textViewNotGraduateMedicineTPoint = dialog.findViewById(R.id.textViewTUSResultNotGraduateMedicineTPoint);
        textViewGraduateMedicineKPoint.setText(String.valueOf(tus.getGraduateMedicineKPoint()));
        textViewGraduateMedicineTPoint.setText(String.valueOf(tus.getGraduateMedicineTPoint()));
        textViewGraduateMedicineAPoint.setText(String.valueOf(tus.getGraduateMedicineAPoint()));
        textViewNotGraduateMedicineTPoint.setText(String.valueOf(tus.getNotGraduateMedicineTPoint()));
        setDialogViewListeners(dialog, tus);
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

    private void setDialogViewListeners(final Dialog dialog, final TUS tus) {
        Button buttonSave = dialog.findViewById(R.id.buttonTUSDialogSave);
        Button buttonClose = dialog.findViewById(R.id.buttonTUSDialogClose);
        final EditText editTextExamName = dialog.findViewById(R.id.editTextTUSDialogExamName);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtil.controlEditTextValue(editTextExamName, getBaseContext())) {
                    tus.setName(editTextExamName.getText().toString());
                    long result = tusService.put(tus);
                    if (result == DatabaseManager.ERROR) {
                        Toast.makeText(TusActivity.this, CommonUtil.PUT_EXAM_ERROR_STRING, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(TusActivity.this, CommonUtil.PUT_EXAM_SUCCESSFUL_STRING, Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                    Intent intent = new Intent(TusActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
