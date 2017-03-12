package com.webcrawler.util;


/**
 * Class which helps to know what is the right version according to:
 * https://www.w3.org/QA/2002/04/valid-dtd-list.html
 */
public class HTMLVersionHelper {

    public static String getVersion(String value) {
        if (value.equalsIgnoreCase("<!DOCTYPE HTML>")) {
            return "HTML5";
        } else if (value.contains("4.01")) {
            return "HTML 4.01";
        } else if (value.contains("1.0")) {
            return "XHTML 1.0";
        } else if (value.contains("1.1")) {
            return "XHTML 1.0";
        } else if (value.contains("2.0")) {
            return "XHTML 1.0";
        } else if (value.contains("mathml")) {
            return "MathML";
        }

        return "";
    }

}
