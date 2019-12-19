package com.pqw.anime_community.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "anime_topic")
@DynamicUpdate
@DynamicInsert
public class AnimeTopic {

    @Id
    @Column(name = "anime_id")
    private String animeId;
    @Column(name = "anime_name")
    private String animeName;
    @Column(name = "anime_small_photo")
    private String animeSmallPhoto;
    @Column(name="anime_big_photo")
    private String animeBigPhoto;
    @Column(name="anime_location")
    private String animeLocation;
    @Column(name="anime_quarter")
    private String animeQuarter;
    @Column(name="anime_time")
    private Date animeTime;
    @Column(name="anime_type")
    private String animeType;
    @Column(name="anime_heat")
    private Integer animeHeat;
    @Column(name = "anime_profile")
    private String animeProfile;

    public String getAnimeId() {
        return animeId;
    }

    public void setAnimeId(String animeId) {
        this.animeId = animeId;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public String getAnimeSmallPhoto() {
        return animeSmallPhoto;
    }

    public void setAnimeSmallPhoto(String animeSmallPhoto) {
        this.animeSmallPhoto = animeSmallPhoto;
    }

    public String getAnimeBigPhoto() {
        return animeBigPhoto;
    }

    public void setAnimeBigPhoto(String animeBigPhoto) {
        this.animeBigPhoto = animeBigPhoto;
    }

    public String getAnimeLocation() {
        return animeLocation;
    }

    public void setAnimeLocation(String animeLocation) {
        this.animeLocation = animeLocation;
    }

    public String getAnimeQuarter() {
        return animeQuarter;
    }

    public void setAnimeQuarter(String animeQuarter) {
        this.animeQuarter = animeQuarter;
    }

    public Date getAnimeTime() {
        return animeTime;
    }

    public void setAnimeTime(Date animeTime) {
        this.animeTime = animeTime;
    }

    public String getAnimeType() {
        return animeType;
    }

    public void setAnimeType(String animeType) {
        this.animeType = animeType;
    }

    public Integer getAnimeHeat() {
        return animeHeat;
    }

    public void setAnimeHeat(Integer animeHeat) {
        this.animeHeat = animeHeat;
    }

    public String getAnimeProfile() {
        return animeProfile;
    }

    public void setAnimeProfile(String animeProfile) {
        this.animeProfile = animeProfile;
    }

    @Override
    public String toString() {
        return animeId+"  地区:"+animeLocation+"  季度:"+animeQuarter+"  "+
                "  时间:"+animeTime+"  类型:"+animeType+";";
    }
}
