package com.lee.shootgame.command;

import java.util.Random;

/**
 * @Author Avin lee
 * @Create 2019/8/21 11:58 by IntelliJ IDEA
 * @Description 表示敌机，属飞行物，属敌人。
 */
public class EnemyFlyings extends FlyingObject implements Enemy{

    private int speed = 2;  //向下的移动速度

    public EnemyFlyings(){
        this.image = Game.enemyflyings;
        this.ember = Game.enemyEmber;
        width = image.getWidth();
        height = image.getHeight();
        Random random = new Random();

        //this在enemyflyings中表示的时敌机
        x = random.nextInt(Game.WIDTH - this.width);  //x为在（页面宽度—自己贴图宽度）的范围产内生的随机数
        y = -this.height;
        //x = 300;
        //y = 300;
    }

    @Override
    public int getScore() {
        return 5;
    }

    @Override
    public void step() {
        y += speed;
    }

    @Override
    public boolean outOfBounds() {
        return this.y > Game.HEIGHT;
    }

}
