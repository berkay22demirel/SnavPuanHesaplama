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
import com.berkay22demirel.sinavpuanhesaplama.Model.ALES;
import com.berkay22demirel.sinavpuanhesaplama.Service.AlesService;
import com.berkay22demirel.sinavpuanhesaplama.Util.CommonUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.ConverterUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.DateTimeUtil;

public class AlesActivity extends AppCompatActivity {

    DatabaseManager databaseManager;
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
        DateTimeUtil.addCountDown(textViewALESTime, PAGE_TITLE);
    }

    private ALES getAles() {
        ALES ales = new ALES();
        AlesService alesService = AlesService.getAlesService();
        ales.setTurkishTrue(ConverterUtil.convertToInteger(editTextTurkishTrue.getText().toString()));
        ales.setTurkishFalse(ConverterUtil.convertToInteger(editTextTurkishFalse.getText().toString()));
        ales.setTurkishNet(CommonUtil.getNet(ales.getTurkishTrue(), ales.getTurkishFalse()));
        ales.setMathsTrue(ConverterUtil.convertToInteger(editTextMathsTrue.getText().toString()));
        ales.setMathsFalse(ConverterUtil.convertToInteger(editTextMathsFalse.getText().toString()));
        ales.setMathsNet(CommonUtil.getNet(ales.getMathsTrue(), ales.getMathsFalse()));
        ales.setNumericalResult(CommonUtil.round(alesService.getNumericalResult(ales.getMathsNet(), ales.getTurkishNet()), 2));
        ales.setVerbalResult(CommonUtil.round(alesService.getVerbalResult(ales.getMathsNet(), ales.getTurkishNet()), 2));
        ales.setEqualWeightResult(CommonUtil.round(alesService.getEqualWeightResult(ales.getMathsNet(), ales.getTurkishNet()), 2));
        ales.setExamType(ExamsEnum.ALES.getId());
        return ales;
    }

    private void setViewListeners() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResultDialog(getAles());
            }
        });
    }

    private void showResultDialog(ALES ales) {
        final Dialog dialog = new Dialog(AlesActivity.this);
        dialog.setContentView(R.layout.dialog_ales);
        TextView textViewNumeric = dialog.findViewById(R.id.textViewALESResultNumeric);
        TextView textViewVerbal = dialog.findViewById(R.id.textViewALESResultVerbal);
        TextView textViewEqualWeight = dialog.findViewById(R.id.textViewALESResultEqualWeight);
        textViewNumeric.setText(String.valueOf(ales.getNumericalResult()));
        textViewVerbal.setText(String.valueOf(ales.getVerbalResult()));
        textViewEqualWeight.setText(String.valueOf(ales.getEqualWeightResult()));
        setDialogViewListeners(dialog, ales);
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

    private void setDialogViewListeners(final Dialog dialog, final ALES ales) {
        Button buttonSave = dialog.findViewById(R.id.buttonALESDialogSave);
        Button buttonClose = dialog.findViewById(R.id.buttonALESDialogClose);
        final EditText editTextExamName = dialog.findViewById(R.id.editTextALESDialogExamName);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ales.setName(editTextExamName.getText().toString());
                long result = databaseManager.put(ales);
                if (result == DatabaseManager.ERROR) {
                    Toast.makeText(AlesActivity.this, CommonUtil.PUT_EXAM_ERROR_STRING, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AlesActivity.this, CommonUtil.PUT_EXAM_SUCCESSFUL_STRING, Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
                Intent intent = new Intent(AlesActivity.this, MainActivity.class);
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

}
