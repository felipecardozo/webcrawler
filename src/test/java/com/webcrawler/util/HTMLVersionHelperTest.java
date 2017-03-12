package com.webcrawler.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HTMLVersionHelperTest {

    @Test
    public void shouldReturnHtml5() {
        assertEquals("HTML5", HTMLVersionHelper.getVersion("<!DOCTYPE HTML>"));
    }

    @Test
    public void shouldReturnHtml4() {
        assertEquals("HTML 4.01", HTMLVersionHelper.getVersion("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">"));
    }

    @Test
    public void shouldReturnHtml1() {
        assertEquals("XHTML 1.0", HTMLVersionHelper.getVersion("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"));
    }

}
