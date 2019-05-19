package com.berkay22demirel.sinavpuanhesaplama.Service;

public class TusService {

    private static TusService tusService;

    public static TusService getTusService() {
        if (tusService == null) {
            tusService = new TusService();
        }
        return tusService;
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
}
