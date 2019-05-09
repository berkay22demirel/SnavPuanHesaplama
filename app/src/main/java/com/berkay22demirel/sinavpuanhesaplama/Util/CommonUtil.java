package com.berkay22demirel.sinavpuanhesaplama.Util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;

public class CommonUtil {

    public static String HELPER_NET_TEXT = " NET";
    public static int FIFTY_QUESTIONS = 50;
    public static int FOURTY_QUESTIONS = 40;

    public static void provideEditTextTrue(final int totalNumberOfQuestions, final EditText editTextTrue, final EditText editTextFalse, final EditText editTextNet) {
        editTextTrue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int editTextTrueValue = ConverterUtil.convertToInteger(editTextTrue.getText().toString());
                int editFalseTextValue = ConverterUtil.convertToInteger(editTextFalse.getText().toString());
                CommonUtil.controlNumberOfQuestions(editTextTrue, totalNumberOfQuestions, editTextTrueValue, editFalseTextValue);
                editTextTrueValue = ConverterUtil.convertToInteger(editTextTrue.getText().toString());
                editTextNet.setText("" + getNet(editTextTrueValue, editFalseTextValue));
            }
        });
    }

    public static void provideEditTextFalse(final int totalNumberOfQuestions, final EditText editTextFalse, final EditText editTextTrue, final EditText editTextNet) {
        editTextFalse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int editTextTrueValue = ConverterUtil.convertToInteger(editTextTrue.getText().toString());
                int editFalseTextValue = ConverterUtil.convertToInteger(editTextFalse.getText().toString());
                CommonUtil.controlNumberOfQuestions(editTextFalse, totalNumberOfQuestions, editFalseTextValue, editTextTrueValue);
                editFalseTextValue = ConverterUtil.convertToInteger(editTextFalse.getText().toString());
                editTextNet.setText("" + getNet(editTextTrueValue, editFalseTextValue));
            }
        });
    }

    private static void controlNumberOfQuestions(EditText changedEditText, int totalNumberOfQuestions, int changedEditTextValue, int otherEditTextValue) {
        int result = totalNumberOfQuestions - changedEditTextValue - otherEditTextValue;
        if (result < 0) {
            changedEditText.setText("" + (totalNumberOfQuestions - otherEditTextValue));
        }
    }

    public static double getNet(double trueNumber, double falseNumber) {
        return (trueNumber - (falseNumber / 4));
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static String getPageTitle(String title) {
        return title + " Puan Hesapla";
    }
}
