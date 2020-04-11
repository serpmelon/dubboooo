package com.togo.consumer.stub;

import com.togo.annotation.scan.Key;
import com.togo.register.Register;
import com.togo.register.ServiceAddress;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
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
 * @date Created in 2019年10月12日 12:54
 * @since 1.0
 */
@Slf4j
public class RPCClient {

    private Map<Key, PrintStream> outMap = new HashMap<>();
    private Map<Key, BufferedReader> inputMap = new HashMap<>();

    private static RPCClient client = new RPCClient();

    public static RPCClient instance(){
        return client;
    }

    /**
     * ont time function
     * @param msg
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public Object sendMsg(String msg, Key key) throws IOException {

        PrintStream out = outputStream(key);
        BufferedReader input = inputStream(key);
        out.println(msg);

        String ret = input.readLine();
        log.info("服务器端返回过来的是: " + ret);

        out.close();
        input.close();

        return ret;
    }

    private void buildSocket(Key key) {

        ServiceAddress address = Register.instance().scan(key);
        //创建一个流套接字并将其连接到指定主机上的指定端口号
        Socket socket;
        try {
            socket = new Socket(address.getHost(), address.getPort());
            //读取服务器端数据
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //向服务器端发送数据
            PrintStream out = new PrintStream(socket.getOutputStream());
            inputMap.put(key, input);
            outMap.put(key, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PrintStream outputStream(Key key) {

        if (outMap.get(key) != null)
            return outMap.get(key);

        buildSocket(key);

        return outMap.get(key);
    }

    public BufferedReader inputStream(Key key) {

        if (inputMap.get(key) != null)
            return inputMap.get(key);

        buildSocket(key);

        return inputMap.get(key);
    }
}