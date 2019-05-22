package com.berkay22demirel.sinavpuanhesaplama.Service;

import android.content.Context;

import com.berkay22demirel.sinavpuanhesaplama.Database.DatabaseManager;
import com.berkay22demirel.sinavpuanhesaplama.Interface.IDatabase;
import com.berkay22demirel.sinavpuanhesaplama.Model.YDS;
import com.berkay22demirel.sinavpuanhesaplama.Util.CommonUtil;

public class YdsService implements IDatabase {
    DatabaseManager databaseManager;

    public YdsService(Context context) {
        this.databaseManager = new DatabaseManager(context);
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

    @Override
    public Long put(Object data) {
        return databaseManager.put(data);
    }

    @Override
    public Object get(Long examID) {
        return databaseManager.get(examID, YDS.class);
    }

    @Override
    public Long delete(Long examID) {
        return databaseManager.delete(examID, YDS.class);
    }
}
