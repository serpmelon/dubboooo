package com.togo.stub;

import com.alibaba.fastjson.JSONObject;
import com.togo.protocol.message.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public static void start() throws Exception {

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

            String result = (String) handleMsg(clientInputStr);
            // 向客户端回复信息
            PrintStream out = new PrintStream(socket.getOutputStream());
            out.println(result);

            out.close();
            input.close();
        }
    }

    public static void init() {


    }

    public static Object handleMsg(String msg)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
            InstantiationException, InvocationTargetException {

        Message message = JSONObject.parseObject(msg, Message.class);

        String klassName = message.getKlassName();
        if ("com.tiger.dubbo.api.DemoService".equals(klassName))
            klassName = "com.togo.service.DemoServiceImpl";
        Class klass = Class.forName(klassName);
        Class[] param = message.getParameterKlassNameArrays();
        Method method = klass.getMethod(message.getMethodName(), param);

        return method.invoke(klass.newInstance(), message.getParameterArrays());
    }
}