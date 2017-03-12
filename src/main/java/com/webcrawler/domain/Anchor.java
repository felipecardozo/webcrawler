package com.webcrawler.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Anchor {

    private String url;
    private String name;
    private boolean isInternal;

    public Anchor(String url, String name, boolean isInternal) {
        super();
        this.url = url;
        this.name = name;
        this.isInternal = isInternal;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public boolean isInternal() {
        return isInternal;
    }

    public void setInternal(boolean isInternal) {
        this.isInternal = isInternal;
    }

}
