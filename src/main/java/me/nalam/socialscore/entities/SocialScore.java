package me.nalam.socialscore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class SocialScore implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    @Column(nullable = false)
    private String tld;
    @Column(name = "social_score", nullable = false)
    private long socialScore;

    public SocialScore() {
    }

    public SocialScore(String id, String tld, long socialScore) {
        this.id = id;
        this.tld = tld;
        this.socialScore = socialScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTld() {
        return tld;
    }

    public void setTld(String tld) {
        this.tld = tld;
    }

    public long getSocialScore() {
        return socialScore;
    }

    public void setSocialScore(long socialScore) {
        this.socialScore = socialScore;
    }

    @Override
    public String toString() {
        return "SocialScore{" +
                "id='" + id + '\'' +
                ", tld='" + tld + '\'' +
                ", socialScore=" + socialScore +
                '}';
    }
}
