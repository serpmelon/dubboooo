package com.togo;

import com.togo.stub.RPCServer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
 * @date Created in 2019年10月12日 13:39
 * @since 1.0
 */
public class ServerApp {

    public static void main(String[] args) {

        try {
            RPCServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}