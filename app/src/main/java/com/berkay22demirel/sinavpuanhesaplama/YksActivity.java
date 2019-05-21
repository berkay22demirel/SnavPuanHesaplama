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
import com.berkay22demirel.sinavpuanhesaplama.Model.YKS;
import com.berkay22demirel.sinavpuanhesaplama.Service.YksService;
import com.berkay22demirel.sinavpuanhesaplama.Util.CommonUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.ConverterUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.DateTimeUtil;

public class YksActivity extends AppCompatActivity {

    DatabaseManager databaseManager;
    EditText editTextDiplomaGrade;
    EditText editTextTurkishTrue;
    EditText editTextTurkishFalse;
    EditText editTextTurkishNet;
    EditText editTextSocialTrue;
    EditText editTextSocialFalse;
    EditText editTextSocialNet;
    EditText editTextMathsTrue;
    EditText editTextMathsFalse;
    EditText editTextMathsNet;
    EditText editTextScienceTrue;
    EditText editTextScienceFalse;
    EditText editTextScienceNet;
    EditText editTextMaths2True;
    EditText editTextMaths2False;
    EditText editTextMaths2Net;
    EditText editTextPhysicsTrue;
    EditText editTextPhysicsFalse;
    EditText editTextPhysicsNet;
    EditText editTextChemistryTrue;
    EditText editTextChemistryFalse;
    EditText editTextChemistryNet;
    EditText editTextBiologyTrue;
    EditText editTextBiologyFalse;
    EditText editTextBiologyNet;
    EditText editTextLiteratureTrue;
    EditText editTextLiteratureFalse;
    EditText editTextLiteratureNet;
    EditText editTextHistoryTrue;
    EditText editTextHistoryFalse;
    EditText editTextHistoryNet;
    EditText editTextGeographicsTrue;
    EditText editTextGeographicsFalse;
    EditText editTextGeographicsNet;
    EditText editTextHistory2True;
    EditText editTextHistory2False;
    EditText editTextHistory2Net;
    EditText editTextGeographics2True;
    EditText editTextGeographics2False;
    EditText editTextGeographics2Net;
    EditText editTextPhilosophyTrue;
    EditText editTextPhilosophyFalse;
    EditText editTextPhilosophyNet;
    EditText editTextReligionTrue;
    EditText editTextReligionFalse;
    EditText editTextReligionNet;
    EditText editTextLanguageTrue;
    EditText editTextLanguageFalse;
    EditText editTextLanguageNet;
    TextView textViewYKSTime;
    CheckBox checkBoxDiplamaNotification;
    Button buttonCalculate;

