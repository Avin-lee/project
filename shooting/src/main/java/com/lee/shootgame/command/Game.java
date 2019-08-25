package com.lee.shootgame.command;

import com.lee.shootgame.dao.Dao;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author Avin lee
 * @Create 2019/8/21 12:25 by IntelliJ IDEA
 * @Description 主程序页面。
 */
public class Game extends JPanel {

    //所有对象都一样称之为静态，图片属于静态资源
    public static final int WIDTH = 400;  //面板宽
    public static final int HEIGHT = 654;  //面板高

    //定义四个常量代表四个状态
    private int state;  //记录当前状态，初始为启动状态
    public static final int START = 0;  //启动状态
    public static final int RUNNING = 1;  //运行状态
    public static final int PAUSE = 2;  //暂停状态
    public static final int GAME_OVER = 3;  //结束状态

    int score = 0;//得分
    private Timer timer;  //定时器
    private int intervel = 1000/100;  //时间间隔 ms

    public static BufferedImage background;
    public static BufferedImage start;
    public static BufferedImage pause;
    public static BufferedImage gameOver;
    public static BufferedImage enemyflyings;
    public static BufferedImage bigenemy;
    public static BufferedImage buff;
    public static BufferedImage bullet;
    public static BufferedImage hero0;
    public static BufferedImage hero1;
    public static BufferedImage[] enemyEmber=new BufferedImage[4];
    public static BufferedImage[] buffEmber=new BufferedImage[4];
    public static BufferedImage[] heroEmber=new BufferedImage[4];
    public static BufferedImage[] bigenemyEmber=new BufferedImage[4];




    //enemyflyings + buff 统称为敌人，FlyingObject 可放这两个
    private FlyingObject[] flyingObjects = {};  //敌人数组：enemyflyings + buff
    private Bullet[] bullets = {};  //子弹数组
    private Hero hero = new Hero();  //玩家角色
    private Ember[] embers = {}; // 灰烬


    //使用静态块加载静态资源，给静态资源赋值
    static{
        try{
            background = ImageIO.read(new File("./src/main/java/com/lee/shootgame/images/background.png"));
            start = ImageIO.read(new File("./src/main/java/com/lee/shootgame/images/start.png"));
            pause = ImageIO.read(new File("./src/main/java/com/lee/shootgame/images/pause.png"));
            gameOver = ImageIO.read(new File("./src/main/java/com/lee/shootgame/images/gameover.png"));
            enemyflyings = ImageIO.read(new File("./src/main/java/com/lee/shootgame/images/enemyflyings.png"));
            buff = ImageIO.read(new File("./src/main/java/com/lee/shootgame/images/buff.png"));
            bullet = ImageIO.read(new File("./src/main/java/com/lee/shootgame/images/bullet.png"));
            hero0 = ImageIO.read(new File("./src/main/java/com/lee/shootgame/images/hero0.png"));
            hero1 = ImageIO.read(new File("./src/main/java/com/lee/shootgame/images/hero1.png"));
            bigenemy = ImageIO.read(new File("./src/main/java/com/lee/shootgame/images/bigenemy.png"));
            for(int i=0; i<4; i++){
                buffEmber[i] = ImageIO.read(
                        new File("./src/main/java/com/lee/shootgame/images/buffember"+i+".png"));
                enemyEmber[i] = ImageIO.read(
                        new File("./src/main/java/com/lee/shootgame/images/enemyember"+i+".png"));
                bigenemyEmber[i] = ImageIO.read(
                        new File("./src/main/java/com/lee/shootgame/images/bigenemyember"+i+".png"));
                /*heroEmber[i] = ImageIO.read(
                        new File("./src/main/java/com/lee/shootgame/images/heroember"+i+".png"));*/
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }



    //重写paint方法
    @Override
    public void paint(Graphics g){
        g.drawImage(background,0,0,null);  //绘制背景
        paintHero(g);
        paintEmber(g);
        paintFlyingObject(g);
        paintBullets(g);
        paintScore(g);
        paintState(g);
    }

    //绘制玩家角色
    private void paintHero(Graphics g){
        g.drawImage(hero.getImage(),hero.x,hero.y,null);
    }

    //绘制爆炸效果
    private void paintEmber(Graphics g){
        for (Ember e : embers) {
            g.drawImage(e.getImage(), e.getX(), e.getY(), null);
        }
    }

    //绘制敌人（enemyflyings+buff）
    private void paintFlyingObject(Graphics g){
        //每一个敌人
        for (FlyingObject flyingObject : flyingObjects) {
            g.drawImage(flyingObject.getImage(), flyingObject.x, flyingObject.y, null);
        }
    }

    //绘制子弹
    private void paintBullets(Graphics g){
        //每一发子弹
        for (Bullet bullet : bullets) {
            g.drawImage(bullet.getImage(), bullet.x, bullet.y, null);
        }
    }

    //绘制生命值和分数
    private void paintScore(Graphics g){
        g.setColor(new Color(0xFF0000));  //设置颜色
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,22));  //设置样式
        g.drawString("SCORE:" + score,10,25);
        g.drawString("LIFE:" + hero.getLife(),10,45);
    }

