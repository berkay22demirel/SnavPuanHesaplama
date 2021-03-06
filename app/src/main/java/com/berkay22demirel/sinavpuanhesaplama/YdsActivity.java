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
import com.berkay22demirel.sinavpuanhesaplama.Model.YDS;
import com.berkay22demirel.sinavpuanhesaplama.Service.YdsService;
import com.berkay22demirel.sinavpuanhesaplama.Util.AdUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.CommonUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.ConverterUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.DateTimeUtil;
import com.google.android.gms.ads.AdView;

public class YdsActivity extends AppCompatActivity {

    YdsService ydsService;
    EditText editTextLanguageTrue;
    EditText editTextLanguageFalse;
    EditText editTextLanguageNet;
    TextView textViewYDSTime;
    Button buttonCalculate;
    AdView adView;
    private static String PAGE_TITLE = ExamsEnum.YDS.getTitle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yds);
        getSupportActionBar().setTitle(CommonUtil.getPageTitle(PAGE_TITLE));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ydsService = new YdsService(this);
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
                Intent intentSettings = new Intent(YdsActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
                break;
            case R.id.action_info:
                Intent intentInfo = new Intent(YdsActivity.this, InfoActivity.class);
                startActivity(intentInfo);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setViewReferences() {
        editTextLanguageTrue = findViewById(R.id.editTextYDSLanguageTrue);
        editTextLanguageFalse = findViewById(R.id.editTextYDSLanguageFalse);
        editTextLanguageNet = findViewById(R.id.editTextYDSLanguageNet);
        textViewYDSTime = findViewById(R.id.textViewYDSTime);
        buttonCalculate = findViewById(R.id.buttonYDSCalculate);
        adView = findViewById(R.id.adViewYDS);
    }

    private void provideViews() {
        CommonUtil.provideEditTextTrue(CommonUtil.EIGHTY_QUESTIONS, editTextLanguageTrue, editTextLanguageFalse, editTextLanguageNet);
        CommonUtil.provideEditTextFalse(CommonUtil.EIGHTY_QUESTIONS, editTextLanguageFalse, editTextLanguageTrue, editTextLanguageNet);
        DateTimeUtil.addCountDown(textViewYDSTime, PAGE_TITLE);
    }

    private YDS getYds() {
        YDS yds = new YDS();
        yds.setLanguageTrue(ConverterUtil.convertToInteger(editTextLanguageTrue.getText().toString()));
        yds.setLanguageFalse(ConverterUtil.convertToInteger(editTextLanguageFalse.getText().toString()));
        yds.setLanguageNet(CommonUtil.getNet(yds.getLanguageTrue(), yds.getLanguageFalse()));
        yds.setResult(CommonUtil.round(ydsService.getResult(yds.getLanguageNet()), 2));
        yds.setExamType(ExamsEnum.YDS.getId());
        return yds;
    }

    private void setViewListeners() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtil.controlEditTextValue(editTextLanguageTrue, getBaseContext())) {
                    showResultDialog(getYds());
                }
            }
        });
    }

    private void showResultDialog(YDS yds) {
        final Dialog dialog = new Dialog(YdsActivity.this);
        dialog.setContentView(R.layout.dialog_yds);
        TextView textViewResult = dialog.findViewById(R.id.textViewYDSResult);
        TextView textViewLevel = dialog.findViewById(R.id.textViewYDSLevel);
        textViewResult.setText(String.valueOf(yds.getResult()));
        textViewLevel.setText(ydsService.getLevel(yds.getResult()));
        setDialogViewListeners(dialog, yds);
        dialog.show();
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(YdsActivity.this)
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

    private void setDialogViewListeners(final Dialog dialog, final YDS yds) {
        Button buttonSave = dialog.findViewById(R.id.buttonYDSDialogSave);
        Button buttonClose = dialog.findViewById(R.id.buttonYDSDialogClose);
        final EditText editTextExamName = dialog.findViewById(R.id.editTextYDSDialogExamName);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtil.controlEditTextValue(editTextExamName, getBaseContext())) {
                    yds.setName(editTextExamName.getText().toString());
                    long result = ydsService.put(yds);
                    if (result == DatabaseManager.ERROR) {
                        Toast.makeText(YdsActivity.this, CommonUtil.PUT_EXAM_ERROR_STRING, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(YdsActivity.this, CommonUtil.PUT_EXAM_SUCCESSFUL_STRING, Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                    Intent intent = new Intent(YdsActivity.this, MainActivity.class);
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
