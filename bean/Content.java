package com.ricefieldpro.bean;

/**
 * Created by niu on 2021/1/21
 **/
public class Content {

    /**
     * mediaId : 6
     * mediaName : 12312
     * fileName : ewqewqewq
     * location : D://media
     * totalSize : 32131
     * md5 : 8563456
     */

    private String mediaId;
    private String mediaName;
    private String fileName;
    private String location;
    private String totalSize;
    private String md5;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(String totalSize) {
        this.totalSize = totalSize;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
