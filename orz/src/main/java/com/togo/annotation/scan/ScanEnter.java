package com.togo.annotation.scan;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author taiyn
 * @Description 标记scan入口位置
 * @Date 9:28 上午 2020/4/5
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ScanEnter {
}
