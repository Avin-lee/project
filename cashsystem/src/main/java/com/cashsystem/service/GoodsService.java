package com.cashsystem.service;

import com.cashsystem.dao.GoodsDao;
import com.cashsystem.entity.Goods;

import java.util.List;

/**
 * @Author Avin
 * @Create 2019/8/6 15:45 by IntelliJ IDEA
 * @Description
 */
public class GoodsService {
    public GoodsDao goodsDao;
    public GoodsService(){
        this.goodsDao = new GoodsDao();
    }
    public List<Goods> quarryAllGoods(){
        return this.goodsDao.quarryAllGoods();
    }
    public boolean putAwayGoods(Goods goods){
        return this.goodsDao.putAwayGoods(goods);
    }
    public Goods getGoods(int goodsId){
        return this.goodsDao.getGoods(goodsId);
    }
    public boolean modifyGoods(Goods goods){
        return this.goodsDao.modifyGoods(goods);
    }
    public boolean soldOutGoods(Goods goods){
        return this.goodsDao.soldOutGoods(goods);
    }
    public boolean updateAfterPay(Goods goods,int goodsNum){
        return this.goodsDao.updateAfterPay(goods,goodsNum);
    }
}
