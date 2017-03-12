package com.webcrawler.crawler;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.webcrawler.domain.Anchor;
import com.webcrawler.domain.FormDocument;
import com.webcrawler.domain.Heading;
import com.webcrawler.domain.HeadingType;
import com.webcrawler.domain.WebPage;
import com.webcrawler.factory.AnchorFactory;
import com.webcrawler.factory.DefaultAnchorFactory;
import com.webcrawler.factory.DefaultHeadingFactory;
import com.webcrawler.factory.HeadingFactory;

/**
 * This class provides an API compatible with {@link WebPage}, but with no guarantee of
 * synchronization</br>
 * This class is designed for use as a drop-in replacement for {@link WebPage}</br>
 * in places where the webpage was being used as a single tread</br>
 * the principal operations are buildConnection to start a connection for the given URL</br>
 * buildDocumentVersion, buildHeaders, buildTitle, buildAnchors and build
 * 
 * @author Felipe
 */
public class PageCrawlerBuilder {

    private Document document;
    private WebPage webPage;
    private String url;

    /**
     * Constructs a {@link PageCrawlerBuilder} initializing the internal URL
     */
    public PageCrawlerBuilder(String url) {
        this.url = url;
        this.webPage = new WebPage(url);
    }

    /**
     * builds the connection
     */
    public PageCrawlerBuilder buildConnection() throws IOException {
        this.document = Jsoup.connect(url).get();
        return this;
    }

    /**
     * builds the {@link WebPage} once the other methods have been built
     * 
     * @return {@link WebPage}
     */
    public WebPage build() {

        if (document == null) {
            throw new IllegalStateException("buildConnection has not been called");
        } else if (webPage.getAnchors() == null) {
            throw new IllegalArgumentException("buildAnchors has not been called");
        } else if (webPage.getDocumentVersion() == null) {
            throw new IllegalArgumentException("buildDocumentVersion has not been called");
        } else if (webPage.getTitle() == null) {
            throw new IllegalArgumentException("buildTitle has not been called");
        } else if (webPage.getHeading() == null) {
            throw new IllegalArgumentException("buildHeaders has not been called");
        }

        return webPage;
    }

    /**
     * Builds a {@link PageCrawlerBuilder} and sets the document version into the {@link WebPage}
     */
    public PageCrawlerBuilder buildDocumentVersion() {
        String valueDocumentType = "";

        List<Node> nods = document.childNodes();
        for (Node node : nods) {
            if (node instanceof DocumentType) {
                DocumentType documentType = (DocumentType) node;
                valueDocumentType = documentType.toString();
                // System.out.println(documentType.attr("publicId"));
            }
        }
        webPage.setDocumentVersion(valueDocumentType);
        return this;
    }

    /**
     * Builds a {@link PageCrawlerBuilder} and sets the headers into the {@link WebPage}
     */
    public PageCrawlerBuilder buildHeaders() {
        Elements headingTags = document.select("h1, h2, h3, h4, h5, h6");
        HeadingFactory factory = new DefaultHeadingFactory();
        if (!headingTags.isEmpty()) {
            List<String> h1Tags = factory.createHeading(HeadingType.H1, headingTags);
            List<String> h2Tags = factory.createHeading(HeadingType.H2, headingTags);
            List<String> h3Tags = factory.createHeading(HeadingType.H3, headingTags);
            List<String> h4Tags = factory.createHeading(HeadingType.H4, headingTags);
            List<String> h5Tags = factory.createHeading(HeadingType.H5, headingTags);
            List<String> h6Tags = factory.createHeading(HeadingType.H6, headingTags);
            webPage.setHeading(new Heading(h1Tags, h2Tags, h3Tags, h4Tags, h5Tags, h6Tags));
        }
        return this;
    }

    /**
     * Builds a {@link PageCrawlerBuilder} and sets the title into the {@link WebPage}
     */
    public PageCrawlerBuilder buildTitle() {
        Elements title = document.select("title");
        if (!title.isEmpty()) {
            webPage.setTitle(title.get(0).text());
        }
        return this;
    }

    /**
     * Builds a {@link PageCrawlerBuilder} and sets the anchors into the {@link WebPage}
     */
    public PageCrawlerBuilder buildAnchors() {
        AnchorFactory anchorFactory = new DefaultAnchorFactory();
        // http://amundsen.com/hypermedia/
        webPage.setAnchors(anchorFactory.createAnchor("a[href]", document));
        return this;
    }

    public PageCrawlerBuilder buildLoginForm() {
        Elements elements = document.select("form");
        if (!elements.isEmpty()) {
            for (Element element : elements) {
                String classForm = element.attr("class");
                boolean isLoginForm = false;
                if (classForm != null && (classForm.contains("signup") || classForm.contains("login"))) {
                    isLoginForm = true;
                }
                String action = element.attr("action");
                String method = element.attr("method");
                if (!isLoginForm && action != null && (action.contains("login") || action.contains("signup"))) {
                    isLoginForm = true;
                }
                if (isLoginForm) {
                    int sizeInputs = element.select("input[type=text]").size();
                    sizeInputs += element.select("input[type=password]").size();
                    webPage.setForm(new FormDocument(method, action, sizeInputs));
                }
            }
        }
        return this;
    }

    public PageCrawlerBuilder buildNumInternalLinks() {
        if (webPage.getAnchors().isEmpty()) {
            throw new IllegalArgumentException("Anchors has not been build");
        }
        int internalLinks = 0;
        List<Anchor> anchors = webPage.getAnchors();
        for (Anchor anchor : anchors) {
            if (anchor.isInternal())
                internalLinks++;
        }
        webPage.setInternalLinks(internalLinks);
        return this;
    }

    public PageCrawlerBuilder buildNumExternalLinks() {
        if (webPage.getAnchors().isEmpty()) {
            throw new IllegalArgumentException("Anchors has not been build");
        }
        int externalLinks = 0;
        List<Anchor> anchors = webPage.getAnchors();
        for (Anchor anchor : anchors) {
            if (!anchor.isInternal())
                externalLinks++;
        }
        webPage.setExternalLinks(externalLinks);
        return this;
    }

}
