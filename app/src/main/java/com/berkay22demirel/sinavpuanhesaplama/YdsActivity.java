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

public class YdsActivity extends AppCompatActivity {

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

    private void setCalculateButtonListener() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controlViewValue()) {
                    int languageTrue = ConverterUtil.convertToInteger(editTextLanguageTrue.getText().toString());
                    int languageFalse = ConverterUtil.convertToInteger(editTextLanguageFalse.getText().toString());
                    double languageNet = CommonUtil.getNet(languageTrue, languageFalse);
                    double result = getResult(languageNet);
                    showResultDialog(result);
                }
            }
        });
    }

    private void showResultDialog(double result) {
        final Dialog dialog = new Dialog(YdsActivity.this);
        dialog.setContentView(R.layout.dialog_yds);
        TextView textViewResult = dialog.findViewById(R.id.textViewYDSResult);
        TextView textViewLevel = dialog.findViewById(R.id.textViewYDSLevel);
        textViewResult.setText(String.valueOf(CommonUtil.round(result, 2)));
        textViewLevel.setText(getLevel(result));
        setDialogButtonsListener(dialog);
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

    private double getResult(double languageNet) {
        return languageNet * 1.25;
    }

    private String getLevel(double result) {
        if (result < 50) {
            return "Barajı geçemediniz";
        } else if (result < 60) {
            return "E";
        } else if (result < 70) {
            return "D";
        } else if (result < 80) {
            return "C";
        } else if (result < 90) {
            return "B";
        } else if (result <= 100) {
            return "A";
        }
        return CommonUtil.EMPTY_STRING;
    }

    private void setDialogButtonsListener(final Dialog dialog) {
        Button buttonCalculate = dialog.findViewById(R.id.buttonYDSDialogCalculate);
        Button buttonSave = dialog.findViewById(R.id.buttonYDSDialogSave);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(YdsActivity.this, "Sınav Kaydedildi", Toast.LENGTH_SHORT).show();
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

    private boolean controlViewValue() {
        String languageTrue = editTextLanguageTrue.getText().toString();
        if (languageTrue == null || languageTrue.equals(CommonUtil.EMPTY_STRING)) {
            editTextLanguageTrue.setError(getResources().getString(R.string.error_validate));
            return false;
        }
        return true;
    }
}
