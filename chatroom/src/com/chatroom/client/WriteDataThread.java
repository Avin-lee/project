package com.chatroom.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author Lee
 * @Date 2019/6/23 13:35
 */
public class WriteDataThread extends Thread{
    private final Socket socket;
    public WriteDataThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            OutputStream out = this.socket.getOutputStream();
            PrintStream printStream = new PrintStream(out);
            Scanner scanner = new Scanner(System.in);
            while(true){
                System.out.println("请输入>");
                String message = scanner.nextLine();
                printStream.println(message);
                printStream.flush();
                if(message.equals("bye")){
                    break;
                }
            }
            this.socket.close();
        } catch (IOException e) {

        }
    }

}
