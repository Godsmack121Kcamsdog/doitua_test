package io.box.doitua.com.boxesmanager.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Util for checking whether incoming info fit`s pattern or not
 */
public class Validator {
    /**
     * @EMAIL_PATTERN - check, whether word is e-mail
     */
    private static final String EMAIL_PATTERN = "^([+a-zA-Z0-9._-]+)@([a-zA-Z0-9.-]+)\\.([a-zA-Z]{2,6})$";

    private final Pattern pattern_mail;
    private Matcher matcher;

    public Validator() {
        pattern_mail = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean isValidableEmail(String email) {
        matcher = pattern_mail.matcher(email);
        return !matcher.matches();
    }
}
