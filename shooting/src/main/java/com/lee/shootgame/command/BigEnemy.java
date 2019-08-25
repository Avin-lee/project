package com.lee.shootgame.command;

import java.util.Random;

/**
 * @Author Avin lee
 * @Create 2019/8/24 10:00 by IntelliJ IDEA
 * @Description  具有生命的敌机，消灭后会有奖励
 */
public class BigEnemy extends FlyingObject implements Enemy,Award {

    private int speed = 1;
    private int life;
    private int awardType;

    public BigEnemy() {
        life = 4;
        this.image = Game.bigenemy;
        this.ember = Game.bigenemyEmber;
        width = image.getWidth();
        height = image.getHeight();
        y = -height;
        Random rand = new Random();
        x = rand.nextInt(Game.WIDTH - width);
        awardType = rand.nextInt(2);
    }

    public int getType() {
        return awardType;
    }

    @Override
    public int getScore() {
        return 50;
    }

    @Override
    public boolean outOfBounds() {
        return false;
    }

    @Override
    public void step() {   //移动
        y += speed;
    }

    public boolean shootBy(Bullet bullet) {
        if(super.shootBy(bullet)){
            life--;
        }
        return life==0;
    }
}
