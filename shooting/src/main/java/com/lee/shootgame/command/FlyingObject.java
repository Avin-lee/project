package com.lee.shootgame.command;

import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * @Author Avin lee
 * @Create 2019/8/21 11:47 by IntelliJ IDEA
 * @Description 飞行物类，所有可以飞的类父类，抽象出了x，y，width以及height属性，image属性表示他们显示的贴图。Lombok提供getter、setter方法。
 */
@Data
public abstract class FlyingObject {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected BufferedImage image;
    protected BufferedImage[] ember;


    public abstract void step();

    public abstract boolean outOfBounds();

    public boolean shootBy(Bullet bullet) {
        if (bullet.isBomb()) {
            return false;
        }
        int x = bullet.x;  //子弹横坐标
        int y = bullet.y;  //子弹纵坐标
        boolean shoot = this.x < x && x < this.x + width && this.y < y && y < this.y + height;
        if (shoot) {
            bullet.setBomb(true);
        }
        return shoot;
    }
}
