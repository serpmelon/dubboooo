package com.togo.provider.stub;

import com.alibaba.fastjson.JSONObject;
import com.togo.annotation.scan.Key;
import com.togo.context.Context;
import com.togo.message.Message;
import com.togo.register.Register;
import com.togo.util.ContextUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.Charset;

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
@Slf4j
public class RPCServer {

    private static void start() throws Exception {

        String address = "127.0.0.1";
        int port = 9024;

        log.info("server start address : [{}], port: [{}]", address, port);

//        Register.INSTANCE.signIn();

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(address, port));

        while (true) {

            Socket socket = serverSocket.accept();

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientInputStr = input.readLine();//这里要注意和客户端输出流的写方法对应,否则会抛 EOFException
            // 处理客户端数据
            System.out.println("客户端发过来的内容:" + clientInputStr);

            String result = (String) handleRequest(clientInputStr);
            // 向客户端回复信息
            PrintStream out = new PrintStream(socket.getOutputStream());
            out.println(result);

            out.close();
            input.close();
        }
    }

    /**
     * <pre>
     * desc : 初始化，扫描类，加载实现类等；
     * @author : taiyn
     * date : 2019-10-15 16:43
     * @param : []
     * @return void
     * </pre>
     */
    public static void init() throws Exception {

        log.info(Thread.currentThread().getStackTrace()[1].getClassName());
        String root = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        ContextUtil.scanAndLoad(root);
        start();
    }

    private static Object handleRequest(String msg) throws Exception {

        Message message = JSONObject.parseObject(msg, Message.class);

        String klassName = message.getKlassName();

        Key key = new Key(klassName);
        key.setAlias(message.getAlias());
        klassName = Context.INSTANCE.getServiceImpl(key);
        Class klass = Class.forName(klassName);
        Class[] param = message.getParameterKlassNameArrays();
        Method method = klass.getMethod(message.getMethodName(), param);

        return method.invoke(klass.newInstance(), message.getParameterArrays());
    }
}