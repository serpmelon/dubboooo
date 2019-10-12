package com.togo.stub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author taiyn
 * @version 1.0
 * @date Created in 2019年10月12日 12:54
 * @since 1.0
 */
public class RPCClient {

    public static void start() throws IOException, InterruptedException {

        String address = "127.0.0.1";
        int port = 9024;

        //创建一个流套接字并将其连接到指定主机上的指定端口号
        Socket socket = new Socket(address, port);

        //读取服务器端数据
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //向服务器端发送数据
        PrintStream out = new PrintStream(socket.getOutputStream());
        System.out.print("请输入: \t");
        String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
        out.println(str);

        String ret = input.readLine();
        System.out.println("服务器端返回过来的是: " + ret);
        // 如接收到 "OK" 则断开连接
        if ("OK".equals(ret)) {
            System.out.println("客户端将关闭连接");
            Thread.sleep(500);
        }

        out.close();
        input.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        start();
    }
}