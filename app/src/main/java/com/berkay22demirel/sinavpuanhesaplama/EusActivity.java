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
import com.berkay22demirel.sinavpuanhesaplama.Model.EUS;
import com.berkay22demirel.sinavpuanhesaplama.Service.EusService;
import com.berkay22demirel.sinavpuanhesaplama.Util.CommonUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.ConverterUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.DateTimeUtil;

public class EusActivity extends AppCompatActivity {

    DatabaseManager databaseManager;
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
        databaseManager = new DatabaseManager(this);
        setViewReferences();
        provideViews();
        setViewListener();
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

    private EUS getEus() {
        EUS eus = new EUS();
        EusService eusService = EusService.getEusService();
        eus.setEusTrue(ConverterUtil.convertToInteger(editTextTrue.getText().toString()));
        eus.setEusFalse(ConverterUtil.convertToInteger(editTextFalse.getText().toString()));
        eus.setEusNet(CommonUtil.getNet(eus.getEusTrue(), eus.getEusFalse()));
        eus.setResult(eusService.getResult(eus.getEusNet()));
        eus.setExamType(ExamsEnum.EUS.getId());
        return eus;
    }

    private void setViewListener() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResultDialog(getEus());
            }
        });
    }

    private void showResultDialog(EUS eus) {
        final Dialog dialog = new Dialog(EusActivity.this);
        dialog.setContentView(R.layout.dialog_eus);
        TextView textViewResult = dialog.findViewById(R.id.textViewEUSResult);
        textViewResult.setText(String.valueOf(CommonUtil.round(eus.getResult(), 2)));
        setDialogViewListeners(dialog, eus);
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

    private void setDialogViewListeners(final Dialog dialog, final EUS eus) {
        Button buttonSave = dialog.findViewById(R.id.buttonEUSDialogSave);
        Button buttonClose = dialog.findViewById(R.id.buttonEUSDialogClose);
        final EditText editTextExamName = dialog.findViewById(R.id.editTextEUSDialogExamName);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eus.setName(editTextExamName.getText().toString());
                long result = databaseManager.put(eus);
                if (result == DatabaseManager.ERROR) {
                    Toast.makeText(EusActivity.this, CommonUtil.PUT_EXAM_ERROR_STRING, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(EusActivity.this, CommonUtil.PUT_EXAM_SUCCESSFUL_STRING, Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
                Intent intent = new Intent(EusActivity.this, MainActivity.class);
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
