package com.berkay22demirel.sinavpuanhesaplama.Enum;

public enum LessonsEnum {
    MATHS(1),
    TURKISH(2),
    NUMERICAL(3),
    VERBAL(4),
    BASIC_SCIENCES(5),
    CLINICAL_SCIENCES(6),
    GENERAL_ABILITY(7),
    GENERAL_KNOWLEDGE(8),
    EUS(9),
    BASIC_MEDICINE_SCIENCES(10),
    CLINICAL_MEDICINE_SCIENCES(11),
    LANGUAGE(12),
    SOCIAL(13),
    SCIENCE(14),
    MATHS2(15),
    PHYSICS(16),
    CHEMISTRY(17),
    BIOLOGY(18),
    LITERATURE(19),
    HISTORY(20),
    GEOGRAPHICS(21),
    HISTORY2(22),
    GEOGRAPHICS2(23),
    PHILOSOPHY(24),
    RELIGION(25),
    DIPLOMA_GRADE(26),
    ASSOCUATE_DEGREE_SUCCESS_GRADE(27);

    private int id;

    LessonsEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
