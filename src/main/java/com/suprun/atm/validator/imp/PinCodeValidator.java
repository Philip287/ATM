package com.suprun.atm.validator.imp;


import com.suprun.atm.validator.Validator;

import java.util.regex.Pattern;

public class PinCodeValidator implements Validator<String> {

    private static final String VALID_PASSWORD_REGEX = "\\d{4}";

    private static Validator<String> instance;

    private PinCodeValidator() {

    }

    public static Validator<String> getInstance() {
        if (instance == null) {
            instance = new PinCodeValidator();
        }
        return instance;
    }

    @Override
    public boolean validate(String password) {
        return Pattern.matches(VALID_PASSWORD_REGEX, password);
    }
}
