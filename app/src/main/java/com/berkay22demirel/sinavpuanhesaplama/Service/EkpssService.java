package com.berkay22demirel.sinavpuanhesaplama.Service;

import com.berkay22demirel.sinavpuanhesaplama.Enum.EkpssTypeEnum;

public class EkpssService {

    private static EkpssService ekpssService;

    public static EkpssService getEkpssService() {
        if (ekpssService == null) {
            ekpssService = new EkpssService();
        }
        return ekpssService;
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
}
