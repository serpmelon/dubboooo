package com.togo.stub;

import com.alibaba.fastjson.JSONObject;
import com.togo.annotation.scan.Key;
import com.togo.protocol.message.Message;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Map;

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

    public static void init() throws UnsupportedEncodingException {

        String root = URLDecoder.decode(RPCServer.class.getResource("/").getPath(), String.valueOf(Charset.defaultCharset()));
        System.out.println(root);
        scan(root);
    }

    /**
     * <pre>
     * desc : 扫描全部类
     * @author : taiyn
     * date : 2019-10-15 15:44
     * @param : [root]
     * @return java.util.Map<com.togo.annotation.scan.Key, java.lang.Class>
     * </pre>
     */
    private static Map<Key, Class> scan(String root) {

        File file = new File(root);
        allFiles(file, root);

        return null;
    }

    /**
     * <pre>
     * desc : 扫描所有的类路径，把它们加入到上线文
     * @author : taiyn
     * date : 2019-10-15 16:37
     * @param : [file, root]
     * @return void
     * </pre>
     */
    private static void allFiles(File file, String root) {

        if (file.isDirectory()) {

            File[] files = file.listFiles();
            if (files == null)
                return;

            for (File f : files) {

                if (f.isDirectory())
                    allFiles(f, root);
                else {
                    String path = f.getAbsolutePath();

                    Context.INSTANCE.addFile(handlePathToClass(path, root));
                }
            }
        }
    }

    /**
     * <pre>
     * desc : 处理类路径
     * @author : taiyn
     * date : 2019-10-15 16:38
     * @param : [path, root]
     * @return java.lang.String
     * </pre>
     */
    private static String handlePathToClass(String path, String root) {

        path = path.substring(root.length());
        path = path.replace('/', '.');
        return path.substring(0, path.length() - ".class".length());
    }

//    private static void loadImpl() {
//
//        for(String path : )
//    }

    public static void main(String[] args) {
        try {
            init();
            System.out.println(Context.INSTANCE.getAllFiles());
            System.out.println();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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