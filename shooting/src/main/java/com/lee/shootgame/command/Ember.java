package com.lee.shootgame.command;

import java.awt.image.BufferedImage;

/**
 * @Author Avin lee
 * @Create 2019/8/24 10:25 by IntelliJ IDEA
 * @Description  飞行物被子弹击中后的残影
 */
public class Ember {
    private BufferedImage[] images = {};
    private int index;
    private int i;
    private BufferedImage image;
    private int intevel = 10;
    private int x, y;

    public Ember(FlyingObject object) {
        images = object.ember;
        image = object.image;
        x = object.x;
        y = object.y;
        index = 0;
        i = 0;
    }

    public boolean burnDown() {
        i++;
        if (i % intevel == 0) {
            if (index == images.length) {
                return true;
            }
            image = images[index++];
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getImage() {
        return image;
    }
}