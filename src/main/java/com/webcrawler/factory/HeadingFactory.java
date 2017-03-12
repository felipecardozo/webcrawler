package com.webcrawler.factory;

import java.util.List;

import org.jsoup.select.Elements;

import com.webcrawler.domain.HeadingType;

public interface HeadingFactory {

    public List<String> createHeading(HeadingType headingType, Elements elements);

}
