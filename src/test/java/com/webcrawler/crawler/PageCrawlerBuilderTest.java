package com.webcrawler.crawler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.Test;

import com.webcrawler.domain.WebPage;

public class PageCrawlerBuilderTest {

    PageCrawlerBuilder pageCrawler;
    WebPage webPage;

    @Test
    public void shouldNotNullAndNotReturnAnyError() {
        pageCrawler = new PageCrawlerBuilder("https://en.wikipedia.org/wiki/Main_Page");
        assertNotNull(pageCrawler);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnIllegalException() throws IOException {
        pageCrawler = new PageCrawlerBuilder(null);
        pageCrawler.buildConnection();
        assertNotNull(pageCrawler);
    }

    @Test(expected = UnknownHostException.class)
    public void shouldReturnUnkownHostExceptionAndUrlWasGiven() throws IOException {
        pageCrawler = new PageCrawlerBuilder("https://en.fakepage.org");
        assertNotNull(pageCrawler.buildConnection());
    }

    @Test
    public void shouldReturnAnchors() throws IOException {
        pageCrawler = new PageCrawlerBuilder("https://en.wikipedia.org/wiki/Main_Page");
        webPage = pageCrawler.buildConnection().buildAnchors().buildDocumentVersion().buildHeaders().buildTitle().build();
        assertNotNull(pageCrawler);
        assertNotNull(webPage);
        assertNotNull(webPage.getAnchors());
        assertTrue(webPage.getAnchors().size() > 0);
    }

    @Test
    public void shouldReturnVersionDocument() throws IOException {
        pageCrawler = new PageCrawlerBuilder("https://en.wikipedia.org/wiki/Main_Page");
        webPage = pageCrawler.buildConnection().buildAnchors().buildDocumentVersion().buildHeaders().buildTitle().build();
        assertNotNull(pageCrawler);
        assertNotNull(webPage);
        assertNotNull(webPage.getDocumentVersion());
        assertEquals("HTML5", webPage.getDocumentVersion());
    }

    @Test
    public void shouldReturnTitleDocument() throws IOException {
        pageCrawler = new PageCrawlerBuilder("https://en.wikipedia.org/wiki/Main_Page");
        webPage = pageCrawler.buildConnection().buildAnchors().buildDocumentVersion().buildHeaders().buildTitle().build();
        assertNotNull(pageCrawler);
        assertNotNull(webPage);
        assertNotNull(webPage.getTitle());
        assertEquals("Wikipedia, the free encyclopedia", webPage.getTitle());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWithoutCallConnection() throws IOException {
        pageCrawler = new PageCrawlerBuilder("https://en.wikipedia.org/wiki/Main_Page");
        webPage = pageCrawler.buildAnchors().buildDocumentVersion().buildHeaders().buildTitle().build();
        assertNotNull(pageCrawler);
        assertNotNull(webPage);
    }

    @Test
    public void shouldReturnFormforGitbub() throws Exception {
        pageCrawler = new PageCrawlerBuilder("https://github.com/");
        webPage = pageCrawler.buildConnection().buildAnchors().buildDocumentVersion().buildHeaders().buildTitle().buildLoginForm().build();
        assertNotNull(pageCrawler);
        assertNotNull(webPage);
        assertNotNull(webPage.getForm());
        assertEquals(3, webPage.getForm().getNumFields());
        assertEquals("/join", webPage.getForm().getAction());
        assertEquals("post", webPage.getForm().getMethod());
    }

    @Test
    public void shouldReturnFormforMienspiegel() throws Exception {
        pageCrawler = new PageCrawlerBuilder("https://www.spiegel.de/meinspiegel/login.html");
        webPage = pageCrawler.buildConnection().buildAnchors().buildDocumentVersion().buildHeaders().buildTitle().buildLoginForm().build();
        assertNotNull(pageCrawler);
        assertNotNull(webPage);
        assertNotNull(webPage.getForm());
        assertEquals(2, webPage.getForm().getNumFields());
        assertEquals("/meinspiegel/login.html", webPage.getForm().getAction());
        assertEquals("post", webPage.getForm().getMethod());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWithoutBuildRequiredProperties() throws IOException {
        pageCrawler = new PageCrawlerBuilder("https://en.wikipedia.org/wiki/Main_Page");
        webPage = pageCrawler.buildAnchors().buildDocumentVersion().build();
        assertNotNull(pageCrawler);
        assertNotNull(webPage);
        assertNotNull(webPage.getDocumentVersion());
    }

    @Test
    public void shouldReturnInternalAndExternalPages() throws Exception {
        pageCrawler = new PageCrawlerBuilder("https://en.wikipedia.org/wiki/Main_Page");
        webPage = pageCrawler.buildConnection().buildAnchors().buildDocumentVersion().buildHeaders().buildTitle().buildNumExternalLinks().buildNumInternalLinks().build();
        assertNotNull(pageCrawler);
        assertNotNull(webPage);
        assertEquals(202, webPage.getInternalLinks());
        assertEquals(141, webPage.getExternalLinks());
    }

}