    //绘制状态
    private void paintState(Graphics g){
        switch (state) {  //根据当前状态画不同的图
            case START:
                g.drawImage(start, 0, 0, null);
                break;
            case PAUSE:
                g.drawImage(pause, 0, 0, null);
                break;
            case GAME_OVER:
                g.drawImage(gameOver, 0, 0, null);
                break;
        }
    }


    //主函数
    public static void main(String[] args) {
        JFrame frame = new JFrame("雷霆战机");
        Game game = new Game();  //面板对象
        frame.add(game);  //将面板添加到JFrame中
        frame.setSize(WIDTH,HEIGHT);  //大小
        frame.setAlwaysOnTop(true);  //设置窗口总在最上
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //默认关闭操作
        frame.setLocationRelativeTo(null);  //设置窗口初始位置：居中
        frame.setVisible(true);
        game.action();  //启动程序时执行
    }



    //启动程序时执行
    public void action(){

        MouseAdapter l = new MouseAdapter() {

            //鼠标移动事件
            @Override
            public void mouseMoved(MouseEvent e) {
                if(state == RUNNING){
                    int x = e.getX();  //鼠标的X坐标
                    int y = e.getY();  //鼠标的y坐标
                    hero.moveTo(x,y);
                }

            }

            //鼠标点击事件
            public void mouseClicked(MouseEvent e){
                switch (state){  //根据当前状态操作
                    case START:  //启动状态改为运行状态
                        state = RUNNING;
                        break;
                    case GAME_OVER:  //游戏结束变为启动
                        Dao dao = new Dao();
                        dao.save(score);
                        score = 0;  //清理分数归零
                        hero = new Hero();
                        flyingObjects = new FlyingObject[0];
                        bullets = new Bullet[0];
                        state = START;
                        break;
                }
            }

            //鼠标移出事件
            public void mouseExited(MouseEvent e){
                if(state == RUNNING){  //运行状态改为暂停状态
                    state = PAUSE;
                }
            }

            //鼠标进入事件
            public void mouseEntered(MouseEvent e){
                if(state == PAUSE){  //暂停状态改为运行状态
                    state = RUNNING;
                }
            }

        };
        this.addMouseListener(l);
        this.addMouseMotionListener(l);

        /*玩家角色随鼠标移动
         *事件：发生一个事件，并处理这个事件，即发生这个事件后我们所做的响应
         *事件： 鼠标点击事件——启动状态变成运行状态
         *      鼠标移动事件——英雄机跟着鼠标移动
         *      鼠标离开之后——运行状态变成暂停状态
         *      鼠标进入之后——暂停状态变成运行状态
         *
         *      如何将事件和事件处理联系在一起？ 采用监听器
         *      采用监听器如下两步：
         *        1、创建监听器对象
         *        2、安装监听器
         *           谁在操作呢？在操作谁呢？    鼠标在操作飞机
         *        3、这些事件都在谁的上面操作呢？   在窗体上操作
         *
         *        因此，要将监听器安装在窗体上
         *        //创建监听器对象
         *          MouseListener ml=new MouseListener(){MouseListener是一个接口，必须重写MouseListener的所有方法
         *        重写以下方法：
         *               mouseClicked()
         *               mousedPressed()
         *               mouseReleased()
         *               mouseEntered()
         *               mouseExited()
         *            }
         *        MouseMotionListener mml=new MouseMotionListener(){
         *        重写MouseMotionListener里面的所有方法：
         *           mouseDragged();
         *           mouseMoved();
         *         }
         *         将监听器安装在窗体上
         *         this.MouseListener();
         *         this.MouseMotionListener (mml);
         */

        timer = new Timer();  //创建定时器对象
        timer.schedule(new TimerTask() {
            //run（）写定时干的事，只要时间到就自动调用run（）
            @Override
            public void run() {  //运行时
                if(state == RUNNING){
                    enterAction();  //enemyflyings+buff入场
                    stepAction();  //飞行物走步
                    shootAction();  //玩家角色发射子弹，子弹入场
                    bangAction();  //子弹与敌人的碰撞
                    outOfBoundsAction();  //删除越界飞行物
                    checkGameOverAction();  //检查游戏结束
                    emberAction();
                }
                repaint();  //重画
            }
        },intervel,intervel);
    }


