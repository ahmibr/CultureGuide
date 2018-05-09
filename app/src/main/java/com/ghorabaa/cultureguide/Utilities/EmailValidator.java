package com.ghorabaa.cultureguide.Utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ahmed Ibrahim on 5/2/18.
 */

/**
 * Helper class to check if email is in valid form
 */
public class EmailValidator {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }

    //Don't instantiate
    private EmailValidator(){}

}
