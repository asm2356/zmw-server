package com.iflytek.manager.api;

import com.iflytek.common.model.DFSFile;

/**
 * @author AZhao
 */
public interface DFSFileService {
    /**
     * @param host groupName path
     * @return 成功返回主键 失败返回 null
     * @author zgzhao
     * @describe
     */
    String addFile(DFSFile dfsFile);

    int updateFile(DFSFile oldDfsFile, DFSFile newDfsFile);

    int deleteFileById(String id);
}