    private void emberAction(){
        Ember[] live = new Ember[embers.length];
        int index = 0;
        for (Ember ember : embers) {
            if (!ember.burnDown()) {
                live[index++] = ember;
            }
        }
        embers = Arrays.copyOf(live, index);
    }


    //enemyflyings+buff入场
    int flyEnterIndex = 0;  //敌人入场计数
    public void enterAction(){ //10ms走一次
        flyEnterIndex++;  //10ms增1
        if(flyEnterIndex%40 == 0){ //400(40*10)ms走一次  40/80/120/160/200

            //1.创建了一个敌人对象（enemyflyings对象 or buff对象）
            FlyingObject obj = nextOne();

            //2.将敌人对象添加到flyingObjects数组中
            //3.flyingObjects数组目前为止没有元素，要装元素就要扩容
            flyingObjects = Arrays.copyOf(flyingObjects,flyingObjects.length + 1);

            //4.将敌人元素添加到flyingObjects数组的最后一个元素
            flyingObjects[flyingObjects.length - 1] = obj;
        }
    }


    //飞行物走步  10ms走一次
    public void stepAction(){
        hero.step();
        for (FlyingObject flyingObject : flyingObjects) {
            flyingObject.step();
        }
        for (Bullet value : bullets) {
            value.step();
        }
    }



    //子弹入场
    int shootIndex = 0;
    public void shootAction(){ //10ms走一次
        shootIndex++;  //10ms增1
        if(shootIndex%30 == 0){  //300 (30*10) ms走一次
            Bullet[] bs = hero.shoot();
            //扩容：有几个子弹就扩几个容量
            bullets = Arrays.copyOf(bullets,bullets.length+bs.length);
            //追加数组
            System.arraycopy(bs,0,bullets,bullets.length - bs.length,bs.length);
            /*if(bs.length > 1){  //双发
                bullets[bullets.length - 2] = bs[0];
                bullets[bullets.length - 1] = bs[1];
            }else {  //单发
                bullets[bullets.length - 1] = bs[0];
            }*/
        }
    }


    //子弹击中飞行物检测
    public void bangAction(){
        for (Bullet value : bullets) {  //遍历所有子弹
            bang(value);  //bullets[i]代表每一个子弹，一个子弹与所有敌人碰撞
        }
    }


    //删除越界飞行物
    public void outOfBoundsAction(){

        //删除越界的敌人

        //有一个越界的就要赋值一次，效率太低
        /*for (int i = 0; i < flyingObjects.length; i++) {
            FlyingObject f = flyingObjects[i];
            if(f.outOfBounds()){
                FlyingObject t = flyingObjects[i];
                flyingObjects[i] = flyingObjects[flyingObjects.length - 1];
                flyingObjects[flyingObjects.length - 1] = t;
                flyingObjects = Arrays.copyOf(flyingObjects,flyingObjects.length - 1);
            }
        }*/

        int index = 0; //1.不越界敌人数组下标 2.不越界敌人个数
        //不越界敌人数组
        FlyingObject[] flyingAlives = new FlyingObject[flyingObjects.length];
        //获取每个敌人
        for (FlyingObject f : flyingObjects) {  //遍历所有敌人
            if (!f.outOfBounds()) {  //不越界
                //将不越界敌人对象添加到不越界敌人数组中
                flyingAlives[index++] = f;  //1.下标增1  2.不越界敌人个数增1
            }
        }
        //将不越界敌人数组元素复制到flyingObjects中
        //index为flyingObjects新数组的新长度
        flyingObjects = Arrays.copyOf(flyingAlives,index);


        //删除越界的子弹
        index = 0;  //归零
        //不越界子弹数组
        Bullet[] bulletAlives = new Bullet[bullets.length];
        for (Bullet b : bullets) {
            if (!b.outOfBounds()) {
                bulletAlives[index++] = b;
            }
        }
        bullets = Arrays.copyOf(bulletAlives,index);
    }

