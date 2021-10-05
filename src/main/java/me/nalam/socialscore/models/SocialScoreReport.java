package me.nalam.socialscore.models;

public class SocialScoreReport {
    private String domain;
    private long urls;
    private long socialScore;

    public SocialScoreReport(String domain, long urls, long socialScore) {
        this.domain = domain;
        this.urls = urls;
        this.socialScore = socialScore;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public long getUrls() {
        return urls;
    }

    public void setUrls(long urls) {
        this.urls = urls;
    }

    public long getSocialScore() {
        return socialScore;
    }

    public void setSocialScore(long socialScore) {
        this.socialScore = socialScore;
    }

    @Override
    public String toString() {
        return "SocialScoreReport{" +
                "domain='" + domain + '\'' +
                ", urls=" + urls +
                ", socialScore=" + socialScore +
                '}';
    }
}
