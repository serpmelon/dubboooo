package com.togo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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
 * @date Created in 2019年10月12日 12:53
 * @since 1.0
 */
public class RPCServer {

    public static void start() throws IOException {

        String address = "127.0.0.1";
        int port = 9024;

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(address, port));

        while (true) {

            Socket socket = serverSocket.accept();

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientInputStr = input.readLine();//这里要注意和客户端输出流的写方法对应,否则会抛 EOFException
            // 处理客户端数据
            System.out.println("客户端发过来的内容:" + clientInputStr);

            // 向客户端回复信息
            PrintStream out = new PrintStream(socket.getOutputStream());
            System.out.print("请输入:\t");
            // 发送键盘输入的一行
            String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
            out.println(s);

            out.close();
            input.close();
        }

    }
}