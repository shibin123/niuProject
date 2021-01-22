package com.ricefieldpro.bean;

import java.io.Serializable;

/**
 * Created by niu on 2021/1/21
 **/
public class PlayList implements Serializable {

    String id;
    String name;
    String type;
    String playlistCharactor;

    PlayContent playContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlaylistCharactor() {
        return playlistCharactor;
    }

    public void setPlaylistCharactor(String playlistCharactor) {
        this.playlistCharactor = playlistCharactor;
    }

    public PlayContent getPlayContent() {
        return playContent;
    }

    public void setPlayContent(PlayContent playContent) {
        this.playContent = playContent;
    }
}
