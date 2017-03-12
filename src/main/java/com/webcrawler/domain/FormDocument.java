package com.webcrawler.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class FormDocument {

    private String method;
    private String action;
    private int numFields;

    public FormDocument(String method, String action, int numFields) {
        super();
        this.method = method;
        this.action = action;
        this.numFields = numFields;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getNumFields() {
        return numFields;
    }

    public void setNumFields(int numFields) {
        this.numFields = numFields;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }


}
