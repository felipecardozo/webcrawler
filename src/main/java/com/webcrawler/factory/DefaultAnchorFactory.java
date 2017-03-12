package com.webcrawler.factory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webcrawler.domain.Anchor;
import com.webcrawler.util.WebCrawlerHelper;

public class DefaultAnchorFactory implements AnchorFactory {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultAnchorFactory.class);

    @Override
    public List<Anchor> createAnchor(String selector, Document document) {
        Elements anchors = document.select("a[href]");
        List<Anchor> anchorsWithFormat = null;

        if (!anchors.isEmpty()) {
            anchorsWithFormat = new ArrayList<>(anchors.size());
            for (Element anchor : anchors) {
                String url = WebCrawlerHelper.getAnchorWithFormat("%s", anchor.attr("abs:href"), WebCrawlerHelper.trim(anchor.text(), 35));
                URI uri = null;
                try {
                    uri = new URI(url);
                    String host = uri.getHost();
                    String domain = WebCrawlerHelper.getUrlDomainName(document.baseUri());
                    anchorsWithFormat.add(new Anchor(url, anchor.text(), host.contains(domain)));
                } catch (URISyntaxException | NullPointerException e) {
                    LOG.info("something in createAnchor went bad {}", e.getMessage());
                }

            }
            return anchorsWithFormat;
        }
        return Collections.emptyList();
    }

}
