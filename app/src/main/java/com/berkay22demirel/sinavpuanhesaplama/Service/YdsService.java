package com.berkay22demirel.sinavpuanhesaplama.Service;

import com.berkay22demirel.sinavpuanhesaplama.Util.CommonUtil;

public class YdsService {
    private static YdsService ydsService;

    public static YdsService getYdsService() {
        if (ydsService == null) {
            ydsService = new YdsService();
        }
        return ydsService;
    }

    public double getResult(double languageNet) {
        return languageNet * 1.25;
    }

    public String getLevel(double result) {
        if (result < 50) {
            return "Barajı geçemediniz";
        } else if (result < 60) {
            return "E";
        } else if (result < 70) {
            return "D";
        } else if (result < 80) {
            return "C";
        } else if (result < 90) {
            return "B";
        } else if (result <= 100) {
            return "A";
        }
        return CommonUtil.EMPTY_STRING;
    }
}
