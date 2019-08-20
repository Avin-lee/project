package com.chatroom.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author Lee
 * @Date 2019/6/23 11:07
 */
public class SingleThreadClient {
    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 4406;
        Socket socket;
        {
            try{
                socket = new Socket(ip,port);
                OutputStream out = socket.getOutputStream();
                PrintStream printStream = new PrintStream(out);
                printStream.println("服务器我来啦");
                printStream.flush();
                InputStream in = socket.getInputStream();
                Scanner scanner = new Scanner(in);
                System.out.println("来自服务器的数据："+scanner.nextLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
