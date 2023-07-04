package com.ayf.areyoufull.entity;

public class Result {
    public static final int CODE_OK = 200;
    public static final int CODE_ERR_BUSINESS = 501;
    public static final int CODE_ERR_UNLOGIN = 502;
    public static final int CODE_ERR_SYS = 503;

    private int code;
    private boolean success;
    private String message;
    private Object data;

    public Result(int code, boolean success, String message, Object data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static Result ok(){
        return new Result(CODE_OK, true, null, null);
    }
    public static Result ok(String message){
        return new Result(CODE_OK, true, message, null);
    }
    public static Result ok(Object data){
        return new Result(CODE_OK, true, null, data);
    }
    public static Result ok(String message, Object data){
        return new Result(CODE_OK, true, message, data);
    }
    public static Result err(int errCode, String message){
        return new Result(errCode, false, message, null);
    }
    public static Result err(int errCode, String message, Object data){
        return new Result(errCode, false,  message, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
