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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.berkay22demirel.sinavpuanhesaplama.Database.DatabaseManager;
import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Model.DGS;
import com.berkay22demirel.sinavpuanhesaplama.Service.DgsService;
import com.berkay22demirel.sinavpuanhesaplama.Util.AdUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.CommonUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.ConverterUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.DateTimeUtil;
import com.google.android.gms.ads.AdView;

public class DgsActivity extends AppCompatActivity {

    DatabaseManager databaseManager;
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
    AdView adView;
    private static String PAGE_TITLE = ExamsEnum.DGS.getTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dgs);
        getSupportActionBar().setTitle(CommonUtil.getPageTitle(PAGE_TITLE));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseManager = new DatabaseManager(this);
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
                Intent intentSettings = new Intent(DgsActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
                break;
            case R.id.action_info:
                Intent intentInfo = new Intent(DgsActivity.this, InfoActivity.class);
                startActivity(intentInfo);
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
        adView = findViewById(R.id.adViewDGS);
    }

    private void provideViews() {
        CommonUtil.provideEditTextTrue(CommonUtil.SIXTY_QUESTIONS, editTextNumericalTrue, editTextNumericalFalse, editTextNumericalNet);
        CommonUtil.provideEditTextFalse(CommonUtil.SIXTY_QUESTIONS, editTextNumericalFalse, editTextNumericalTrue, editTextNumericalNet);
        CommonUtil.provideEditTextTrue(CommonUtil.SIXTY_QUESTIONS, editTextVerbalTrue, editTextVerbalFalse, editTextVerbalNet);
        CommonUtil.provideEditTextFalse(CommonUtil.SIXTY_QUESTIONS, editTextVerbalFalse, editTextVerbalTrue, editTextVerbalNet);
        CommonUtil.setEditTextMaxValue(editTextAssociateDegreeSuccessGrade, 100);
        DateTimeUtil.addCountDown(textViewDGSTime, PAGE_TITLE);
    }

    private DGS getDgs() {
        DGS dgs = new DGS();
        DgsService dgsService = DgsService.getDgsService();
        dgs.setNumericalTrue(ConverterUtil.convertToInteger(editTextNumericalTrue.getText().toString()));
        dgs.setNumericalFalse(ConverterUtil.convertToInteger(editTextNumericalFalse.getText().toString()));
        dgs.setNumericalNet(CommonUtil.getNet(dgs.getNumericalTrue(), dgs.getNumericalFalse()));
        dgs.setVerbalTrue(ConverterUtil.convertToInteger(editTextVerbalTrue.getText().toString()));
        dgs.setVerbalFalse(ConverterUtil.convertToInteger(editTextVerbalFalse.getText().toString()));
        dgs.setVerbalNet(CommonUtil.getNet(dgs.getVerbalTrue(), dgs.getVerbalFalse()));
        dgs.setAssociateDegreeSuccessGrade(ConverterUtil.convertToInteger(editTextAssociateDegreeSuccessGrade.getText().toString()));
        dgs.setBeforeResult(checkBoxBeforeResult.isChecked());
        dgsService.setResult(dgs);
        dgs.setExamType(ExamsEnum.DGS.getId());
        return dgs;
    }

    private void setViewListeners() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResultDialog(getDgs());
            }
        });
    }

    private void showResultDialog(DGS dgs) {
        final Dialog dialog = new Dialog(DgsActivity.this);
        dialog.setContentView(R.layout.dialog_dgs);
        TextView textViewResultNumeric = dialog.findViewById(R.id.textViewDGSResultNumeric);
        TextView textViewResultVerbal = dialog.findViewById(R.id.textViewDGSResultVerbal);
        TextView textViewResultEqualWeight = dialog.findViewById(R.id.textViewDGSResultEqualWeight);
        textViewResultNumeric.setText(String.valueOf(dgs.getNumericalResult()));
        textViewResultVerbal.setText(String.valueOf(dgs.getVerbalResult()));
        textViewResultEqualWeight.setText(String.valueOf(dgs.getEqualWeightResult()));
        setDialogViewListeners(dialog, dgs);
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

    private void setDialogViewListeners(final Dialog dialog, final DGS dgs) {
        Button buttonSave = dialog.findViewById(R.id.buttonDGSDialogSave);
        Button buttonClose = dialog.findViewById(R.id.buttonDGSDialogClose);
        final EditText editTextExamName = dialog.findViewById(R.id.editTextDGSDialogExamName);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtil.controlEditTextValue(editTextExamName, getBaseContext())) {
                    dgs.setName(editTextExamName.getText().toString());
                    long result = databaseManager.put(dgs);
                    if (result == DatabaseManager.ERROR) {
                        Toast.makeText(DgsActivity.this, CommonUtil.PUT_EXAM_ERROR_STRING, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(DgsActivity.this, CommonUtil.PUT_EXAM_SUCCESSFUL_STRING, Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                    Intent intent = new Intent(DgsActivity.this, MainActivity.class);
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
