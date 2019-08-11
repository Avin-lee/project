package com.cashsystem.cmd.impl.common;

import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.annotation.CustomerCommand;
import com.cashsystem.cmd.annotation.EntranceCommand;
import com.cashsystem.cmd.impl.AbstractCommand;
import com.cashsystem.cmd.impl.Subject;

/**
 * @Author Avin
 * @Create 2019/8/5 14:51
 * @Description
 */
@CommandMeta(
        name = "GYXT",
        desc = "关于系统",
        group = "公共命令"
)
@AdminCommand
@CustomerCommand
@EntranceCommand
public class AboutCommand extends AbstractCommand {

    @Override
    public void execute(Subject subject) {
        System.out.println("**************************");
        System.out.println("*  基于字符界面的收银台系统  *");
        System.out.println("*     作者：Avin Lee      *");
        System.out.println("*    时间：2019-08-04     *");
        System.out.println("**************************");
    }
}
