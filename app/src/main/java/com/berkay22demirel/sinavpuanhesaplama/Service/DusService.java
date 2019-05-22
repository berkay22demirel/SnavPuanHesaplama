package com.berkay22demirel.sinavpuanhesaplama.Service;

import android.content.Context;

import com.berkay22demirel.sinavpuanhesaplama.Database.DatabaseManager;
import com.berkay22demirel.sinavpuanhesaplama.Interface.IDatabase;
import com.berkay22demirel.sinavpuanhesaplama.Model.DUS;

public class DusService implements IDatabase {

    DatabaseManager databaseManager;

    public DusService(Context context) {
        this.databaseManager = new DatabaseManager(context);
    }

    public double getResult(double basicSciencesNet, double clinicalSciencesNet) {
        return 19.22377 + basicSciencesNet * 0.60299075 + clinicalSciencesNet * 0.415765875;
    }

    @Override
    public Long put(Object data) {
        return databaseManager.put(data);
    }

    @Override
    public Object get(Long examID) {
        return databaseManager.get(examID, DUS.class);
    }

    @Override
    public Long delete(Long examID) {
        return databaseManager.delete(examID, DUS.class);
    }
}
