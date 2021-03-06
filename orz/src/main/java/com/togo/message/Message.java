package com.togo.message;

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
 * @date Created in 2019年10月14日 20:51
 * @since 1.0
 */
public class Message {

    /**
     * 服务类名称
     */
    private String klassName;
    /**
     * 服务别名
     */
    private String alias;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 方法参数类型
     */
    private Class<?>[] parameterKlassNameArrays;
    /**
     * 方法参数值
     */
    private Object[] parameterArrays;

    public String getKlassName() {
        return klassName;
    }

    public void setKlassName(String klassName) {
        this.klassName = klassName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterKlassNameArrays() {
        return parameterKlassNameArrays;
    }

    public void setParameterKlassNameArrays(Class<?>[] parameterKlassNameArrays) {
        this.parameterKlassNameArrays = parameterKlassNameArrays;
    }

    public Object[] getParameterArrays() {
        return parameterArrays;
    }

    public void setParameterArrays(Object[] parameterArrays) {
        this.parameterArrays = parameterArrays;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}