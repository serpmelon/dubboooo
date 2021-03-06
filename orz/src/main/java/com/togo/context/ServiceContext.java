package com.togo.context;

import com.togo.annotation.scan.Key;
import com.togo.exception.MultiImplServiceException;
import com.togo.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>服务端上下文，存储扫描到到类</p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author taiyn
 * @version 1.0
 * @date Created in 2019年10月15日 15:20
 * @since 1.0
 */
@Slf4j
public enum ServiceContext {

    INSTANCE;

    private List<String> allFiles;
    private Map<Key, String> allServiceImpls;

    ServiceContext(){
        allFiles = new ArrayList<>();
        allServiceImpls = new HashMap<>();
    }

    public void addFile(String path) {

        allFiles.add(path);
    }

    public void addServiceImpl(Key key, String path) {

        String oldPath = allServiceImpls.get(key);
        if (StringUtil.isNotEmpty(oldPath)) {

            throw new MultiImplServiceException(key.toString());
        }

        allServiceImpls.put(key, path);
    }

    public String getServiceImpl(Key key) {

        return allServiceImpls.get(key);
    }

    public Map<Key, String> getAllServiceImpls() {
        return allServiceImpls;
    }

    public List<String> getAllFiles() {
        return allFiles;
    }

    @Override
    public String toString() {
        return "Context{" +
                "allFiles=" + allFiles +
                ", allServiceImpls=" + allServiceImpls +
                '}';
    }
}