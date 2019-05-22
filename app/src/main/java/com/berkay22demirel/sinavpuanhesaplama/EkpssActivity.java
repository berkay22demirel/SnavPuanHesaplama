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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.berkay22demirel.sinavpuanhesaplama.Database.DatabaseManager;
import com.berkay22demirel.sinavpuanhesaplama.Enum.EkpssTypeEnum;
import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Model.EKPSS;
import com.berkay22demirel.sinavpuanhesaplama.Service.EkpssService;
import com.berkay22demirel.sinavpuanhesaplama.Util.AdUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.CommonUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.ConverterUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.DateTimeUtil;
import com.google.android.gms.ads.AdView;

public class EkpssActivity extends AppCompatActivity {

    EkpssService ekpssService;
    EditText editTextGeneralAbilityTrue;
    EditText editTextGeneralAbilityFalse;
    EditText editTextGeneralAbilityNet;
    EditText editTextGeneralKnowledgeTrue;
    EditText editTextGeneralKnowledgeFalse;
    EditText editTextGeneralKnowledgeNet;
    Spinner spinnerEKPSSType;
    TextView textViewEKPSSTime;
    Button buttonCalculate;
    AdView adView;
    private static final String PAGE_TITLE = ExamsEnum.EKPSS.getTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekpss);
        getSupportActionBar().setTitle(CommonUtil.getPageTitle(PAGE_TITLE));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ekpssService = new EkpssService(this);
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
                Intent intentSettings = new Intent(EkpssActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
                break;
            case R.id.action_info:
                Intent intentInfo = new Intent(EkpssActivity.this, InfoActivity.class);
                startActivity(intentInfo);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setViewReferences() {
        editTextGeneralAbilityTrue = findViewById(R.id.editTextEKPSSGeneralAbilityTrue);
        editTextGeneralAbilityFalse = findViewById(R.id.editTextEKPSSGeneralAbilityFalse);
        editTextGeneralAbilityNet = findViewById(R.id.editTextEKPSSGeneralAbilityNet);
        editTextGeneralKnowledgeTrue = findViewById(R.id.editTextEKPSSGeneralKnowledgeTrue);
        editTextGeneralKnowledgeFalse = findViewById(R.id.editTextEKPSSGeneralKnowledgeFalse);
        editTextGeneralKnowledgeNet = findViewById(R.id.editTextEKPSSGeneralKnowledgeNet);
        spinnerEKPSSType = findViewById(R.id.spinnerEKPSSType);
        textViewEKPSSTime = findViewById(R.id.textViewEKPSSTime);
        buttonCalculate = findViewById(R.id.buttonEKPSSCalculate);
        adView = findViewById(R.id.adViewEKPSS);
    }

    private void provideViews() {
        CommonUtil.provideEditTextTrue(CommonUtil.THIRTY_QUESTIONS, editTextGeneralAbilityTrue, editTextGeneralAbilityFalse, editTextGeneralAbilityNet);
        CommonUtil.provideEditTextFalse(CommonUtil.THIRTY_QUESTIONS, editTextGeneralAbilityFalse, editTextGeneralAbilityTrue, editTextGeneralAbilityNet);
        CommonUtil.provideEditTextTrue(CommonUtil.THIRTY_QUESTIONS, editTextGeneralKnowledgeTrue, editTextGeneralKnowledgeFalse, editTextGeneralKnowledgeNet);
        CommonUtil.provideEditTextFalse(CommonUtil.THIRTY_QUESTIONS, editTextGeneralKnowledgeFalse, editTextGeneralKnowledgeTrue, editTextGeneralKnowledgeNet);
        String[] ekpssType = EkpssTypeEnum.getEkpssTypes();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ekpssType);
        spinnerEKPSSType.setAdapter(adapter);
        DateTimeUtil.addCountDown(textViewEKPSSTime, PAGE_TITLE);
    }

    private EKPSS getEkpss() {
        EKPSS ekpss = new EKPSS();
        ekpss.setGeneralAbilityTrue(ConverterUtil.convertToInteger(editTextGeneralAbilityTrue.getText().toString()));
        ekpss.setGeneralAbilityFalse(ConverterUtil.convertToInteger(editTextGeneralAbilityFalse.getText().toString()));
        ekpss.setGeneralAbilityNet(CommonUtil.getNet(ekpss.getGeneralAbilityTrue(), ekpss.getGeneralAbilityFalse()));
        ekpss.setGeneralKnowledgeTrue(ConverterUtil.convertToInteger(editTextGeneralKnowledgeTrue.getText().toString()));
        ekpss.setGeneralKnowledgeFalse(ConverterUtil.convertToInteger(editTextGeneralKnowledgeFalse.getText().toString()));
        ekpss.setGeneralKnowledgeNet(CommonUtil.getNet(ekpss.getGeneralKnowledgeTrue(), ekpss.getGeneralKnowledgeFalse()));
        ekpss.setExamSubType(spinnerEKPSSType.getSelectedItemPosition());
        ekpss.setResult(CommonUtil.round(ekpssService.getResult(ekpss.getGeneralAbilityNet(), ekpss.getGeneralKnowledgeNet(), ekpss.getExamSubType()), 2));
        ekpss.setExamType(ExamsEnum.EKPSS.getId());
        return ekpss;
    }

    private void setViewListeners() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResultDialog(getEkpss());
            }
        });
    }

    private void showResultDialog(EKPSS ekpss) {
        final Dialog dialog = new Dialog(EkpssActivity.this);
        dialog.setContentView(R.layout.dialog_ekpss);
        TextView textViewResult = dialog.findViewById(R.id.textViewEKPSSResult);
        textViewResult.setText(String.valueOf(ekpss.getResult()));
        setDialogViewListeners(dialog, ekpss);
        dialog.show();
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(EkpssActivity.this)
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

    private void setDialogViewListeners(final Dialog dialog, final EKPSS ekpss) {
        Button buttonSave = dialog.findViewById(R.id.buttonEKPSSDialogSave);
        Button buttonClose = dialog.findViewById(R.id.buttonEKPSSDialogClose);
        final EditText editTextExamName = dialog.findViewById(R.id.editTextEKPSSDialogExamName);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtil.controlEditTextValue(editTextExamName, getBaseContext())) {
                    ekpss.setName(editTextExamName.getText().toString());
                    long result = ekpssService.put(ekpss);
                    if (result == DatabaseManager.ERROR) {
                        Toast.makeText(EkpssActivity.this, CommonUtil.PUT_EXAM_ERROR_STRING, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(EkpssActivity.this, CommonUtil.PUT_EXAM_SUCCESSFUL_STRING, Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                    Intent intent = new Intent(EkpssActivity.this, MainActivity.class);
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
