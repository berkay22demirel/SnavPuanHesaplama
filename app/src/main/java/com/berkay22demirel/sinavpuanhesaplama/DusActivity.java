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
import com.berkay22demirel.sinavpuanhesaplama.Model.DUS;
import com.berkay22demirel.sinavpuanhesaplama.Service.DusService;
import com.berkay22demirel.sinavpuanhesaplama.Util.CommonUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.ConverterUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.DateTimeUtil;

public class DusActivity extends AppCompatActivity {

    DatabaseManager databaseManager;
    EditText editTextBasicSciencesTrue;
    EditText editTextBasicSciencesFalse;
    EditText editTextBasicSciencesNet;
    EditText editTextClinicalSciencesTrue;
    EditText editTextClinicalSciencesFalse;
    EditText editTextClinicalSciencesNet;
    TextView textViewDUSTime;
    Button buttonCalculate;

    private static String PAGE_TITLE = ExamsEnum.DUS.getTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dus);
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
        editTextBasicSciencesTrue = findViewById(R.id.editTextDUSBasicSciencesTrue);
        editTextBasicSciencesFalse = findViewById(R.id.editTextDUSBasicSciencesFalse);
        editTextBasicSciencesNet = findViewById(R.id.editTextDUSBasicSciencesNet);
        editTextClinicalSciencesTrue = findViewById(R.id.editTextDUSClinicalSciencesTrue);
        editTextClinicalSciencesFalse = findViewById(R.id.editTextDUSClinicalSciencesFalse);
        editTextClinicalSciencesNet = findViewById(R.id.editTextDUSClinicalSciencesNet);
        textViewDUSTime = findViewById(R.id.textViewDUSTime);
        buttonCalculate = findViewById(R.id.buttonDUSCalculate);
    }

    private void provideViews() {
        CommonUtil.provideEditTextTrue(CommonUtil.FOURTY_QUESTIONS, editTextBasicSciencesTrue, editTextBasicSciencesFalse, editTextBasicSciencesNet);
        CommonUtil.provideEditTextFalse(CommonUtil.FOURTY_QUESTIONS, editTextBasicSciencesFalse, editTextBasicSciencesTrue, editTextBasicSciencesNet);
        CommonUtil.provideEditTextTrue(CommonUtil.EIGHTY_QUESTIONS, editTextClinicalSciencesTrue, editTextClinicalSciencesFalse, editTextClinicalSciencesNet);
        CommonUtil.provideEditTextFalse(CommonUtil.EIGHTY_QUESTIONS, editTextClinicalSciencesFalse, editTextClinicalSciencesTrue, editTextClinicalSciencesNet);
        DateTimeUtil.addCountDown(textViewDUSTime, PAGE_TITLE);
    }

    private DUS getDus() {
        DUS dus = new DUS();
        DusService dusService = DusService.getDusService();
        dus.setBasicSciencesTrue(ConverterUtil.convertToInteger(editTextBasicSciencesTrue.getText().toString()));
        dus.setBasicSciencesFalse(ConverterUtil.convertToInteger(editTextBasicSciencesFalse.getText().toString()));
        dus.setBasicSciencesNet(CommonUtil.getNet(dus.getBasicSciencesTrue(), dus.getBasicSciencesFalse()));
        dus.setClinicalSciencesTrue(ConverterUtil.convertToInteger(editTextClinicalSciencesTrue.getText().toString()));
        dus.setClinicalSciencesFalse(ConverterUtil.convertToInteger(editTextClinicalSciencesFalse.getText().toString()));
        dus.setClinicalSciencesNet(CommonUtil.getNet(dus.getClinicalSciencesTrue(), dus.getClinicalSciencesFalse()));
        dus.setResult(dusService.getResult(dus.getBasicSciencesNet(), dus.getClinicalSciencesNet()));
        dus.setExamType(ExamsEnum.DUS.getId());
        return dus;
    }

    private void setViewListeners() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DUS dus = getDus();
                showResultDialog(dus);
            }
        });
    }

    private void showResultDialog(DUS dus) {
        final Dialog dialog = new Dialog(DusActivity.this);
        dialog.setContentView(R.layout.dialog_dus);
        TextView textViewResult = dialog.findViewById(R.id.textViewDUSResult);
        textViewResult.setText(String.valueOf(CommonUtil.round(dus.getResult(), 2)));
        setDialogViewListeners(dialog, dus);
        dialog.show();
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(DusActivity.this)
                .setTitle("UYARI!")
                .setMessage("*Sınav Sonucunuz 2015 katsayılarına göre hesaplanmıştır. Gireceğiniz sınavda ufak farklılıklar gösterebilir.")
                .setPositiveButton(getResources().getText(R.string.button_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setIcon(getResources().getDrawable(R.drawable.alert_dialog_icon))
                .show();
    }


    private void setDialogViewListeners(final Dialog dialog, final DUS dus) {
        Button buttonSave = dialog.findViewById(R.id.buttonDUSDialogSave);
        Button buttonClose = dialog.findViewById(R.id.buttonDUSDialogClose);
        final EditText editTextExamName = dialog.findViewById(R.id.editTextDUSDialogExamName);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dus.setName(editTextExamName.getText().toString());
                long result = databaseManager.put(dus);
                if (result == DatabaseManager.ERROR) {
                    Toast.makeText(DusActivity.this, CommonUtil.PUT_EXAM_ERROR_STRING, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(DusActivity.this, CommonUtil.PUT_EXAM_SUCCESSFUL_STRING, Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
                Intent intent = new Intent(DusActivity.this, MainActivity.class);
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
