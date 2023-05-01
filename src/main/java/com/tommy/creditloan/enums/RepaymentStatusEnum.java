package com.tommy.creditloan.enums;

public enum RepaymentStatusEnum {

    INIT(1, "initial"),
    PROCESSING(2, "processing"),

    FINISHED(3, "finished"),

    OVERDUE(4, "overdue"),

    FAILED(10, "failed");

    private Integer code;

    private String desc;

    RepaymentStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getStatusDesc(Integer code) {
        RepaymentStatusEnum[] statusEnum = values();
        for (RepaymentStatusEnum repaymentStatusEnum : statusEnum) {
            if(repaymentStatusEnum.getCode().intValue() == code) {
                return repaymentStatusEnum.getDesc();
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