    private static String PAGE_TITLE = ExamsEnum.YKS.getTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yks);
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
            case R.id.action_alert:
                showAlertDialog();
                break;
            case R.id.action_settings:
                Intent intentSettings = new Intent(YksActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
                break;
            case R.id.action_info:
                Intent intentInfo = new Intent(YksActivity.this, InfoActivity.class);
                startActivity(intentInfo);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setViewReferences() {
        editTextDiplomaGrade = findViewById(R.id.editTextYKSDiplomaGrade);
        editTextTurkishTrue = findViewById(R.id.editTextYKSTurkishTrue);
        editTextTurkishFalse = findViewById(R.id.editTextYKSTurkishFalse);
        editTextTurkishNet = findViewById(R.id.editTextYKSTurkishNet);
        editTextSocialTrue = findViewById(R.id.editTextYKSSocialTrue);
        editTextSocialFalse = findViewById(R.id.editTextYKSSocialFalse);
        editTextSocialNet = findViewById(R.id.editTextYKSSocialNet);
        editTextMathsTrue = findViewById(R.id.editTextYKSMathsTrue);
        editTextMathsFalse = findViewById(R.id.editTextYKSMathsFalse);
        editTextMathsNet = findViewById(R.id.editTextYKSMathsNet);
        editTextScienceTrue = findViewById(R.id.editTextYKSScienceTrue);
        editTextScienceFalse = findViewById(R.id.editTextYKSScienceFalse);
        editTextScienceNet = findViewById(R.id.editTextYKSScienceNet);
        editTextMaths2True = findViewById(R.id.editTextYKSMaths2True);
        editTextMaths2False = findViewById(R.id.editTextYKSMaths2False);
        editTextMaths2Net = findViewById(R.id.editTextYKSMaths2Net);
        editTextPhysicsTrue = findViewById(R.id.editTextYKSPhysicsTrue);
        editTextPhysicsFalse = findViewById(R.id.editTextYKSPhysicsFalse);
        editTextPhysicsNet = findViewById(R.id.editTextYKSPhysicsNet);
        editTextChemistryTrue = findViewById(R.id.editTextYKSChemistryTrue);
        editTextChemistryFalse = findViewById(R.id.editTextYKSChemistryFalse);
        editTextChemistryNet = findViewById(R.id.editTextYKSChemistryNet);
        editTextBiologyTrue = findViewById(R.id.editTextYKSBiologyTrue);
        editTextBiologyFalse = findViewById(R.id.editTextYKSBiologyFalse);
        editTextBiologyNet = findViewById(R.id.editTextYKSBiologyNet);
        editTextLiteratureTrue = findViewById(R.id.editTextYKSLiteratureTrue);
        editTextLiteratureFalse = findViewById(R.id.editTextYKSLiteratureFalse);
        editTextLiteratureNet = findViewById(R.id.editTextYKSLiteratureNet);
        editTextHistoryTrue = findViewById(R.id.editTextYKSHistoryTrue);
        editTextHistoryFalse = findViewById(R.id.editTextYKSHistoryFalse);
        editTextHistoryNet = findViewById(R.id.editTextYKSHistoryNet);
        editTextGeographicsTrue = findViewById(R.id.editTextYKSGeographicsTrue);
        editTextGeographicsFalse = findViewById(R.id.editTextYKSGeographicsFalse);
        editTextGeographicsNet = findViewById(R.id.editTextYKSGeographicsNet);
        editTextHistory2True = findViewById(R.id.editTextYKSHistory2True);
        editTextHistory2False = findViewById(R.id.editTextYKSHistory2False);
        editTextHistory2Net = findViewById(R.id.editTextYKSHistory2Net);
        editTextGeographics2True = findViewById(R.id.editTextYKSGeographics2True);
        editTextGeographics2False = findViewById(R.id.editTextYKSGeographics2False);
        editTextGeographics2Net = findViewById(R.id.editTextYKSGeographics2Net);
        editTextPhilosophyTrue = findViewById(R.id.editTextYKSPhilosophyTrue);
        editTextPhilosophyFalse = findViewById(R.id.editTextYKSPhilosophyFalse);
        editTextPhilosophyNet = findViewById(R.id.editTextYKSPhilosophyNet);
        editTextReligionTrue = findViewById(R.id.editTextYKSReligionTrue);
        editTextReligionFalse = findViewById(R.id.editTextYKSReligionFalse);
        editTextReligionNet = findViewById(R.id.editTextYKSReligionNet);
        editTextLanguageTrue = findViewById(R.id.editTextYKSLanguageTrue);
        editTextLanguageFalse = findViewById(R.id.editTextYKSLanguageFalse);
        editTextLanguageNet = findViewById(R.id.editTextYKSLanguageNet);
        textViewYKSTime = findViewById(R.id.textViewYKSTime);
        checkBoxDiplamaNotification = findViewById(R.id.checkBoxYKSDiplomaNotification);
        buttonCalculate = findViewById(R.id.buttonYKSCalculate);
        DateTimeUtil.addCountDown(textViewYKSTime, PAGE_TITLE);
    }

    private void provideViews() {
        CommonUtil.provideEditTextTrue(CommonUtil.FOURTY_QUESTIONS, editTextTurkishTrue, editTextTurkishFalse, editTextTurkishNet);
        CommonUtil.provideEditTextFalse(CommonUtil.FOURTY_QUESTIONS, editTextTurkishFalse, editTextTurkishTrue, editTextTurkishNet);
        CommonUtil.provideEditTextTrue(CommonUtil.TWENTY_QUESTIONS, editTextSocialTrue, editTextSocialFalse, editTextSocialNet);
        CommonUtil.provideEditTextFalse(CommonUtil.TWENTY_QUESTIONS, editTextSocialFalse, editTextSocialTrue, editTextSocialNet);
        CommonUtil.provideEditTextTrue(CommonUtil.FOURTY_QUESTIONS, editTextMathsTrue, editTextMathsFalse, editTextMathsNet);
        CommonUtil.provideEditTextFalse(CommonUtil.FOURTY_QUESTIONS, editTextMathsFalse, editTextMathsTrue, editTextMathsNet);
        CommonUtil.provideEditTextTrue(CommonUtil.TWENTY_QUESTIONS, editTextScienceTrue, editTextScienceFalse, editTextScienceNet);
        CommonUtil.provideEditTextFalse(CommonUtil.TWENTY_QUESTIONS, editTextScienceFalse, editTextScienceTrue, editTextScienceNet);
        CommonUtil.provideEditTextTrue(CommonUtil.FOURTY_QUESTIONS, editTextMaths2True, editTextMaths2False, editTextMaths2Net);
        CommonUtil.provideEditTextFalse(CommonUtil.FOURTY_QUESTIONS, editTextMaths2False, editTextMaths2True, editTextMaths2Net);
        CommonUtil.provideEditTextTrue(CommonUtil.FOURTEEN_QUESTIONS, editTextPhysicsTrue, editTextPhysicsFalse, editTextPhysicsNet);
        CommonUtil.provideEditTextFalse(CommonUtil.FOURTEEN_QUESTIONS, editTextPhysicsFalse, editTextPhysicsTrue, editTextPhysicsNet);
        CommonUtil.provideEditTextTrue(CommonUtil.THIRTEEN_QUESTIONS, editTextChemistryTrue, editTextChemistryFalse, editTextChemistryNet);
        CommonUtil.provideEditTextFalse(CommonUtil.THIRTEEN_QUESTIONS, editTextChemistryFalse, editTextChemistryTrue, editTextChemistryNet);
        CommonUtil.provideEditTextTrue(CommonUtil.THIRTEEN_QUESTIONS, editTextBiologyTrue, editTextBiologyFalse, editTextBiologyNet);
        CommonUtil.provideEditTextFalse(CommonUtil.THIRTEEN_QUESTIONS, editTextBiologyFalse, editTextBiologyTrue, editTextBiologyNet);
        CommonUtil.provideEditTextTrue(CommonUtil.TWENTY_FOUR_QUESTIONS, editTextLiteratureTrue, editTextLiteratureFalse, editTextLiteratureNet);
        CommonUtil.provideEditTextFalse(CommonUtil.TWENTY_FOUR_QUESTIONS, editTextLiteratureFalse, editTextLiteratureTrue, editTextLiteratureNet);
        CommonUtil.provideEditTextTrue(CommonUtil.TEN_QUESTIONS, editTextHistoryTrue, editTextHistoryFalse, editTextHistoryNet);
        CommonUtil.provideEditTextFalse(CommonUtil.TEN_QUESTIONS, editTextHistoryFalse, editTextHistoryTrue, editTextHistoryNet);
        CommonUtil.provideEditTextTrue(CommonUtil.SIX_QUESTIONS, editTextGeographicsTrue, editTextGeographicsFalse, editTextGeographicsNet);
        CommonUtil.provideEditTextFalse(CommonUtil.SIX_QUESTIONS, editTextGeographicsFalse, editTextGeographicsTrue, editTextGeographicsNet);
        CommonUtil.provideEditTextTrue(CommonUtil.ELEVEN_QUESTIONS, editTextHistory2True, editTextHistory2False, editTextHistory2Net);
        CommonUtil.provideEditTextFalse(CommonUtil.ELEVEN_QUESTIONS, editTextHistory2False, editTextHistory2True, editTextHistory2Net);
        CommonUtil.provideEditTextTrue(CommonUtil.ELEVEN_QUESTIONS, editTextGeographics2True, editTextGeographics2False, editTextGeographics2Net);
        CommonUtil.provideEditTextFalse(CommonUtil.ELEVEN_QUESTIONS, editTextGeographics2False, editTextGeographics2True, editTextGeographics2Net);
        CommonUtil.provideEditTextTrue(CommonUtil.TWELVE_QUESTIONS, editTextPhilosophyTrue, editTextPhilosophyFalse, editTextPhilosophyNet);
        CommonUtil.provideEditTextFalse(CommonUtil.TWELVE_QUESTIONS, editTextPhilosophyFalse, editTextPhilosophyTrue, editTextPhilosophyNet);
        CommonUtil.provideEditTextTrue(CommonUtil.SIX_QUESTIONS, editTextReligionTrue, editTextReligionFalse, editTextReligionNet);
        CommonUtil.provideEditTextFalse(CommonUtil.SIX_QUESTIONS, editTextReligionFalse, editTextReligionTrue, editTextReligionNet);
        CommonUtil.provideEditTextTrue(CommonUtil.EIGHTY_QUESTIONS, editTextLanguageTrue, editTextLanguageFalse, editTextLanguageNet);
        CommonUtil.provideEditTextFalse(CommonUtil.EIGHTY_QUESTIONS, editTextLanguageFalse, editTextLanguageTrue, editTextLanguageNet);
        CommonUtil.setEditTextMaxValue(editTextDiplomaGrade, 100);
    }

    private YKS getYks() {
        YKS yks = new YKS();
        YksService yksService = YksService.getYksService();
        yks.setDiplomaGrade(ConverterUtil.convertToInteger(editTextDiplomaGrade.getText().toString()));
        yks.setTurkishTrue(ConverterUtil.convertToInteger(editTextTurkishTrue.getText().toString()));
        yks.setTurkishFalse(ConverterUtil.convertToInteger(editTextTurkishFalse.getText().toString()));
        yks.setSocialTrue(ConverterUtil.convertToInteger(editTextSocialTrue.getText().toString()));
        yks.setSocialFalse(ConverterUtil.convertToInteger(editTextSocialFalse.getText().toString()));
        yks.setMathsTrue(ConverterUtil.convertToInteger(editTextMathsTrue.getText().toString()));
        yks.setMathsFalse(ConverterUtil.convertToInteger(editTextMathsFalse.getText().toString()));
        yks.setScienceTrue(ConverterUtil.convertToInteger(editTextScienceTrue.getText().toString()));
        yks.setScienceFalse(ConverterUtil.convertToInteger(editTextScienceFalse.getText().toString()));
        yks.setMaths2True(ConverterUtil.convertToInteger(editTextMaths2True.getText().toString()));
        yks.setMaths2False(ConverterUtil.convertToInteger(editTextMaths2False.getText().toString()));
        yks.setPhysicsTrue(ConverterUtil.convertToInteger(editTextPhysicsTrue.getText().toString()));
        yks.setPhysicsFalse(ConverterUtil.convertToInteger(editTextPhysicsFalse.getText().toString()));
        yks.setChemistryTrue(ConverterUtil.convertToInteger(editTextChemistryTrue.getText().toString()));
        yks.setChemistryFalse(ConverterUtil.convertToInteger(editTextChemistryFalse.getText().toString()));
        yks.setBiologyTrue(ConverterUtil.convertToInteger(editTextBiologyTrue.getText().toString()));
        yks.setBiologyFalse(ConverterUtil.convertToInteger(editTextBiologyFalse.getText().toString()));
        yks.setLiteratureTrue(ConverterUtil.convertToInteger(editTextLiteratureTrue.getText().toString()));
        yks.setLiteratureFalse(ConverterUtil.convertToInteger(editTextLiteratureFalse.getText().toString()));
        yks.setHistoryTrue(ConverterUtil.convertToInteger(editTextHistoryTrue.getText().toString()));
        yks.setHistoryFalse(ConverterUtil.convertToInteger(editTextHistoryFalse.getText().toString()));
        yks.setGeographicsTrue(ConverterUtil.convertToInteger(editTextGeographicsTrue.getText().toString()));
        yks.setGeographicsFalse(ConverterUtil.convertToInteger(editTextGeographicsFalse.getText().toString()));
        yks.setHistory2True(ConverterUtil.convertToInteger(editTextHistory2True.getText().toString()));
        yks.setHistory2False(ConverterUtil.convertToInteger(editTextHistory2False.getText().toString()));
        yks.setGeographics2True(ConverterUtil.convertToInteger(editTextGeographics2True.getText().toString()));
        yks.setGeographics2False(ConverterUtil.convertToInteger(editTextGeographics2False.getText().toString()));
        yks.setPhilosophyTrue(ConverterUtil.convertToInteger(editTextPhilosophyTrue.getText().toString()));
        yks.setPhilosophyFalse(ConverterUtil.convertToInteger(editTextPhilosophyFalse.getText().toString()));
        yks.setReligionTrue(ConverterUtil.convertToInteger(editTextReligionTrue.getText().toString()));
        yks.setReligionFalse(ConverterUtil.convertToInteger(editTextReligionFalse.getText().toString()));
        yks.setLanguageTrue(ConverterUtil.convertToInteger(editTextLanguageTrue.getText().toString()));
        yks.setLanguageFalse(ConverterUtil.convertToInteger(editTextLanguageFalse.getText().toString()));
        yks.setDiplomaNotification(checkBoxDiplamaNotification.isChecked());
        yks.setResultSimpleTYT(CommonUtil.round(yksService.getSimpleTYTResult(yks), 2));
        yks.setResultSimpleNumerical(CommonUtil.round(yksService.getSimpleNumericalResult(yks), 2));
        yks.setResultSimpleVerbal(CommonUtil.round(yksService.getSimpleVerbalResult(yks), 2));
        yks.setResultSimpleEqualWeight(CommonUtil.round(yksService.getSimpleEqualWeightResult(yks), 2));
        yks.setResultSimpleLanguage(CommonUtil.round(yksService.getSimpleLanguageResult(yks), 2));
        yks.setResultCalculatedTYT(CommonUtil.round(yksService.getCalculatedTYTResult(yks), 2));
        yks.setResultCalculatedNumerical(CommonUtil.round(yksService.getCalculatedNumericalResult(yks), 2));
        yks.setResultCalculatedVerbal(CommonUtil.round(yksService.getCalculatedVerbalResult(yks), 2));
        yks.setResultCalculatedEqualWeight(CommonUtil.round(yksService.getCalculatedEqualWeightResult(yks), 2));
        yks.setResultCalculatedLanguage(CommonUtil.round(yksService.getCalculatedLanguageResult(yks), 2));
        yks.setExamType(ExamsEnum.YKS.getId());
        return yks;
    }

    private void setViewListeners() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(getYks());
            }
        });
    }

    private void showDialog(final YKS yks) {
        if (yks.getResultSimpleTYT() == YksService.INCALCULABLE_RESULT) {
            new AlertDialog.Builder(YksActivity.this)
                    .setTitle("YKS Sonucunuz Hesaplanamaz!")
                    .setMessage("Türkçe veya Temel Matematik testlerinin en az birinden 0,5 veya üzeri netiniz olmadığı için puanınız hesaplanmadı.")
                    .setPositiveButton(getResources().getText(R.string.button_ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).setIcon(getResources().getDrawable(R.drawable.alert_dialog_icon))
                    .show();
        } else if (yks.getResultSimpleTYT() < YksService.INSUFFICIENT_RESULT) {
            new AlertDialog.Builder(YksActivity.this)
                    .setTitle("TYT Puanınız Yetersiz!")
                    .setMessage("TYT puanınız 150 puan altında olduğu için meslek yüksekokulları ve lisans programlarından tercih yapamazsınız.")
                    .setPositiveButton(getResources().getText(R.string.button_show_result), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            showResultDailog(yks);
                        }
                    }).setNegativeButton(getResources().getText(R.string.button_recalculate), null)
                    .setIcon(getResources().getDrawable(R.drawable.alert_dialog_icon))
                    .show();
        } else {
            showResultDailog(yks);
        }
    }

    private void showResultDailog(YKS yks) {
        final Dialog dialog = new Dialog(YksActivity.this);
        dialog.setContentView(R.layout.dialog_yks);
        TextView textViewYKSResultSimpleTYT = dialog.findViewById(R.id.textViewYKSResultSimpleTYT);
        TextView textViewYKSResultSimpleNumerical = dialog.findViewById(R.id.textViewYKSResultSimpleNumerical);
        TextView textViewYKSResultSimpleVerbal = dialog.findViewById(R.id.textViewYKSResultSimpleVerbal);
        TextView textViewYKSResultSimpleEqualWeight = dialog.findViewById(R.id.textViewYKSResultSimpleEqualWeight);
        TextView textViewYKSResultSimpleLanguage = dialog.findViewById(R.id.textViewYKSResultSimpleLanguage);
        TextView textViewYKSResultCalculatedTYT = dialog.findViewById(R.id.textViewYKSResultCalculatedTYT);
        TextView textViewYKSResultCalculatedNumerical = dialog.findViewById(R.id.textViewYKSResultCalculatedNumerical);
        TextView textViewYKSResultCalculatedVerbal = dialog.findViewById(R.id.textViewYKSResultCalculatedVerbal);
        TextView textViewYKSResultCalculatedEqualWeight = dialog.findViewById(R.id.textViewYKSResultCalculatedEqualWeight);
        TextView textViewYKSResultCalculatedLanguage = dialog.findViewById(R.id.textViewYKSResultCalculatedLanguage);
        textViewYKSResultSimpleTYT.setText(String.valueOf(yks.getResultSimpleTYT()));
        textViewYKSResultSimpleNumerical.setText(String.valueOf(yks.getResultSimpleNumerical()));
        textViewYKSResultSimpleVerbal.setText(String.valueOf(yks.getResultSimpleVerbal()));
        textViewYKSResultSimpleEqualWeight.setText(String.valueOf(yks.getResultSimpleEqualWeight()));
        textViewYKSResultSimpleLanguage.setText(String.valueOf(yks.getResultSimpleLanguage()));
        textViewYKSResultCalculatedTYT.setText(String.valueOf(yks.getResultCalculatedTYT()));
        textViewYKSResultCalculatedNumerical.setText(String.valueOf(yks.getResultCalculatedNumerical()));
        textViewYKSResultCalculatedVerbal.setText(String.valueOf(yks.getResultCalculatedVerbal()));
        textViewYKSResultCalculatedEqualWeight.setText(String.valueOf(yks.getResultCalculatedEqualWeight()));
        textViewYKSResultCalculatedLanguage.setText(String.valueOf(yks.getResultCalculatedLanguage()));
        setDialogViewListeners(dialog, yks);
        dialog.show();
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(YksActivity.this)
                .setTitle("UYARI!")
                .setMessage("*Sınav Sonucunuz 2018 katsayılarına göre hesaplanmıştır. Gireceğiniz sınavda ufak farklılıklar gösterebilir. " +
                        "*YTY puanınızın hesaplanabilmesi için Türkçe veya Temel Matematik testlerinin birinden en az 0.5 net yapmanız gerekmektedir. " +
                        "*AYT puanınızın hesaplanabilmesi için ilgili testlerden en az birinden 0.5 net yapmanız gerekmektedir. " +
                        "*TYT puanınız 150 puan ve üzerinde olduğunda ön lisans programlarından tercih yapabilir ve özel yetenek gerektiren lisans programlarına ön kayıt yaptırabilirsiniz. " +
                        "*AYT SAY, EA, SÖZ ve DİL puanlarının herhangi biri 180 ve üzerinde olduğunda, lisans programlarına tercih yapabilirsiniz.")
                .setPositiveButton(getResources().getText(R.string.button_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setIcon(getResources().getDrawable(R.drawable.alert_dialog_icon))
                .show();
    }

    private void setDialogViewListeners(final Dialog dialog, final YKS yks) {
        Button buttonSave = dialog.findViewById(R.id.buttonYKSDialogSave);
        Button buttonClose = dialog.findViewById(R.id.buttonYKSDialogClose);
        final EditText editTextExamName = dialog.findViewById(R.id.editTextYKSDialogExamName);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtil.controlEditTextValue(editTextExamName, getBaseContext())) {
                    yks.setName(editTextExamName.getText().toString());
                    long result = databaseManager.put(yks);
                    if (result == DatabaseManager.ERROR) {
                        Toast.makeText(YksActivity.this, CommonUtil.PUT_EXAM_ERROR_STRING, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(YksActivity.this, CommonUtil.PUT_EXAM_SUCCESSFUL_STRING, Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                    Intent intent = new Intent(YksActivity.this, MainActivity.class);
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
