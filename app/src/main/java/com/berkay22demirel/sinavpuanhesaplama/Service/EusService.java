package com.berkay22demirel.sinavpuanhesaplama.Service;

import android.content.Context;

import com.berkay22demirel.sinavpuanhesaplama.Database.DatabaseManager;
import com.berkay22demirel.sinavpuanhesaplama.Interface.IDatabase;
import com.berkay22demirel.sinavpuanhesaplama.Model.EUS;

public class EusService implements IDatabase {

    DatabaseManager databaseManager;

    public EusService(Context context) {
        this.databaseManager = new DatabaseManager(context);
    }

    public double getResult(double net) {
        return 26.08172 + net * 0.89214;
    }

    @Override
    public Long put(Object data) {
        return databaseManager.put(data);
    }

    @Override
    public Object get(Long examID) {
        return databaseManager.get(examID, EUS.class);
    }

    @Override
    public Long delete(Long examID) {
        return databaseManager.delete(examID, EUS.class);
    }
}
