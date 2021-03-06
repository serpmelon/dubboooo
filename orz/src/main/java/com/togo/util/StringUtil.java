package com.togo.util;

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
 * @date Created in 2019年10月15日 15:35
 * @since 1.0
 */
public class StringUtil {

    public static boolean isEmpty(String string) {

        return string == null || string.equals("");
    }

    public static boolean isNotEmpty(String string) {

        return !isEmpty(string);
    }
}