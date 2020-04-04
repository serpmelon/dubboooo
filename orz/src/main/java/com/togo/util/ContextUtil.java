package com.togo.util;

import com.togo.annotation.Orz;
import com.togo.annotation.scan.Key;
import com.togo.context.Context;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

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
 * @date Created in 2019年10月21日 20:40
 * @since 1.0
 */
@Slf4j
public class ContextUtil {

    /**
     * <pre>
     * desc : 扫描全部类
     * @author : taiyn
     * date : 2019-10-15 15:44
     * @param : [root]
     * @return java.util.Map<com.com.togo.annotation.scanAndLoad.Key, java.lang.Class>
     * </pre>
     */
    public static void scanAndLoad(String root) {

        log.info("start scanAndLoad");
        File file = new File(root);
        scan(file, root);
        loadImpl();
        System.out.println(Context.INSTANCE.getAllFiles());
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
    private static void scan(File file, String root) {

        if (file.isDirectory()) {

            File[] files = file.listFiles();
            if (files == null)
                return;

            for (File f : files) {

                if (f.isDirectory())
                    scan(f, root);
                else {
                    String path = f.getAbsolutePath();

                    log.info("scan and add path : [{}]", path);
                    handlePathToClass(path, root);
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
    private static void handlePathToClass(String path, String root) {

        if (!path.endsWith(".class"))
            return;
        path = path.substring(root.length());
        path = path.replace('/', '.');

        Context.INSTANCE.addFile(path.substring(0, path.length() - ".class".length()));
    }


    /**
     * <pre>
     * desc : 加载实现类
     * @author : taiyn
     * date : 2019-10-15 16:44
     * @param : []
     * @return void
     * </pre>
     */
    private static void loadImpl() {

        for (String path : Context.INSTANCE.getAllFiles()) {

            try {
                Class<?> klass = Class.forName(path);
                if (klass.isAnnotationPresent(Orz.class)) {
                    Class<?>[] interfaces = klass.getInterfaces();
                    for (Class<?> c : interfaces) {

                        Key key = new Key(c.getName());
                        Orz orz = klass.getDeclaredAnnotation(Orz.class);
                        if (StringUtil.isNotEmpty(orz.name())) {

                            key.setAlias(orz.name());
                        }
                        Context.INSTANCE.addServiceImpl(key, path);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void inject() {


    }
}