package com.lee.shootgame.command;

import java.util.Random;

/**
 * @Author Avin lee
 * @Create 2019/8/21 12:10 by IntelliJ IDEA
 * @Description 表示Buff奖励加成，属飞行物，属奖励。
 */
public class Buff extends FlyingObject implements Award {

    private int xspeed = 1;  //x坐标的移动速度
    private int yspeed = 2;  //y坐标的移动速度
    private int awardType;  //奖励类型

    @Override
    public int getType() {
        return awardType;
    }

    public Buff(){
        this.image = Game.buff;
        this.ember = Game.buffEmber;
        width = image.getWidth();
        height = image.getHeight();
        Random random = new Random();
        //this在buff中表示的时buff
        x = random.nextInt(Game.WIDTH - this.width);
        y = -this.height;
        //x = 100;
        //y = 200;
        awardType = random.nextInt(2);
    }

    @Override
    public void step() {
        x += xspeed;
        y += yspeed;
        if(x > Game.WIDTH - this.width){
            xspeed = -1;
        }
        if (x < 0) {
            xspeed = 1;
        }
    }

    @Override
    public boolean outOfBounds() {
        return this.y > Game.HEIGHT;
    }
}
