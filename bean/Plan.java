package com.ricefieldpro.bean;

/**
 * Created by niu on 2021/1/20
 **/
public class Plan {


    /**
     * seal : d404ed70-490c-4d89-a2b6-a0aec2451f9f
     * revision : 20170531006
     * timestamp : 2020-02-26 13:41:50
     * playerInfo : {"playerIp":"10.9.21.131","name":"国家图书馆上行播放器"}
     * playerScreen : {"displayNo":"1","channel":{"name":"查询机","seal":"bf02a60afb76430992f605fea4dec751","identifier":"","revision":"20200417174542","timestamp":1587116748957,"layout":{"width":2160,"height":3840,"mediaId":6,"fileName":"ewqewqewq","mediaName":"12312","emergArribute":"","frameSet":[{"name":"web组件1","track":"","playType":"web","x":0,"y":0,"width":2160,"height":840,"playContent":{"playItems":[],"playArribute":{"webInfo":{"volume":"","fileName":"gasgsagdsaf","totalTime":"","mediaType":"zip","mediaId":17,"mediaName":"顶部web.zip"}}}},{"name":"custom组件2","track":"","playType":"custom","x":0,"y":840,"width":2160,"height":1500,"playlist":29,"playContent":{"playItems":[],"playArribute":{}}},{"name":"视频组件3","track":"","playType":"video","x":0,"y":2340,"width":2160,"height":1500,"playlist":12,"playContent":{"playItems":[],"playArribute":{}}}]}},"playlists":{"12":{"id":12,"name":"视频播表","type":"video","playlistCharactor":"","playContent":{"playItems":[{"mediaId":7,"mediaName":"幅度萨芬","fileName":"qewqe","mediaType":"video","totalTime":"540"},{"mediaId":9,"mediaName":"ccca","fileName":"fasf","mediaType":"video","totalTime":"540"},{"mediaId":11,"mediaName":"bbbb","fileName":"xczx","mediaType":"video","totalTime":"540"}],"playArribute":{"volume":"50"}}},"29":{"id":29,"name":"信息播表","type":"custom","playlistCharactor":"","playContent":{"playItems":[],"playArribute":{"moduleList":"aroundInfo,routeMap,FLTrain,travelQuery","playData":{"fileName":"gfhgjrty","totalTime":"540","mediaType":"zip","mediaId":16,"mediaName":"信息.zip"}}}}},"contents":{"11":{"mediaId":11,"mediaName":"Wildlife.wmv","fileName":"c243d62f-a67a-46c2-b8a4-aff408636193.wmv","location":"/media","totalSize":6432,"md5":"78B39643A04B91D5C031C02455AAFC363D1B6920"},"6":{"mediaId":6,"mediaName":"12312","fileName":"ewqewqewq","location":"D://media","totalSize":32131,"md5":"8563456"},"17":{"mediaId":17,"mediaName":"顶部web.zip","fileName":"gasgsagdsaf","location":"D://media","totalSize":754,"md5":"3543253276"},"7":{"mediaId":7,"mediaName":"幅度萨芬","fileName":"qewqe","location":"D://media","totalSize":31241,"md5":"8576543"},"9":{"mediaId":9,"mediaName":"ccca","fileName":"fasf","location":"D://media","totalSize":5324,"md5":"7534543254"}}}
     */

    private String seal;
    private String revision;
    private String timestamp;
    private PlayerInfo playerInfo;
    private PlayerScreen playerScreen;

    public String getSeal() {
        return seal;
    }

    public void setSeal(String seal) {
        this.seal = seal;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    public PlayerScreen getPlayerScreen() {
        return playerScreen;
    }

    public void setPlayerScreen(PlayerScreen playerScreen) {
        this.playerScreen = playerScreen;
    }
}
