package com.lgren.school.student.pojo;

import java.util.Date;

public class AdvertisingImg {
    private Integer id;

    private String adImgName;

    private String adImgUri;

    private String adUrl;

    private String insertWorkerId;

    private Date insertTime;

    private Date updateTime;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdImgName() {
        return adImgName;
    }

    public void setAdImgName(String adImgName) {
        this.adImgName = adImgName == null ? null : adImgName.trim();
    }

    public String getAdImgUri() {
        return adImgUri;
    }

    public void setAdImgUri(String adImgUri) {
        this.adImgUri = adImgUri == null ? null : adImgUri.trim();
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl == null ? null : adUrl.trim();
    }

    public String getInsertWorkerId() {
        return insertWorkerId;
    }

    public void setInsertWorkerId(String insertWorkerId) {
        this.insertWorkerId = insertWorkerId == null ? null : insertWorkerId.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}