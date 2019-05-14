package com.berkay22demirel.sinavpuanhesaplama.Util;

import java.util.List;

public class ValidatorUtil {

    public static <T> boolean isValidList(List<T> list) {
        if (list != null && !list.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isValidString(String value) {
        if (value != null && !value.isEmpty()) {
            return true;
        }
        return false;
    }

    public static <T> boolean isValidArray(Object[] array) {
        if (array != null && array.length > 0) {
            return true;
        }
        return false;
    }
}
