package com.chatroom.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author Lee
 * @Date 2019/6/23 13:36
 */
public class ClientHandler implements Runnable{
    private final Socket client;
    private String name;
    private static final Map<String,Socket> socketMap = new ConcurrentHashMap<>();
    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            InputStream in = this.client.getInputStream();
            Scanner scanner = new Scanner(in);
            while(true){
                String line = scanner.nextLine();
                if(line.startsWith("register:")){
                    String[] segment = line.split(":");
                    if(segment.length==2 && segment[0].equals("register")){
                        String name = segment[1];
                        register(name);
                    }
                    continue;
                }
                if(line.startsWith("groupChat:")){
                    String[] segment = line.split(":");
                    if(segment.length==2 && segment[0].equals("group")){
                        String message = segment[1];
                        groupChat(message);
                    }
                    continue;
                }
                if(line.startsWith("privateChat:")){
                    String[] segments = line.split(":");
                    if(segments.length == 3 && segments[0].equals("privateChat")){
                        String name = segments[1];
                        String message = segments[2];
                        privateChat(name,message);
                    }
                    continue;
                }
                if(line.equalsIgnoreCase("bye")){
                    quiteChat();
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void quiteChat() {
        socketMap.remove(this.name);
        System.out.println(this.name + "已经离线！");
        printOnlineClient();
    }

    private void privateChat(String name, String message) {
        Socket socket = socketMap.get(name);
        if(socket != null){
            sendMessage(socket,this.name+"说:"+message);
        }
    }

    private void groupChat(String message) {
        for(Map.Entry<String,Socket> entry : socketMap.entrySet()){
            Socket socket = entry.getValue();
            if(socket == this.client){
                continue;
            }
            sendMessage(socket,this.name+"说：" + message);
        }
    }

    private  void register(String name){
        this.name = name;
        socketMap.put(name,this.client);
        System.out.println(name + "注册到系统中！");
        sendMessage(this.client,"欢迎"+name+"注册成功！");
        printOnlineClient();
    }

    private void printOnlineClient(){
        System.out.println("当前在线的客户端有：" + socketMap.size() + "个，名称列表如下：");
        for (String name : socketMap.keySet()) {
            System.out.println(name);
        }
    }

    private void sendMessage(Socket socket,String message){
        try {
            OutputStream out = socket.getOutputStream();
            PrintStream printStream = new PrintStream(out);
            printStream.println(message);
            printStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
