package com.ums.validation;

import com.ums.exception.OperationException;
import org.springframework.http.HttpStatus;

public class PasswordValidation {

    public static void passwordMatches(String password, String passwordConfirm) throws OperationException {
        if(!password.equals(passwordConfirm))
            throw new OperationException(HttpStatus.NOT_ACCEPTABLE,"Passwords are not the same");
    }
}
