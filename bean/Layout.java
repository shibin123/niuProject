package com.ricefieldpro.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by niu on 2021/1/21
 **/
public class Layout implements Serializable {

    String width;
    String height;
    String mediaId;
    String fileName;
    String mediaName;
    String emergArribute;
    List<FrameSet> frameSet;

    public String getEmergArribute() {
        return emergArribute;
    }

    public void setEmergArribute(String emergArribute) {
        this.emergArribute = emergArribute;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public List<FrameSet> getFrameSet() {
        return frameSet;
    }

    public void setFrameSet(List<FrameSet> frameSet) {
        this.frameSet = frameSet;
    }
}
