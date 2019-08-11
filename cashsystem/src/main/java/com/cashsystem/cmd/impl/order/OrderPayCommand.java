package com.cashsystem.cmd.impl.order;

import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.annotation.CustomerCommand;
import com.cashsystem.cmd.impl.AbstractCommand;
import com.cashsystem.cmd.impl.Subject;
import com.cashsystem.common.OrderStatus;
import com.cashsystem.entity.Goods;
import com.cashsystem.entity.Order;
import com.cashsystem.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Avin
 * @Create 2019/8/5 14:55
 * @Description
 */
@CommandMeta(
        name = "ZFDD",
        desc = "支付订单",
        group = "订单信息"
)
@CustomerCommand
public class OrderPayCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("请输入你要购买的商品ID以及数量。多个货物之间使用,号隔开：格式：1-8,3-2");
        String string = scanner.nextLine();
        String[] strings = string.split(",");
        //把所有需要购买的商品存放至goodsList
        List<Goods> goodsList = new ArrayList<>();

        for (String goodsString : strings){
            String[] str = goodsString.split("-");
            Goods goods = this.goodsService.getGoods(Integer.parseInt(str[0]));
            goods.setBuyGoodsNum(Integer.parseInt(str[1]));
            goodsList.add(goods);
        }
        Order order = new Order();
        order.setId(String.valueOf(System.currentTimeMillis()));
        order.setAccount_id(subject.getAccount().getId());
        order.setAccount_name(subject.getAccount().getName());
        order.setCreate_time(LocalDateTime.now());

        int totalMoney = 0;
        int actualMoney = 0;

        for (Goods goods : goodsList){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder_id(order.getId());
            orderItem.setGoods_id(goods.getId());
            orderItem.setGoods_name(goods.getName());
            orderItem.setGoods_introduce(goods.getIntroduce());
            orderItem.setGoods_num(goods.getBuyGoodsNum());
            orderItem.setGoods_unit(goods.getUnit());
            orderItem.setGoods_price(goods.getPrice());
            orderItem.setGoods_discount(goods.getDiscount());
            order.orderItemsList.add(orderItem);

            int currentMoney = goods.getBuyGoodsNum()*goods.getPrice();
            totalMoney += currentMoney;

            actualMoney += currentMoney*goods.getDiscount()/100;
        }
        order.setTotal_money(totalMoney);
        order.setActual_amount(actualMoney);
        order.setOrder_status(OrderStatus.PLAYING);

        System.out.println(order);
        System.out.println("请确认是否支付以上订单：确认输入：zf");
        String confirm = scanner.next();
        if ("zf".equalsIgnoreCase(confirm)) {
            order.setFinish_time((LocalDateTime.now()));
            order.setOrder_status(OrderStatus.OK);

            boolean effect = this.orderService.commitOrder(order);
            if(effect) {
                for (Goods goods : goodsList){
                    boolean isUpdate = this.goodsService.updateAfterPay(goods,goods.getBuyGoodsNum());
                    if(isUpdate){
                        System.out.println("库存更新成功！");
                    }else {
                        System.out.println("库存更新失败！");
                    }
                }
                System.out.println(order);
            }
        }else {
            System.out.println("订单没有支付成功，请重新下单！");
        }
    }
}
