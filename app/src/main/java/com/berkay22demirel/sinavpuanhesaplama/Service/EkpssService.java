package com.berkay22demirel.sinavpuanhesaplama.Service;

import android.content.Context;

import com.berkay22demirel.sinavpuanhesaplama.Database.DatabaseManager;
import com.berkay22demirel.sinavpuanhesaplama.Enum.EkpssTypeEnum;
import com.berkay22demirel.sinavpuanhesaplama.Interface.IDatabase;
import com.berkay22demirel.sinavpuanhesaplama.Model.EKPSS;

public class EkpssService implements IDatabase {

    DatabaseManager databaseManager;

    public EkpssService(Context context) {
        this.databaseManager = new DatabaseManager(context);
    }

    public double getResult(double generalAbilityNet, double generalKnowledgeNet, int ekpssType) {
        if (generalAbilityNet != 0.0 || generalKnowledgeNet != 0.0) {
            if (EkpssTypeEnum.SECONDARY_EDUCATION.getId() == ekpssType) {
                return 57.420 + generalAbilityNet * 0.637 + generalKnowledgeNet * 0.783;
            } else if (EkpssTypeEnum.ASSOCIATE_DEGREE.getId() == ekpssType) {
                return 55.262 + generalAbilityNet * 0.772 + generalKnowledgeNet * 0.725;
            } else if (EkpssTypeEnum.BACHELOR_DEGREE.getId() == ekpssType) {
                return 50.906 + generalAbilityNet * 0.878 + generalKnowledgeNet * 0.759;
            }
        }
        return 0.0;
    }

    @Override
    public Long put(Object data) {
        return databaseManager.put(data);
    }

    @Override
    public Object get(Long examID) {
        return databaseManager.get(examID, EKPSS.class);
    }

    @Override
    public Long delete(Long examID) {
        return databaseManager.delete(examID, EKPSS.class);
    }
}
