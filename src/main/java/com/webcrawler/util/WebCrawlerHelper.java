package com.webcrawler.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawlerHelper {

    private static final String hostExtractorRegexString = "(?:https?://)?(?:www\\.)?(.+\\.)(com|au\\.uk|co\\.in|be|in|uk|org\\.in|org|net|edu|gov|mil)";
    private static final Pattern hostExtractorRegexPattern = Pattern.compile(hostExtractorRegexString);

    public static String getAnchorWithFormat(String msg, Object... args) {
        return String.format(msg, args);
    }

    public static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width - 1) + ".";
        else
            return s;
    }

    /**
     * Extracts the domain name from {@code url} by means of String manipulation rather than using
     * the {@link URI} or {@link URL} class.
     *
     * @param url is non-null.
     * @return the domain name within {@code url}.
     * @throws URISyntaxException
     */
    public static String getUrlDomainName(String url) throws URISyntaxException {
        if (url == null)
            return null;
        url = url.trim();
        Matcher m = hostExtractorRegexPattern.matcher(url);
        if (m.find() && m.groupCount() == 2) {
            return m.group(1) + m.group(2);
        } else {
            return null;
        }
    }

}
