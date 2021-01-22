package com.ricefieldpro.bean;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niu on 2021/1/21
 **/
public class PlayerScreen implements Serializable {

    String displayNo;
    Channel channel;

    /***contents***/
    Object contents;
    List<Content> contentList = new ArrayList<>();
    Map<String, Content> contentHashMap = new HashMap<>();

    public Map<String, Content> getContentHashMap() {
        getContentList();
        return contentHashMap;
    }

    public List<Content> getContentList() {
        contentList = new ArrayList<>();

        Map<String, Object> tempContentHashMap = (Map<String, Object>) contents;
        Content content;
        for (String s : tempContentHashMap.keySet()) {
            content = new Content();

            Object contentObject = tempContentHashMap.get(s);
            Map<String, String> contentMap = (Map<String, String>) contentObject;
            content.setMediaId(String.valueOf(contentMap.get("mediaId")));
            content.setMediaName(contentMap.get("mediaName"));
            content.setFileName(contentMap.get("fileName"));
            content.setLocation(contentMap.get("location"));
            content.setTotalSize(String.valueOf(contentMap.get("totalSize")));
            content.setMd5(String.valueOf(contentMap.get("md5")));
            contentList.add(content);
            contentHashMap.put(s, content);
        }
        return contentList;
    }


    /******playlists*****/

    Object playlists;
    List<PlayList> list;
    Map<String, PlayList> PlayHashMap = new HashMap<>();

    public Map<String, PlayList> getPlayHashMap() {
        getPlaylists();
        return PlayHashMap;
    }

    public List<PlayList> getPlaylists() {
        list = new ArrayList<>();
        PlayList playList;
        Map<String, Object> tempPlayHashMap = (Map<String, Object>) playlists;
        for (String s : tempPlayHashMap.keySet()) {

            //PlayList
            Map<String, String> tempMap = (Map<String, String>) tempPlayHashMap.get(s);
            playList = new PlayList();
            playList.setId(String.valueOf(tempMap.get("id")));
            playList.setName(tempMap.get("name").toString());
            playList.setType(tempMap.get("type").toString());
            playList.setPlaylistCharactor(tempMap.get("playlistCharactor").toString());

            //PlayList - PlayContent
            PlayContent playContent = new PlayContent();
            Object contentObject = tempMap.get("playContent");
            Map<String, String> contentMap = (Map<String, String>) contentObject;

            //PlayList - PlayContent - playItems
            List<PlayItem> playItemList = new ArrayList<>();
            Object playItemObject = contentMap.get("playItems");
            playItemList = (List<PlayItem>) playItemObject;
            playContent.setPlayItems(playItemList);

            //PlayList - PlayContent - PlayArribute
            PlayArribute playArribute = new PlayArribute();
            Object PlayArributeObject = contentMap.get("playArribute");
            Map<String, String> playArributeMap = (Map<String, String>) PlayArributeObject;
            playArribute.setModuleList(playArributeMap.get("moduleList"));


            //PlayList - PlayContent - PlayArribute - PlayData
            PlayData playData = new PlayData();
            Object playDataObject = playArributeMap.get("playData");
            Map<String, String> playDataMap = (Map<String, String>) playDataObject;
            if (playDataMap != null) {
                playData.setFileName(playDataMap.get("fileName"));
                playData.setMediaId(String.valueOf(playDataMap.get("mediaId")));
                playData.setMediaName(playDataMap.get("mediaName"));
                playData.setMediaType(playDataMap.get("mediaType"));
                playData.setTotalTime(playDataMap.get("totalTime"));
            }
            //PlayList - PlayContent - PlayArribute - WebInfo
            WebInfo webInfo = new WebInfo();
            Object webInfoObject = playArributeMap.get("webInfo");
            Map<String, String> webInfoMap = (Map<String, String>) webInfoObject;
            if (webInfoMap != null) {
                webInfo.setMediaId(String.valueOf(webInfoMap.get("mediaId")));
                webInfo.setFileName(webInfoMap.get("fileName"));
                webInfo.setMediaName(webInfoMap.get("mediaName"));
                webInfo.setMediaType(webInfoMap.get("mediaType"));
                webInfo.setTotalTime(webInfoMap.get("totalTime"));
                webInfo.setVolume(webInfoMap.get("volume"));
            }

            playArribute.setPlayData(playData);
            playContent.setPlayArribute(playArribute);
            playList.setPlayContent(playContent);
            list.add(playList);
            PlayHashMap.put(s, playList);
        }

        return list;
    }


    public String getDisplayNo() {
        return displayNo;
    }

    public void setDisplayNo(String displayNo) {
        this.displayNo = displayNo;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
