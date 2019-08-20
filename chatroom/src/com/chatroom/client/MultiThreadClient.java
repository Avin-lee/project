package com.chatroom.client;

import java.io.IOException;
import java.net.Socket;

/**
 * @Author Lee
 * @Date 2019/6/23 13:34
 */
public class MultiThreadClient {

    public static void main(String[] args) {
        String defaultHost = "127.0.0.1";
        int defaultPort = 4406;
        String host = defaultHost;
        int port = defaultPort;
        for(String arg : args){
            if(arg.startsWith("--port=")){
                String portStr = arg.substring("--port=".length());
                try{
                    port = Integer.parseInt(portStr);
                }catch(NumberFormatException e){
                    port = defaultPort;
                }
            }
            if(arg.startsWith("--host=")){
                String hostStr = arg.substring("--host=".length());
                host = hostStr;
            }
        }


        try {
            final Socket socket = new Socket(host,port);
            new WriteDataThread(socket).start();
            new ReadDataThread(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
