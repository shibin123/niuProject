package com.ricefieldpro.bean;

import java.io.Serializable;

/**
 * Created by niu on 2021/1/21
 **/
public class PlayData implements Serializable{


    /**
     * fileName : gfhgjrty
     * totalTime : 540
     * mediaType : zip
     * mediaId : 16
     * mediaName : 信息.zip
     */

    private String fileName;
    private String totalTime;
    private String mediaType;
    private String mediaId;
    private String mediaName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }
}
