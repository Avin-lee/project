package com.lee.shootgame.command;

/**
 * @Author Avin lee
 * @Create 2019/8/21 12:15 by IntelliJ IDEA
 * @Description 表示子弹，属飞行物。
 */
public class Bullet extends FlyingObject {
    private int speed = 3;  //向上移动的速度
    private boolean bomb;

    public Bullet(int x,int y){
        this.x = x;
        this.y = y;
        this.image = Game.bullet;
    }

    public void setBomb(boolean bomb){
        this.bomb = bomb;
    }

    public boolean isBomb(){
        return bomb;
    }

    @Override
    public void step() {
        y -= speed;
    }

    @Override
    public boolean outOfBounds() {
        return this.y < -this.height;
    }
}
