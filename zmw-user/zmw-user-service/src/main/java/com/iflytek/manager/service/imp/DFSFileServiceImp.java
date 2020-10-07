package com.iflytek.manager.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.iflytek.common.model.DFSFile;
import com.iflytek.common.utils.RandomUtils;
import com.iflytek.manager.api.DFSFileService;
import com.iflytek.manager.dao.DFSFileDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AZhao
 */
@Service
public class DFSFileServiceImp implements DFSFileService {

    @Autowired
    private DFSFileDao dfsFileDao;

    @Override
    public String addFile(DFSFile dfsFile) {
        dfsFile.setId(RandomUtils.getUUID());
        return dfsFileDao.insert(dfsFile) > 0 ? dfsFile.getId() : null;
    }

    @Override
    public int updateFile(final DFSFile oldDfsFile, final DFSFile newDfsFile) {
        Map<String, DFSFile> map = new HashMap<String, DFSFile>() {
            private static final long serialVersionUID = 1160828616637988657L;

            {
                put("oldDfsFile", oldDfsFile);
                put("newDfsFile", newDfsFile);
            }
        };
        return dfsFileDao.updateFile(map);
    }

    @Override
    public int deleteFileById(String id) {
        return dfsFileDao.deleteById(id);
    }
}

