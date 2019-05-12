package com.berkay22demirel.sinavpuanhesaplama.Util;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.widget.EditText;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;

public class CommonUtil {

    public static String EMPTY_STRING = "";
    public static int SIX_QUESTIONS = 6;
    public static int TEN_QUESTIONS = 10;
    public static int ELEVEN_QUESTIONS = 11;
    public static int TWELVE_QUESTIONS = 12;
    public static int THIRTEEN_QUESTIONS = 13;
    public static int FOURTEEN_QUESTIONS = 14;
    public static int TWENTY_QUESTIONS = 20;
    public static int TWENTY_FOUR_QUESTIONS = 24;
    public static int THIRTY_QUESTIONS = 30;
    public static int FOURTY_QUESTIONS = 40;
    public static int FIFTY_QUESTIONS = 50;
    public static int SIXTY_QUESTIONS = 60;
    public static int EIGHTY_QUESTIONS = 80;

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

    public static void setEditTextMaxValue(final EditText editText, final int maxValue) {
        if (editText != null) {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int value = ConverterUtil.convertToInteger(editText.getText().toString());
                    if (value > maxValue) {
                        editText.setText("" + maxValue);
                    }
                }
            });
        }
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
