package com.chatroom.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @Author Lee
 * @Date 2019/6/23 13:35
 */
public class ReadDataThread extends Thread {
    private final Socket socket;
    public ReadDataThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run(){
        try {
            InputStream in = this.socket.getInputStream();
            Scanner scanner = new Scanner(in);
            while(true){
                try{
                    String message = scanner.nextLine();
                    System.out.println("来自服务端>"+message);
                }catch(NoSuchElementException e){
                    break;
                }
            }
        } catch (IOException e) {

        }
    }
}
