package com.berkay22demirel.sinavpuanhesaplama.Enum;

public enum ResultsEnum {
    NUMERICAL(1),
    VERBAL(2),
    EQUAL_WEIGHT(3),
    DUS(4),
    EKPSS(5),
    EUS(6),
    GRADUATE_MEDICINE_K_POINT(7),
    GRADUATE_MEDICINE_T_POINT(8),
    GRADUATE_MEDICINE_A_POINT(9),
    NOT_GRADUATE_MEDICINE_T_POINT(10),
    YDS(11),
    SIMPLE_TYT(12),
    SIMPLE_NUMERICAL(13),
    SIMPLE_VERBAL(14),
    SIMPLE_EQUAL_WEIGHT(15),
    SIMPLE_LANGUAGE(16),
    CALCULATED_TYT(17),
    CALCULATED_NUMERICAL(18),
    CALCULATED_VERBAL(19),
    CALCULATED_EQUAL_WEIGHT(20),
    CALCULATED_LANGUAGE(21);

    private int id;

    ResultsEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
