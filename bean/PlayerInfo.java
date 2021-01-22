package com.ricefieldpro.bean;

import java.io.Serializable;

/**
 * Created by niu on 2021/1/21
 **/
public class PlayerInfo implements Serializable{

    String playerIp;
    String name;

    public String getPlayerIp() {
        return playerIp;
    }

    public void setPlayerIp(String playerIp) {
        this.playerIp = playerIp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
