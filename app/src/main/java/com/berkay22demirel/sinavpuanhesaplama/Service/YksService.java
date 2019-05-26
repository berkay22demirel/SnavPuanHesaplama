package com.berkay22demirel.sinavpuanhesaplama.Service;

import android.content.Context;

import com.berkay22demirel.sinavpuanhesaplama.Database.DatabaseManager;
import com.berkay22demirel.sinavpuanhesaplama.Interface.IDatabase;
import com.berkay22demirel.sinavpuanhesaplama.Model.YKS;

public class YksService implements IDatabase {
    DatabaseManager databaseManager;
    public static double INCALCULABLE_RESULT = -0.1;
    public static double INSUFFICIENT_RESULT = 150;

    public YksService(Context context) {
        this.databaseManager = new DatabaseManager(context);
    }

    public double getSimpleTYTResult(YKS yks) {
        if (yks.getTurkishNet() < 0.5 && yks.getMathsNet() < 0.5) {
            return INCALCULABLE_RESULT;
        }
        double simpleResult = yks.getTurkishNet() * 3.3 + yks.getMathsNet() * 3.3 + yks.getSocialNet() * 3.4 + yks.getScienceNet() * 3.4;
        if (simpleResult > 0) {
            return 100.0 + simpleResult;
        }
        return 0.0;
    }

    public double getSimpleNumericalResult(YKS yks) {
        if (yks.getMaths2Net() >= 0.5 || yks.getPhysicsNet() >= 0.5 || yks.getChemistryNet() >= 0.5 || yks.getBiologyNet() >= 0.5) {
            double simpleResult = yks.getMaths2Net() * 3.0 + yks.getPhysicsNet() * 2.85 + yks.getChemistryNet() * 3.07 + yks.getBiologyNet() * 3.07;
            if (simpleResult > 0) {
                return 100.0 + simpleResult + getTYTResultForYKS(yks);
            }
        }
        return 0.0;
    }

    public double getSimpleVerbalResult(YKS yks) {
        if (yks.getLiteratureNet() >= 0.5 || yks.getHistoryNet() >= 0.5 || yks.getGeographicsNet() >= 0.5 || yks.getHistory2Net() >= 0.5 || yks.getGeographics2Net() >= 0.5 || yks.getPhilosophyNet() >= 0.5 || yks.getReligionNet() >= 0.5) {
            double simpleResult = yks.getLiteratureNet() * 3.0 + yks.getHistoryNet() * 2.8 + yks.getGeographicsNet() * 3.33 + yks.getHistory2Net() * 2.91 + yks.getGeographics2Net() * 2.91 + yks.getPhilosophyNet() * 3 + yks.getReligionNet() * 3.33;
            if (simpleResult > 0) {
                return 100.0 + simpleResult + getTYTResultForYKS(yks);
            }
        }
        return 0.0;
    }

    public double getSimpleEqualWeightResult(YKS yks) {
        if (yks.getMaths2Net() >= 0.5 || yks.getLiteratureNet() >= 0.5 || yks.getHistoryNet() >= 0.5 || yks.getGeographicsNet() >= 0.5) {
            double simpleResult = yks.getMaths2Net() * 3.0 + yks.getLiteratureNet() * 3.0 + yks.getHistoryNet() * 2.8 + yks.getGeographicsNet() * 3.33;
            if (simpleResult > 0) {
                return 100.0 + simpleResult + getTYTResultForYKS(yks);
            }
        }
        return 0.0;
    }

    public double getSimpleLanguageResult(YKS yks) {
        if (yks.getLanguageNet() >= 0.5) {
            double simpleResult = yks.getLanguageNet() * 3.0;
            if (simpleResult > 0) {
                return 100.0 + simpleResult + getTYTResultForYKS(yks);
            }
        }
        return 0.0;
    }

    public double getCalculatedTYTResult(YKS yks) {
        return getCalculatedResult(yks, yks.getResultSimpleTYT());
    }

    public double getCalculatedNumericalResult(YKS yks) {
        return getCalculatedResult(yks, yks.getResultSimpleNumerical());
    }

    public double getCalculatedVerbalResult(YKS yks) {
        return getCalculatedResult(yks, yks.getResultSimpleVerbal());
    }

    public double getCalculatedEqualWeightResult(YKS yks) {
        return getCalculatedResult(yks, yks.getResultSimpleEqualWeight());
    }

    public double getCalculatedLanguageResult(YKS yks) {
        return getCalculatedResult(yks, yks.getResultSimpleLanguage());
    }

    private double getCalculatedResult(YKS yks, double simpleResult) {
        if (yks.getDiplomaGrade() < 50 || simpleResult <= 0) {
            return 0.0;
        }
        double factor;
        if (yks.isDiplomaNotification()) {
            factor = 0.3;
        } else {
            factor = 0.6;
        }

        return simpleResult + factor * yks.getDiplomaGrade();
    }

    private double getTYTResultForYKS(YKS yks) {
        return yks.getTurkishNet() * 1.32 + yks.getMathsNet() * 1.32 + yks.getSocialNet() * 1.36 + yks.getScienceNet() * 1.36;
    }

    @Override
    public Long put(Object data) {
        return databaseManager.put(data);
    }

    @Override
    public Object get(Long examID) {
        return databaseManager.get(examID, YKS.class);
    }

    @Override
    public Long delete(Long examID) {
        return databaseManager.delete(examID, YKS.class);
    }
}
