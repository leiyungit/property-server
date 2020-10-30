package com.msb.resultJson;

public class ResultObject {

    private Integer code = 200;
    private String message = "";
    private Object result;

    public ResultObject() {
    }

    public ResultObject(Object result) {
        this.result = result;
    }

    public ResultObject(String message, Object result) {
        this.message = message;
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResultObject{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
