package com.berkay22demirel.sinavpuanhesaplama.Service;

public class EusService {

    private static EusService eusService;

    public static EusService getEusService() {
        if (eusService == null) {
            eusService = new EusService();
        }
        return eusService;
    }

    public double getResult(double net) {
        return 26.08172 + net * 0.89214;
    }
}
