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

public class EusActivity extends AppCompatActivity {

    EditText editTextTrue;
    EditText editTextFalse;
    EditText editTextNet;
    TextView textViewEUSTime;
    Button buttonCalculate;

    private static String PAGE_TITLE = ExamsEnum.EUS.getTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eus);
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
        editTextTrue = findViewById(R.id.editTextEUSTrue);
        editTextFalse = findViewById(R.id.editTextEUSFalse);
        editTextNet = findViewById(R.id.editTextEUSNet);
        textViewEUSTime = findViewById(R.id.textViewEUSTime);
        buttonCalculate = findViewById(R.id.buttonEUSCalculate);
    }

    private void provideViews() {
        CommonUtil.provideEditTextTrue(CommonUtil.HUNDRED_QUESTIONS, editTextTrue, editTextFalse, editTextNet);
        CommonUtil.provideEditTextFalse(CommonUtil.HUNDRED_QUESTIONS, editTextFalse, editTextTrue, editTextNet);
        DateTimeUtil.addCountDown(textViewEUSTime, PAGE_TITLE);
    }

    private void setCalculateButtonListener() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int eusTrue = ConverterUtil.convertToInteger(editTextTrue.getText().toString());
                int eusFalse = ConverterUtil.convertToInteger(editTextFalse.getText().toString());
                double eusNet = CommonUtil.getNet(eusTrue, eusFalse);
                double result = getResult(eusNet);
                showResultDialog(result);
            }
        });
    }

    private void showResultDialog(double result) {
        final Dialog dialog = new Dialog(EusActivity.this);
        dialog.setContentView(R.layout.dialog_eus);
        TextView textViewResult = dialog.findViewById(R.id.textViewEUSResult);
        textViewResult.setText(String.valueOf(CommonUtil.round(result, 2)));
        setDialogButtonsListener(dialog);
        dialog.show();
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(EusActivity.this)
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

    private double getResult(double net) {
        return 26.08172 + net * 0.89214;
    }

    private void setDialogButtonsListener(final Dialog dialog) {
        Button buttonCalculate = dialog.findViewById(R.id.buttonEUSDialogCalculate);
        Button buttonSave = dialog.findViewById(R.id.buttonEUSDialogSave);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EusActivity.this, "Sınav Kaydedildi", Toast.LENGTH_SHORT).show();
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
