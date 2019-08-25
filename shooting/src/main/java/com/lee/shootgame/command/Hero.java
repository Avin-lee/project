package com.lee.shootgame.command;

import java.awt.image.BufferedImage;

/**
 * @Author Avin lee
 * @Create 2019/8/21 12:21 by IntelliJ IDEA
 * @Description 表示玩家角色，属飞行物。
 */
public class Hero extends FlyingObject{
    private int life;  //生命值
    private int doubleFire;  //火力值
    private BufferedImage[] images;  //图片数组
    private int index;  //控制切换的频率

    public Hero(){
        this.image = Game.hero0;  //hero图片有两个，默认选hero0；
        this.ember = Game.heroEmber;
        width = image.getWidth();
        height = image.getHeight();
        x = 150;
        y = 400;
        life = 3;
        doubleFire = 0;  //单倍火力
        images = new BufferedImage[]{Game.hero0,Game.hero1};
        index = 0;
    }

    @Override
    public void step() {
        image = images[index++/10%images.length];  //玩家角色没100ms切换一次
    }

    @Override
    public boolean outOfBounds() {
        return false;
    }

    //玩家角色发射子弹(创建子弹对象，有可能单发，也有可能双发，返回类型为Bullet[])
    public Bullet[] shoot(){
        int xStep = this.width / 4;
        int yStep = 40;
        //火力值为0单发，火力值大于0双发
        if(doubleFire > 0){
            Bullet[] bullets = new Bullet[2];
            bullets[0] = new Bullet(this.x + 1*xStep,this.y - yStep);
            bullets[1] = new Bullet(this.x + 3*xStep,this.y - yStep);
            doubleFire -= 2;  //发一次双倍火力，火力值减2
            return bullets;
        }else {
            Bullet[] bullets = new Bullet[1];
            bullets[0] = new Bullet(this.x + 2*xStep,this.y - yStep);
            return bullets;
        }
    }

    public void moveTo(int x,int y){
        this.x = x - this.width/2;
        this.y = y - this.height/2;
    }

    //生命值+1
    public void addLife(){
        life++;
    }

    //碰撞生命值减少
    public void subtractLife(){
        life--;
    }

    //火力值变为双倍
    public void addDoubleFire(){
        doubleFire += 40;
    }

    //碰撞设置火力值
    public void setDoubleFire(int doubleFire){
        this.doubleFire = doubleFire;  //将传过来的火力值付给hero
    }

    //获取当前生命值
    public int getLife(){
        return this.life;
    }

    //玩家角色与敌人的碰撞检测
    public boolean hit(FlyingObject other){

        //x1,x2,y1,y2所表示的是以敌人为中心，边长为 hero + enemyflyings 的矩形

        int x1 = other.x - this.width/2;
        int x2 = other.x + other.width + this.width/2;
        int y1 = other.y - this.height;
        int y2 = other.y + other.height + this.height/2;
        int hx = this.x + this.width/2;  //代表英雄机的中心x坐标
        int hy = this.y + this.height/2;  //代表英雄机的中心y坐标
        return hx > x1 && hx < x2  //若玩家角色hero的中心点在以x1,x2,y1,y2为中心的矩形内部，则表示撞上了
                &&
                hy > y1 && hy < y2;
    }

}
