package com.berkay22demirel.sinavpuanhesaplama;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Util.CommonUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.ConverterUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.DateTimeUtil;

public class EkpssActivity extends AppCompatActivity {

    EditText editTextGeneralAbilityTrue;
    EditText editTextGeneralAbilityFalse;
    EditText editTextGeneralAbilityNet;
    EditText editTextGeneralKnowledgeTrue;
    EditText editTextGeneralKnowledgeFalse;
    EditText editTextGeneralKnowledgeNet;
    Spinner spinnerEKPSSType;
    TextView textViewEKPSSTime;
    Button buttonCalculate;
    private static final String PAGE_TITLE = ExamsEnum.EKPSS.getTitle();
    private static final String SECONDARY_EDUCATION_STRING = "Ortaöğretim";
    private static final int SECONDARY_EDUCATION_CODE = 0;
    private static final String ASSOCIATE_DEGREE_STRING = "Önlisans";
    private static final int ASSOCIATE_DEGREE_CODE = 1;
    private static final String BACHELOR_DEGREE_STRING = "Lisans";
    private static final int BACHELOR_DEGREE_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekpss);
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
        editTextGeneralAbilityTrue = findViewById(R.id.editTextEKPSSGeneralAbilityTrue);
        editTextGeneralAbilityFalse = findViewById(R.id.editTextEKPSSGeneralAbilityFalse);
        editTextGeneralAbilityNet = findViewById(R.id.editTextEKPSSGeneralAbilityNet);
        editTextGeneralKnowledgeTrue = findViewById(R.id.editTextEKPSSGeneralKnowledgeTrue);
        editTextGeneralKnowledgeFalse = findViewById(R.id.editTextEKPSSGeneralKnowledgeFalse);
        editTextGeneralKnowledgeNet = findViewById(R.id.editTextEKPSSGeneralKnowledgeNet);
        spinnerEKPSSType = findViewById(R.id.spinnerEKPSSType);
        textViewEKPSSTime = findViewById(R.id.textViewEKPSSTime);
        buttonCalculate = findViewById(R.id.buttonEKPSSCalculate);
    }

    private void provideViews() {
        CommonUtil.provideEditTextTrue(CommonUtil.THIRTY_QUESTIONS, editTextGeneralAbilityTrue, editTextGeneralAbilityFalse, editTextGeneralAbilityNet);
        CommonUtil.provideEditTextFalse(CommonUtil.THIRTY_QUESTIONS, editTextGeneralAbilityFalse, editTextGeneralAbilityTrue, editTextGeneralAbilityNet);
        CommonUtil.provideEditTextTrue(CommonUtil.THIRTY_QUESTIONS, editTextGeneralKnowledgeTrue, editTextGeneralKnowledgeFalse, editTextGeneralKnowledgeNet);
        CommonUtil.provideEditTextFalse(CommonUtil.THIRTY_QUESTIONS, editTextGeneralKnowledgeFalse, editTextGeneralKnowledgeTrue, editTextGeneralKnowledgeNet);
        String[] ekpssType = {SECONDARY_EDUCATION_STRING, ASSOCIATE_DEGREE_STRING, BACHELOR_DEGREE_STRING};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ekpssType);
        spinnerEKPSSType.setAdapter(adapter);
        DateTimeUtil.addCountDown(textViewEKPSSTime, PAGE_TITLE);
    }

    private void setCalculateButtonListener() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int generalAbilityTrue = ConverterUtil.convertToInteger(editTextGeneralAbilityTrue.getText().toString());
                int generalAbilityFalse = ConverterUtil.convertToInteger(editTextGeneralAbilityFalse.getText().toString());
                int generalKnowledgeTrue = ConverterUtil.convertToInteger(editTextGeneralKnowledgeTrue.getText().toString());
                int generalKnowledgeFalse = ConverterUtil.convertToInteger(editTextGeneralKnowledgeFalse.getText().toString());
                int ekpssType = spinnerEKPSSType.getSelectedItemPosition();
                double generalAbilityNet = CommonUtil.getNet(generalAbilityTrue, generalAbilityFalse);
                double generalKnowledgeNet = CommonUtil.getNet(generalKnowledgeTrue, generalKnowledgeFalse);
                double result = getResult(generalAbilityNet, generalKnowledgeNet, ekpssType);
                showResultDialog(result);
            }
        });
    }

    private void showResultDialog(double result) {
        final Dialog dialog = new Dialog(EkpssActivity.this);
        dialog.setContentView(R.layout.dialog_ekpss);
        TextView textViewResult = dialog.findViewById(R.id.textViewEKPSSResult);
        textViewResult.setText(String.valueOf(CommonUtil.round(result, 2)));
        setDialogButtonsListener(dialog);
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

    private double getResult(double generalAbilityNet, double generalKnowledgeNet, int ekpssType) {
        if (generalAbilityNet != 0.0 || generalKnowledgeNet != 0.0) {
            if (ekpssType == SECONDARY_EDUCATION_CODE) {
                return 57.420 + generalAbilityNet * 0.637 + generalKnowledgeNet * 0.783;
            } else if (ekpssType == ASSOCIATE_DEGREE_CODE) {
                return 55.262 + generalAbilityNet * 0.772 + generalKnowledgeNet * 0.725;
            } else if (ekpssType == BACHELOR_DEGREE_CODE) {
                return 50.906 + generalAbilityNet * 0.878 + generalKnowledgeNet * 0.759;
            }
        }
        return 0.0;
    }

    private void setDialogButtonsListener(final Dialog dialog) {
        Button buttonCalculate = dialog.findViewById(R.id.buttonEKPSSDialogCalculate);
        Button buttonSave = dialog.findViewById(R.id.buttonEKPSSDialogSave);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EkpssActivity.this, "Sınav Kaydedildi", Toast.LENGTH_SHORT).show();
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
