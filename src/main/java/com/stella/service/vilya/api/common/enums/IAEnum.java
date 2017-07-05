package com.stella.service.vilya.api.common.enums;

public enum IAEnum {

    B(0, "< before"),
    M(1, "meets"),
    O(2, "overlaps"),
    S(3, "starts"),
    D(4, "during"),
    F(5, "finishes"),
    EQ(6, "equals"),
    FI(7, "finished by"),
    DI(8, "includes"),
    SI(9, "started by"),
    OI(10, "overlapped by"),
    MI(11, "met by"),
    BI(12, "> after");

    private Integer code;
    private String desc;

    IAEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getName() {
        return name();
    }

    public static IAEnum getByCode(Integer code) {
        for (IAEnum iaEnum : values()) {
            if (iaEnum.getCode() == code) {
                return iaEnum;
            }
        }
        return null;
    }
}
