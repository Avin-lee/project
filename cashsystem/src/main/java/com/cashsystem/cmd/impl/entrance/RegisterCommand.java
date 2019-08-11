package com.cashsystem.cmd.impl.entrance;

import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.annotation.EntranceCommand;
import com.cashsystem.cmd.impl.AbstractCommand;
import com.cashsystem.cmd.impl.Subject;
import com.cashsystem.common.AccountStatus;
import com.cashsystem.common.AccountType;
import com.cashsystem.entity.Account;

/**
 * @Author Avin
 * @Create 2019/8/5 14:53
 * @Description
 */
@CommandMeta(
        name = "ZC",
        desc = "注册",
        group = "入口命令"
)
@EntranceCommand
public class RegisterCommand extends AbstractCommand {

    @Override
    public void execute(Subject subject) {
        System.out.println("请输入用户名：");
        String username = scanner.nextLine();
        System.out.println("请输入密码：");
        String password1 = scanner.nextLine();
        System.out.println("请再次输入密码：");
        String password2 = scanner.nextLine();
        if(!password1.endsWith(password2)){
            System.out.println("两次密码不一致！");
            return;
        }
        System.out.println("请输入姓名：");
        String name = scanner.nextLine();
        System.out.println("请输入账户的类型：1.管理员 2.用户");
        int accountType = scanner.nextInt();
        AccountType accountType2 = AccountType.valueof(accountType);
        System.out.println("请输入用户的状态：1.启用 2.启停");
        int accountStatus1 = scanner.nextInt();
        AccountStatus accountStatus = AccountStatus.valueof(accountStatus1);

        final Account account = new Account();
        account.setUsername(username);
        account.setPassword(password1);
        account.setName(name);
        account.setAccountType(accountType2);
        account.setAccountStatus(accountStatus);
        boolean effect = this.accountService.Register(account);
        if(effect){
            System.out.println("注册成功！");
        }else {
            System.out.println("注册失败！");
        }

    }
}
