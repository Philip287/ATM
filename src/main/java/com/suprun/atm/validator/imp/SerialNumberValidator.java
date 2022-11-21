package com.suprun.atm.validator.imp;

import com.suprun.atm.validator.Validator;

import java.util.regex.Pattern;

public class SerialNumberValidator implements Validator<String> {

    private static final String VALID_PASSWORD_REGEX = "\\d{16}";

    private static Validator<String> instance;

    private SerialNumberValidator() {

    }

    public static Validator<String> getInstance() {
        if (instance == null) {
            instance = new SerialNumberValidator();
        }
        return instance;
    }

    @Override
    public boolean validate(String password) {
        return Pattern.matches(VALID_PASSWORD_REGEX, password);
    }
}
