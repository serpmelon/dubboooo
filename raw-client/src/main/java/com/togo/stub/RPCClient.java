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

    private static PrintStream out;
    private static BufferedReader input;

    public static void start() throws IOException, InterruptedException {

        String address = "127.0.0.1";
        int port = 9024;

        //创建一个流套接字并将其连接到指定主机上的指定端口号
        Socket socket = new Socket(address, port);

        //读取服务器端数据
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //向服务器端发送数据
        out = new PrintStream(socket.getOutputStream());
        System.out.print("客户端启动: \t\n");


    }

    public static Object sendMsg(String msg) throws IOException, InterruptedException {

        out.println(msg);

        String ret = input.readLine();
        System.out.println("服务器端返回过来的是: " + ret);

        out.close();
        input.close();

        return ret;
    }
}