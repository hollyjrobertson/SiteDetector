package com.site_detector.models;

public class Website {
    private String url;
    private String SiteStatus;

    public Website(String url, String SiteStatus) {
        this.url = url;
        this.SiteStatus = SiteStatus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSiteStatus() {
        return SiteStatus;
    }

    public void setSiteStatus(String SiteStatus) {
        this.SiteStatus = SiteStatus;
    }

    @Override
    public String toString() {
        return "Currently " + url + " is " + SiteStatus + "!";
    }
}
