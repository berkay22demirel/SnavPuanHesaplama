package com.berkay22demirel.sinavpuanhesaplama.Enum;

import com.berkay22demirel.sinavpuanhesaplama.Util.ValidatorUtil;

public enum EkpssTypeEnum {
    SECONDARY_EDUCATION(0, "Ortaöğretim"),
    ASSOCIATE_DEGREE(1, "Önlisans"),
    BACHELOR_DEGREE(2, "Lisans");

    private int id;
    private String name;
    private static String[] ekpssTypes;

    EkpssTypeEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String[] getEkpssTypes() {
        if (ValidatorUtil.isValidArray(ekpssTypes)) {
            return ekpssTypes;
        }
        ekpssTypes = new String[EkpssTypeEnum.values().length];
        EkpssTypeEnum[] ekpssTypeEnum = EkpssTypeEnum.values();
        for (int i = 0; i < ekpssTypeEnum.length; i++) {
            ekpssTypes[i] = ekpssTypeEnum[i].name;
        }
        return ekpssTypes;
    }

    public static EkpssTypeEnum getEkpssTypeById(int id) {
        EkpssTypeEnum[] ekpssTypeEnum = EkpssTypeEnum.values();
        for (int i = 0; i < ekpssTypeEnum.length; i++) {
            if (id == ekpssTypeEnum[i].getId()) {
                return ekpssTypeEnum[i];
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
