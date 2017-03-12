package com.webcrawler.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.webcrawler.util.HTMLVersionHelper;

public class WebPage {

    private String url;
    private String documentVersion;
    private List<Anchor> anchors;
    private Heading heading;
    private String title;
    private FormDocument form;
    private int internalLinks;
    private int externalLinks;

    public WebPage(String url) {
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setDocumentVersion(String valueDocumentType) {
        this.documentVersion = HTMLVersionHelper.getVersion(valueDocumentType);
    }

    public String getDocumentVersion() {
        return documentVersion;
    }

    public List<Anchor> getAnchors() {
        return anchors;
    }

    public void setAnchors(List<Anchor> anchors) {
        this.anchors = anchors;
    }

    public Heading getHeading() {
        return heading;
    }

    public void setHeading(Heading heading) {
        this.heading = heading;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FormDocument getForm() {
        return form;
    }

    public void setForm(FormDocument form) {
        this.form = form;
    }

    public int getInternalLinks() {
        return internalLinks;
    }

    public void setInternalLinks(int internalLinks) {
        this.internalLinks = internalLinks;
    }

    public int getExternalLinks() {
        return externalLinks;
    }

    public void setExternalLinks(int externalLinks) {
        this.externalLinks = externalLinks;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }



}
