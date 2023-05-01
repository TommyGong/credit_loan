package com.tommy.creditloan.web.resp;

import java.io.Serializable;

public class CreditLoanResp<T> implements Serializable {

    private static final long serialVersionUID = -8614657378503039626L;

    private static final String CODE_200 = "200";

    private static final String CODE_ERROR = "500";

    private String code;
    private String message;
    private T data;

    public CreditLoanResp(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> CreditLoanResp<T> ok(T data) {
        return new CreditLoanResp<>(CODE_200, null, data);
    }

    public static CreditLoanResp<String> ok() {
        return new CreditLoanResp<>(CODE_200, null, null);
    }

    public static <T> CreditLoanResp<T> error() {
        return error(null);
    }

    public static <T> CreditLoanResp<T> error(String message) {
        return error(CODE_ERROR, message, null);
    }

    public static <T> CreditLoanResp<T> error(String code, String message) {
        return error(code, message, null);
    }

    public static <T> CreditLoanResp<T> error(String code, String message, T data) {
        return new CreditLoanResp<>(code, message, data);
    }

    public boolean isSuccess() {
        if (CODE_200.equals(code)) {
            return true;
        }
        return false;
    }

}
