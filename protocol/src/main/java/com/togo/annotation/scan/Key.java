package com.togo.annotation.scan;

import java.util.Arrays;
import java.util.Objects;

/**
 * <p>扫描类的时候，记录类的名称</p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author taiyn
 * @version 1.0
 * @date Created in 2019年10月15日 12:30
 * @since 1.0
 */
public class Key {

    private String name;
    private String[] alias;

    public Key(String name) {

        this.name = name;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return Objects.equals(name, key.name) &&
                Arrays.equals(alias, key.alias);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(alias);
        return result;
    }

    @Override
    public String toString() {
        return "Key{" +
                "name='" + name + '\'' +
                ", alias=" + Arrays.toString(alias) +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAlias() {
        return alias;
    }

    public void setAlias(String... alias) {
        this.alias = alias;
    }
}