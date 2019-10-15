package com.togo.stub;

import com.togo.annotation.scan.Key;
import com.togo.exception.MultiImplServiceException;
import com.togo.util.StringUtil;

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
public enum Context {

    INSTANCE;

    private List<String> allFiles;
    private Map<Key, String> allServiceImpls;

    Context(){
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

    public List<String> getAllFiles() {
        return allFiles;
    }

    public void setAllFiles(List<String> allFiles) {
        this.allFiles = allFiles;
    }

    public Map<Key, String> getAllServiceImpls() {
        return allServiceImpls;
    }

    public void setAllServiceImpls(Map<Key, String> allServiceImpls) {
        this.allServiceImpls = allServiceImpls;
    }
}