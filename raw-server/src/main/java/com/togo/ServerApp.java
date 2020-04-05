package com.togo;


import com.togo.context.Context;
import com.togo.provider.stub.RPCServer;
import com.togo.util.ContextUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URL;

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
@Slf4j
public class ServerApp {

    public static void main(String[] args) {

        try {
            log.info("init");
            RPCServer.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}