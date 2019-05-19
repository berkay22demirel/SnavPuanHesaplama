package com.berkay22demirel.sinavpuanhesaplama.Service;

public class DusService {

    private static DusService dusService;

    public static DusService getDusService() {
        if (dusService == null) {
            dusService = new DusService();
        }
        return dusService;
    }

    public double getResult(double basicSciencesNet, double clinicalSciencesNet) {
        return 19.22377 + basicSciencesNet * 0.60299075 + clinicalSciencesNet * 0.415765875;
    }
}
