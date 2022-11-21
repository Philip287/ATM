package com.suprun.atm.validator.imp;

import com.suprun.atm.validator.Validator;

import java.io.File;

public class FilePathValidator implements Validator {

    private static Validator<String> instance;

    private FilePathValidator() {

    }

    public static Validator<String> getInstance() {
        if (instance == null) {
            instance = new FilePathValidator();
        }
        return instance;
    }

    @Override
    public boolean validate(Object o) {
        String path = o.toString();
        if (path == null || path.isEmpty() || path.trim().isEmpty()) {
            return false;
        }
        boolean flag = false;
        File file = new File(path);
        if (file.exists()) {
            if (file.length() > 0) {
                flag = true;
            }
        }
        return flag;
    }
}
