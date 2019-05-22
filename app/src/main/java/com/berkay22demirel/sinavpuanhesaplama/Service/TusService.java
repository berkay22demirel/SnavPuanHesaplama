package com.berkay22demirel.sinavpuanhesaplama.Service;

import android.content.Context;

import com.berkay22demirel.sinavpuanhesaplama.Database.DatabaseManager;
import com.berkay22demirel.sinavpuanhesaplama.Interface.IDatabase;
import com.berkay22demirel.sinavpuanhesaplama.Model.TUS;

public class TusService implements IDatabase {

    DatabaseManager databaseManager;

    public TusService(Context context) {
        this.databaseManager = new DatabaseManager(context);
    }

    public double getGraduateMedicineKPoint(double basicMedicineSciencesNet, double clinicalMedicineSciencesNet) {
        return (18.06980 + clinicalMedicineSciencesNet * 0.60489) * 0.5 + (28.27491 + basicMedicineSciencesNet * 0.42438) * 0.5;
    }

    public double getGraduateMedicineTPoint(double basicMedicineSciencesNet, double clinicalMedicineSciencesNet) {
        return (18.06980 + clinicalMedicineSciencesNet * 0.60489) * 0.3 + (28.27491 + basicMedicineSciencesNet * 0.42438) * 0.7;
    }

    public double getGraduateMedicineAPoint(double clinicalMedicineSciencesNet) {
        return 18.06980 + clinicalMedicineSciencesNet * 0.60489;
    }

    public double getNotGraduateMedicineTPoint(double basicMedicineSciencesNet) {
        return 28.27491 + basicMedicineSciencesNet * 0.42438;
    }

    @Override
    public Long put(Object data) {
        return databaseManager.put(data);
    }

    @Override
    public Object get(Long examID) {
        return databaseManager.get(examID, TUS.class);
    }

    @Override
    public Long delete(Long examID) {
        return databaseManager.delete(examID, TUS.class);
    }
}
