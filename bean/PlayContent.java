package com.ricefieldpro.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by niu on 2021/1/21
 **/
public class PlayContent implements Serializable {

    List<PlayItem> playItems;
    PlayArribute playArribute;

    public List<PlayItem> getPlayItems() {
        return playItems;
    }

    public void setPlayItems(List<PlayItem> playItems) {
        this.playItems = playItems;
    }

    public PlayArribute getPlayArribute() {
        return playArribute;
    }

    public void setPlayArribute(PlayArribute playArribute) {
        this.playArribute = playArribute;
    }
}
