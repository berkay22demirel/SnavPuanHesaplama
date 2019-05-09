package com.berkay22demirel.sinavpuanhesaplama;

import android.app.Dialog;
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

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Util.CommonUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.ConverterUtil;
import com.berkay22demirel.sinavpuanhesaplama.Util.DateTimeUtil;

public class YksActivity extends AppCompatActivity {

    EditText editTextdiplomaGrade;
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
        setViewReferences();
        DateTimeUtil.addCountDown(textViewYKSTime, PAGE_TITLE);
        provideEditTexts();
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

        }
        return super.onOptionsItemSelected(item);
    }

    private void setViewReferences() {
        editTextdiplomaGrade = findViewById(R.id.editTextYKSDiplomaGrade);
        editTextTurkishTrue = findViewById(R.id.editTextYKSTurkishTrue);
        editTextTurkishFalse = findViewById(R.id.editTextYKSTurkishFalse);
        editTextTurkishNet = findViewById(R.id.editTextYKSTurkishNet);
        editTextSocialTrue = findViewById(R.id.editTextYKSSocialTrue);
        editTextSocialFalse = findViewById(R.id.editTextYKSScienceFalse);
        editTextSocialNet = findViewById(R.id.editTextYKSScienceNet);
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
        textViewYKSTime = findViewById(R.id.textViewYKSTime);
        checkBoxDiplamaNotification = findViewById(R.id.checkBoxYKSDiplomaNotification);
        buttonCalculate = findViewById(R.id.buttonYKSCalculate);
    }

    private void provideEditTexts() {
        CommonUtil.provideEditTextTrue(CommonUtil.FIFTY_QUESTIONS, editTextTurkishTrue, editTextTurkishFalse, editTextTurkishNet);
        CommonUtil.provideEditTextFalse(CommonUtil.FIFTY_QUESTIONS, editTextTurkishFalse, editTextTurkishTrue, editTextTurkishNet);
        CommonUtil.provideEditTextTrue(CommonUtil.FIFTY_QUESTIONS, editTextSocialTrue, editTextSocialFalse, editTextSocialNet);
        CommonUtil.provideEditTextFalse(CommonUtil.FIFTY_QUESTIONS, editTextSocialFalse, editTextSocialTrue, editTextSocialNet);
        CommonUtil.provideEditTextTrue(CommonUtil.FIFTY_QUESTIONS, editTextMathsTrue, editTextMathsFalse, editTextMathsNet);
        CommonUtil.provideEditTextFalse(CommonUtil.FIFTY_QUESTIONS, editTextMathsFalse, editTextMathsTrue, editTextMathsNet);
        CommonUtil.provideEditTextTrue(CommonUtil.FIFTY_QUESTIONS, editTextScienceTrue, editTextScienceFalse, editTextScienceNet);
        CommonUtil.provideEditTextFalse(CommonUtil.FIFTY_QUESTIONS, editTextScienceFalse, editTextScienceTrue, editTextScienceNet);
        CommonUtil.provideEditTextTrue(CommonUtil.FIFTY_QUESTIONS, editTextMaths2True, editTextMaths2False, editTextMaths2Net);
        CommonUtil.provideEditTextFalse(CommonUtil.FIFTY_QUESTIONS, editTextMaths2False, editTextMaths2True, editTextMaths2Net);
        CommonUtil.provideEditTextTrue(CommonUtil.FIFTY_QUESTIONS, editTextPhysicsTrue, editTextPhysicsFalse, editTextPhysicsNet);
        CommonUtil.provideEditTextFalse(CommonUtil.FIFTY_QUESTIONS, editTextPhysicsFalse, editTextPhysicsTrue, editTextPhysicsNet);
        CommonUtil.provideEditTextTrue(CommonUtil.FIFTY_QUESTIONS, editTextChemistryTrue, editTextChemistryFalse, editTextChemistryNet);
        CommonUtil.provideEditTextFalse(CommonUtil.FIFTY_QUESTIONS, editTextChemistryFalse, editTextChemistryTrue, editTextChemistryNet);
        CommonUtil.provideEditTextTrue(CommonUtil.FIFTY_QUESTIONS, editTextBiologyTrue, editTextBiologyFalse, editTextBiologyNet);
        CommonUtil.provideEditTextFalse(CommonUtil.FIFTY_QUESTIONS, editTextBiologyFalse, editTextBiologyTrue, editTextBiologyNet);
        CommonUtil.provideEditTextTrue(CommonUtil.FIFTY_QUESTIONS, editTextLiteratureTrue, editTextLiteratureFalse, editTextLiteratureNet);
        CommonUtil.provideEditTextFalse(CommonUtil.FIFTY_QUESTIONS, editTextLiteratureFalse, editTextLiteratureTrue, editTextLiteratureNet);
        CommonUtil.provideEditTextTrue(CommonUtil.FIFTY_QUESTIONS, editTextHistoryTrue, editTextHistoryFalse, editTextHistoryNet);
        CommonUtil.provideEditTextFalse(CommonUtil.FIFTY_QUESTIONS, editTextHistoryFalse, editTextHistoryTrue, editTextHistoryNet);
        CommonUtil.provideEditTextTrue(CommonUtil.FIFTY_QUESTIONS, editTextGeographicsTrue, editTextGeographicsFalse, editTextGeographicsNet);
        CommonUtil.provideEditTextFalse(CommonUtil.FIFTY_QUESTIONS, editTextGeographicsFalse, editTextGeographicsTrue, editTextGeographicsNet);
        CommonUtil.provideEditTextTrue(CommonUtil.FIFTY_QUESTIONS, editTextHistory2True, editTextHistory2False, editTextHistory2Net);
        CommonUtil.provideEditTextFalse(CommonUtil.FIFTY_QUESTIONS, editTextHistory2False, editTextHistory2True, editTextHistory2Net);
        CommonUtil.provideEditTextTrue(CommonUtil.FIFTY_QUESTIONS, editTextGeographics2True, editTextGeographics2False, editTextGeographics2Net);
        CommonUtil.provideEditTextFalse(CommonUtil.FIFTY_QUESTIONS, editTextGeographics2False, editTextGeographics2True, editTextGeographics2Net);
        CommonUtil.provideEditTextTrue(CommonUtil.FIFTY_QUESTIONS, editTextPhilosophyTrue, editTextPhilosophyFalse, editTextPhilosophyNet);
        CommonUtil.provideEditTextFalse(CommonUtil.FIFTY_QUESTIONS, editTextPhilosophyFalse, editTextPhilosophyTrue, editTextPhilosophyNet);
        CommonUtil.provideEditTextTrue(CommonUtil.FIFTY_QUESTIONS, editTextReligionTrue, editTextReligionFalse, editTextReligionNet);
        CommonUtil.provideEditTextFalse(CommonUtil.FIFTY_QUESTIONS, editTextReligionFalse, editTextReligionTrue, editTextReligionNet);
    }

    private void setCalculateButtonListener() {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mathsTrue = ConverterUtil.convertToInteger(editTextMathsTrue.getText().toString());
                int mathsFalse = ConverterUtil.convertToInteger(editTextMathsFalse.getText().toString());
                int turkishTrue = ConverterUtil.convertToInteger(editTextTurkishTrue.getText().toString());
                int turkishFalse = ConverterUtil.convertToInteger(editTextTurkishFalse.getText().toString());
                double mathsNet = CommonUtil.getNet(mathsTrue, mathsFalse);
                double turkishNet = CommonUtil.getNet(turkishTrue, turkishFalse);
                double numericalResult = 0;
                double verbalResult = 0;
                double equalWeightResult = 0;
                showResultDailog(mathsNet, turkishNet, numericalResult, verbalResult, equalWeightResult);
            }
        });
    }

    private void showResultDailog(double mathsNet, double turkishNet, double numericalResult, double verbalResult, double equalWeightResult) {
        final Dialog dialog = new Dialog(YksActivity.this);
        dialog.setContentView(R.layout.dialog_yks);
        dialog.show();
    }
}