    //检测游戏是否结束
    public void checkGameOverAction(){
        if(isGameOver()){  //游戏结束
            state = GAME_OVER;
        }
    }


    //判断游戏是否结束
    public boolean isGameOver(){
        int index = -1;
        //一个英雄机和所有敌人撞
        for (int i = 0; i < flyingObjects.length; i++) {  //遍历所有敌人
            FlyingObject f = flyingObjects[i];  //获取每一个敌人
            if(hero.hit(f)){  //撞上了
                hero.subtractLife();  //玩家生命值减1
                hero.setDoubleFire(0);  //玩家火力值清零
                index = i;

                //玩家撞上敌人之后自己不产生爆炸效果，不然不美观
                /*Ember ember = new Ember(hero);
                embers = Arrays.copyOf(embers, embers.length+1);
                embers[embers.length-1]=ember;*/
            }
        }
        if(index != -1){
            FlyingObject t = flyingObjects[index];
            flyingObjects[index] = flyingObjects[flyingObjects.length-1];
            flyingObjects[flyingObjects.length-1] = t;
            flyingObjects = Arrays.copyOf(flyingObjects, flyingObjects.length-1);

            Ember ember = new Ember(t);
            embers = Arrays.copyOf(embers, embers.length+1);
            embers[embers.length-1]=ember;
        }
        return hero.getLife() <= 0;
    }


    //一个子弹与所有敌人的碰撞
    public void bang(Bullet b){
        int index = -1;  //被撞的敌人的下标不能为0，如果为0默认第一个被撞上
        for (int i = 0; i < flyingObjects.length; i++) {  //遍历所有敌人
            FlyingObject f = flyingObjects[i];  //获取每一个敌人
            if(f.shootBy(b)){  //如果敌人被子弹撞上
                index = i;  //记录被撞敌人的下标
                break;  //其余敌人不再做比较
            }
        }
        if (index != -1) {  //代表有撞上的
            FlyingObject one = flyingObjects[index];  //获取被撞敌人对象

            FlyingObject f = flyingObjects[index];  //将被撞敌人与数组中最后一个元素交换
            flyingObjects[index] = flyingObjects[flyingObjects.length - 1];
            flyingObjects[flyingObjects.length - 1] = f;

            flyingObjects = Arrays.copyOf(flyingObjects,flyingObjects.length - 1);  //缩容（删除最后一个被撞的元素）

            if(one instanceof Enemy){  //如果被撞对象是敌人
                Enemy e = (Enemy) one;  //将被撞对象强转敌人累加分数，因为score要绘制到页面上因此声名在bangAction上面
                score += e.getScore();
            }
            if(one instanceof Award){  //若果被撞对象是奖励
                Award a = (Award)one;
                int type = a.getType();  //获取奖励类型并判断
                switch (type) {
                    case Award.DOUBLE_FIRE:  //双倍火力
                        hero.addDoubleFire();
                        break;
                    case Award.LIFE:  //生命值
                        hero.addLife();
                        break;
                }
            }

            //飞行物变成灰烬
            Ember ember = new Ember(one);
            embers = Arrays.copyOf(embers,embers.length + 1);
            embers[embers.length - 1] = ember;
        }
    }

    //工厂方法的作用：产生对象的，创建敌人对象过程中是随机的，有可能是敌人有可能是蜜蜂
    //创建enemyflyings+buff对象
    public static FlyingObject nextOne(){
        Random random = new Random();
        int type = random.nextInt(20);
        if (type == 0) {  //随机数为0返回buff对象
            return new Buff();
        }else if(type<=2) {  //随机数为1-19返回enemyflyings对象
            return new BigEnemy();
        }else {
            return new EnemyFlyings();
        }
    }

}
