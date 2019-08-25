package com.lee.shootgame.command;

/**
 * @Author Avin lee
 * @Create 2019/8/21 11:56 by IntelliJ IDEA
 * @Description 奖励接口，如果子弹击中了buff，玩家可获得奖励（双倍火力或者生命值+1）。
 */
public interface Award {
    int DOUBLE_FIRE = 0;  //0表示奖励为双倍火力，1表示生命值+1
    int LIFE = 1;
    int getType();
}
