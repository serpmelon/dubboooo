package com.togo.exception;

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
 * @date Created in 2019年10月15日 15:42
 * @since 1.0
 */
public class MultiImplServiceException extends RuntimeException {

    public MultiImplServiceException(String msg) {

        super(msg);
    }
}