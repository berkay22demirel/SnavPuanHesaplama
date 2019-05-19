package com.berkay22demirel.sinavpuanhesaplama.Service;

import com.berkay22demirel.sinavpuanhesaplama.Model.DGS;

public class DgsService {

    private static DgsService dgsService;

    public static DgsService getDgsService() {
        if (dgsService == null) {
            dgsService = new DgsService();
        }
        return dgsService;
    }

    public void setResult(DGS dgs) {
        double numerical = getNumerical(dgs.getNumericalNet());
        double verbal = getVerbal(dgs.getVerbalNet());
        dgs.setNumericalResult(getNumericalResult(numerical, verbal, dgs.getAssociateDegreeSuccessGrade(), dgs.getBeforeResult()));
        dgs.setVerbalResult(getVerbalResult(numerical, verbal, dgs.getAssociateDegreeSuccessGrade(), dgs.getBeforeResult()));
        dgs.setEqualWeightResult(getEqualWeightResult(numerical, verbal, dgs.getAssociateDegreeSuccessGrade(), dgs.getBeforeResult()));
    }

    private double getNumericalResult(double numerical, double verbal, double associateDegreeSuccessGrade, boolean beforeResult) {
        double factor;
        if (beforeResult) {
            factor = 0.45;
        } else {
            factor = 0.6;
        }
        return numerical * 3.0 + verbal * 0.6 + associateDegreeSuccessGrade * 0.8 * factor;
    }

    private double getVerbalResult(double numerical, double verbal, double associateDegreeSuccessGrade, boolean beforeResult) {
        double factor;
        if (beforeResult) {
            factor = 0.45;
        } else {
            factor = 0.6;
        }
        return numerical * 0.6 + verbal * 3.0 + associateDegreeSuccessGrade * 0.8 * factor;
    }

    private double getEqualWeightResult(double numerical, double verbal, double associateDegreeSuccessGrade, boolean beforeResult) {
        double factor;
        if (beforeResult) {
            factor = 0.45;
        } else {
            factor = 0.6;
        }
        return numerical * 1.8 + verbal * 1.8 + associateDegreeSuccessGrade * 0.8 * factor;
    }

    private double getNumerical(double numericalNet) {
        double testMean = 7.58;
        double testStandardDeviation = 9.45;
        return 50.0 + 10.0 * ((numericalNet - testMean) / testStandardDeviation);
    }

    private double getVerbal(double verbalNetNet) {
        double testMean = 23.91;
        double testStandardDeviation = 13.59;
        return 50.0 + 10.0 * ((verbalNetNet - testMean) / testStandardDeviation);
    }
}
