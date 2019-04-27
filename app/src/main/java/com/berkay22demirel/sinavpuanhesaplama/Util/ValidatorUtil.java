package com.berkay22demirel.sinavpuanhesaplama.Util;

import java.util.List;

public class ValidatorUtil {

    public static <T> boolean isValidList(List<T> list) {
        if (list != null && !list.isEmpty()) {
            return true;
        }
        return false;
    }
}
