package com.cashsystem.cmd.impl;

import java.util.Scanner;

/**
 * @Author Avin
 * @Create 2019/8/5 14:46
 * @Description
 */
public interface Command {
    Scanner scanner = new Scanner(System.in);
    void execute(Subject subject);
}
