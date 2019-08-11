package com.cashsystem.cmd.impl.goods;

import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.impl.AbstractCommand;
import com.cashsystem.cmd.impl.Subject;
import com.cashsystem.entity.Goods;

import java.util.List;

/**
 * @Author Avin
 * @Create 2019/8/5 14:54
 * @Description
 */
@CommandMeta(
        name = "GXSP",
        desc = "更新商品",
        group = "商品信息"
)
@AdminCommand
public class GoodsUpdateCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("更新商品");
        System.out.println("现有商品如下：");
        List<Goods> goodsList = this.goodsService.quarryAllGoods();
        if(goodsList.isEmpty()){
            System.out.println("商品为空！");
        }else {
            for(Goods goods : goodsList){
                System.out.println(goods);
            }
        }
        System.out.println("请输入更新商品的编号：");

        int goodsId = scanner.nextInt();
        Goods goods = this.goodsService.getGoods(goodsId);
        if (goods == null) {
            System.out.println("没有此编号的货物！");
            return;
        }else {
            System.out.println("商品信息如下：");
            System.out.println(goods);
        }
        System.out.println("请输入新的商品简介：");
        String introduce = scanner.next();
        System.out.println("请输入新的商品库存：");
        int stock = Integer.parseInt(scanner.next());
        System.out.println("请输入新的商品单位：包，个，箱...");
        String unit = scanner.next();
        System.out.println("请输入新的商品价格：单位元");
        int price = new Double(100*scanner.nextDouble()).intValue();
        System.out.println("请输入新的商品折扣：");
        int discount = scanner.nextInt();



        System.out.println("是否更新? （y/n 或者 yes/no）");
        String flag = scanner.next();
        if("y".equalsIgnoreCase(flag) || "yes".equalsIgnoreCase(flag)){
            goods.setIntroduce(introduce);
            goods.setStock(stock);
            goods.setUnit(unit);
            goods.setPrice(price);
            goods.setDiscount(discount);

            boolean effect = this.goodsService.modifyGoods(goods);
            if (effect){
                System.out.println("商品更新成功！");
            }else {
                System.out.println("商品更新失败！");
            }
        }else {
            System.out.println("更新取消，商品未变更。");
        }
    }
}
