package com.thoughtworks.gtb.basicquiz.exception;

public class UserIsNotFoundException extends Exception {
    public UserIsNotFoundException() {
        super("找不到用户");
    }
}
