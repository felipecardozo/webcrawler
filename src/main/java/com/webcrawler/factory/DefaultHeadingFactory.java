package com.webcrawler.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.webcrawler.domain.HeadingType;
import com.webcrawler.util.WebCrawlerHelper;

public class DefaultHeadingFactory implements HeadingFactory {

    @Override
    public List<String> createHeading(HeadingType headingType, Elements elements) {
        List<String> headings = null;
        if (!elements.isEmpty()) {
            headings = new ArrayList<>();
            for (Element t : elements) {
                headings.add(WebCrawlerHelper.trim(t.text(), 35));
            }
            return headings;
        }

        return Collections.emptyList();
    }

}
