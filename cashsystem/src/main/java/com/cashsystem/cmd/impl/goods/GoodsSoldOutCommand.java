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
        name = "XJSP",
        desc = "下架商品",
        group = "商品信息"
)
@AdminCommand
public class GoodsSoldOutCommand extends AbstractCommand {

    @Override
    public void execute(Subject subject) {
        System.out.println("下架商品");
        System.out.println("现有商品如下：");
        List<Goods> goodsList = this.goodsService.quarryAllGoods();
        if (goodsList.isEmpty()) {
            System.out.println("商品为空！");
        }else {
            for (Goods goods : goodsList){
                System.out.println(goods);
            }
        }
        System.out.println("请输入要下架的商品编号：");
        int goodsId = scanner.nextInt();
        Goods goods = this.goodsService.getGoods(goodsId);
        if (goods == null) {
            System.out.println("对不起没有此商品！");
        }else{
            System.out.println("该商品信息如下：");
            System.out.println(goods);
            System.out.println("是否要下架"+goodsId+"号商品？ （y/n 或者 yes/no）");
            String result = scanner.next();

            if ("y".equalsIgnoreCase(result) || "yes".equalsIgnoreCase(result)) {
                boolean effect = this.goodsService.soldOutGoods(goods);
                if (effect) {
                    System.out.println("下架成功！");
                }else{
                    System.out.println("下架失败！");
                }
            }else {
                System.out.println("下架取消，商品未变更。");
            }
        }
    }
}
