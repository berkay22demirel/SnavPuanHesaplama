package com.berkay22demirel.sinavpuanhesaplama.Util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class CommonUtil {

    public static String HELPER_NET_TEXT = " NET";

    public static void setEditTextChangedListener(final int totalNumberOfQuestions, final EditText changedEditText, final EditText OtherEditText) {
        changedEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                CommonUtil.controlNumberOfQuestions(totalNumberOfQuestions, changedEditText, OtherEditText);
            }
        });
    }

    private static void controlNumberOfQuestions(int totalNumberOfQuestions, EditText changedEditText, EditText OtherEditText) {
        int otherEditTextValue = ConverterUtil.convertToInteger(OtherEditText.getText().toString());
        int changedEditTextValue = ConverterUtil.convertToInteger(changedEditText.getText().toString());
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
}
