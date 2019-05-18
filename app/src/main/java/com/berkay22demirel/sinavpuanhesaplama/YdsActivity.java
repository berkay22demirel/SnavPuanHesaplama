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
import com.berkay22demirel.sinavpuanhesaplama.Util.CommonUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.ConverterUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.DateTimeUtil;

public class YdsActivity extends AppCompatActivity {

    DatabaseManager databaseManager;
    EditText editTextLanguageTrue;
    EditText editTextLanguageFalse;
    EditText editTextLanguageNet;
    TextView textViewYDSTime;
    Button buttonCalculate;
    private static String PAGE_TITLE = ExamsEnum.YDS.getTitle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yds);
        getSupportActionBar().setTitle(CommonUtil.getPageTitle(PAGE_TITLE));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseManager = new DatabaseManager(this);
        setViewReferences();
        provideViews();
        setViewListeners();
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
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
            case R.id.action_alert:
                showAlertDialog();
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
    }

    private void provideViews() {
        CommonUtil.provideEditTextTrue(CommonUtil.EIGHTY_QUESTIONS, editTextLanguageTrue, editTextLanguageFalse, editTextLanguageNet);
        CommonUtil.provideEditTextFalse(CommonUtil.EIGHTY_QUESTIONS, editTextLanguageFalse, editTextLanguageTrue, editTextLanguageNet);
        DateTimeUtil.addCountDown(textViewYDSTime, PAGE_TITLE);
    }

    private YDS getYds() {
        YDS yds = new YDS();
        YdsService ydsService = YdsService.getYdsService();
        yds.setLanguageTrue(ConverterUtil.convertToInteger(editTextLanguageTrue.getText().toString()));
        yds.setLanguageFalse(ConverterUtil.convertToInteger(editTextLanguageFalse.getText().toString()));
        yds.setLanguageNet(CommonUtil.getNet(yds.getLanguageTrue(), yds.getLanguageFalse()));
        yds.setResult(ydsService.getResult(yds.getLanguageNet()));
        yds.setExamType(ExamsEnum.YDS.getId());
        return yds;
    }

    private void setViewListeners() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controlViewValue()) {
                    showResultDialog(getYds());
                }
            }
        });
    }

    private void showResultDialog(YDS yds) {
        final Dialog dialog = new Dialog(YdsActivity.this);
        YdsService ydsService = YdsService.getYdsService();
        dialog.setContentView(R.layout.dialog_yds);
        TextView textViewResult = dialog.findViewById(R.id.textViewYDSResult);
        TextView textViewLevel = dialog.findViewById(R.id.textViewYDSLevel);
        textViewResult.setText(String.valueOf(CommonUtil.round(yds.getResult(), 2)));
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
                yds.setName(editTextExamName.getText().toString());
                long result = databaseManager.put(yds);
                if (result == DatabaseManager.ERROR) {
                    Toast.makeText(YdsActivity.this, CommonUtil.PUT_EXAM_ERROR_STRING, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(YdsActivity.this, CommonUtil.PUT_EXAM_SUCCESSFUL_STRING, Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
                Intent intent = new Intent(YdsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private boolean controlViewValue() {
        String languageTrue = editTextLanguageTrue.getText().toString();
        if (languageTrue == null || languageTrue.equals(CommonUtil.EMPTY_STRING)) {
            editTextLanguageTrue.setError(getResources().getString(R.string.error_validate));
            return false;
        }
        return true;
    }
}
