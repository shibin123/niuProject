package com.ricefieldpro.bean;

import java.io.Serializable;

/**
 * Created by niu on 2021/1/21
 **/
public class PlayArribute implements Serializable {
    String volume;
    String moduleList;
    WebInfo webInfo;
    PlayData playData;

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getModuleList() {
        return moduleList;
    }

    public void setModuleList(String moduleList) {
        this.moduleList = moduleList;
    }

    public WebInfo getWebInfo() {
        return webInfo;
    }

    public void setWebInfo(WebInfo webInfo) {
        this.webInfo = webInfo;
    }

    public PlayData getPlayData() {
        return playData;
    }

    public void setPlayData(PlayData playData) {
        this.playData = playData;
    }
}
