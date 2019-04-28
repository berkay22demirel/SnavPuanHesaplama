package com.berkay22demirel.sinavpuanhesaplama.Util;

public class ConverterUtil {

    public static int convertToInteger (String value) {
        if(ValidatorUtil.isValidString(value)) {
            try {
                return Integer.parseInt(value);
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }
}
