package com.pngo.crudProj.exception;

public enum ErrorCode {
    INVALID_KEY(1007, "Invalid key!"),
    USERNAME_BLANK(1003, "Username cannot be blank!"),
    INVALID_PASSWORD(1004, "Invalid password!"),
    UNCATEGORIED_EXCEPTION(9999, "Uncategorized exception!"),
    USER_EXISTED(1001, "User already exists!"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
