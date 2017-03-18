package com.epam.zubar.hr.utils;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Contains various methods that allow to validate data
 * received from JSPs before inserting them into the database/updating the
 * database.
 * @author Mikalay Zubar
 *
 */

public class Validator {
    private static final ResourceBundle RB = ResourceBundle.getBundle("property/validation");
    private static final int MAX_LENGTH = 16;
    private static Pattern pattern;
    private static Matcher matcher;

    public static boolean validatePassword(String password){
        boolean result = false;
        String regEx = RB.getString("password");
        pattern = Pattern.compile(regEx);
        if(password != null && password.length() > 0){
            matcher = pattern.matcher(password);
            result = matcher.matches();
            matcher.reset();
        }
        return result;
    }

    public static boolean validateLogin(String login){
        boolean result = false;
        String regEx = RB.getString("login");
        pattern = Pattern.compile(regEx);
        int len = login.length();
        if(login != null && len > 0 && len < MAX_LENGTH){
            matcher = pattern.matcher(login);
            result = matcher.matches();
            matcher.reset();
        }
        return result;
    }

    //validates any Users names - first, second doesn't meter
    public static boolean validateName(String name){
        boolean result  = false;
        String regEx = RB.getString("user_name");
        pattern = Pattern.compile(regEx);
        int len = name.length();
        if(name != null && len > 0 && len < MAX_LENGTH){
            matcher = pattern.matcher(name);
            result = matcher.matches();
            matcher.reset();
        }
        return result;
    }

}

