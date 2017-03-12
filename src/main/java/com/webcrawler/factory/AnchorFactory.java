package com.webcrawler.factory;

import java.util.List;

import org.jsoup.nodes.Document;

import com.webcrawler.domain.Anchor;

public interface AnchorFactory {

    public List<Anchor> createAnchor(String selector, Document document);

}
