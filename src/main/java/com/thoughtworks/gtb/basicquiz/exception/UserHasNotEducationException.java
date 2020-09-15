package com.thoughtworks.gtb.basicquiz.exception;

public class UserHasNotEducationException extends Exception {
    public UserHasNotEducationException() {
        super("该用户没有教育经历");
    }
}
