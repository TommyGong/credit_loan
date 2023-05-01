package com.tommy.creditloan.enums;

public enum LoanTypeEnum {

    HOUSE(1, "house"),
    CAR(2, "car");

    private Integer code;

    private String desc;

    LoanTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getStatusDesc(Integer code) {
        LoanTypeEnum[] statusEnum = values();
        for (LoanTypeEnum loanTypeEnum : statusEnum) {
            if(loanTypeEnum.getCode().intValue() == code) {
                return loanTypeEnum.getDesc();
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
