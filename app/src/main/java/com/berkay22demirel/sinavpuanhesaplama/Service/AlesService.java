package com.berkay22demirel.sinavpuanhesaplama.Service;

public class AlesService {

    private static AlesService alesService;

        public static AlesService getAlesService() {
            if (alesService == null) {
                alesService = new AlesService();
            }
            return alesService;
    }

    public double getNumericalResult(double mathsNet, double turkishNet) {
        //Adayın Ağırlıklı Puanı
        double ap = mathsNet * 0.75 + turkishNet * 0.25;
        //Ağırlıklı Puanların Ortalaması
        double x = 0.75 * 19.07 + 0.25 * 28.85;
        //Ağırlıklı Puanların Standart Sapması
        double s = 0.75 * 13.03 + 0.25 * 9.97;
        //Ağırlıklı Puanların En Büyüğü
        double b = 50.0;
        return 70.0 + (30.0 * (2.0 * (ap - x) - s)) / (2.0 * (b - x) - s);
    }

    public double getVerbalResult(double mathsNet, double turkishNet) {
        //Adayın Ağırlıklı Puanı
        double ap = mathsNet * 0.25 + turkishNet * 0.75;
        //Ağırlıklı Puanların Ortalaması
        double x = 0.25 * 19.07 + 0.75 * 28.85;
        //Ağırlıklı Puanların Standart Sapması
        double s = 0.25 * 13.03 + 0.75 * 9.97;
        //Ağırlıklı Puanların En Büyüğü
        double b = 50.0;
        return 70.0 + (30.0 * (2.0 * (ap - x) - s)) / (2.0 * (b - x) - s);
    }

    public double getEqualWeightResult(double mathsNet, double turkishNet) {
        //Adayın Ağırlıklı Puanı
        double ap = mathsNet * 0.50 + turkishNet * 0.50;
        //Ağırlıklı Puanların Ortalaması
        double x = 0.50 * 19.07 + 0.50 * 28.85;
        //Ağırlıklı Puanların Standart Sapması
        double s = 0.50 * 13.03 + 0.50 * 9.97;
        //Ağırlıklı Puanların En Büyüğü
        double b = 50.0;
        return 70.0 + (30.0 * (2.0 * (ap - x) - s)) / (2.0 * (b - x) - s);
    }

}
