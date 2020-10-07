package com.iflytek.manager.service.test;

import com.iflytek.common.constant.ConfigConstant;
import com.iflytek.common.model.DFSFile;
import com.iflytek.config.service.app.SystemConfigUtils;
import com.iflytek.config.service.other.FastDFSClient;
import com.iflytek.manager.api.DFSFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author AZhao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/managerServiceContext.xml"})
public class DFSFileTest {
    @Autowired
    private DFSFileService dfsFileService;
    private Logger logger = LoggerFactory.getLogger(DFSFileTest.class);
    @Test
    public void addFile() {
        DFSFile dfsFile = new DFSFile();
        dfsFile.setHost("192.168.52.128:8888");
        dfsFile.setGroupName("group1");
        dfsFile.setPath("sdakshdfjkhqwej./sdfkl./s/df/s/df.jpg");
        logger.info(dfsFileService.addFile(dfsFile) + "");
    }

    @Test
    public void deleteFileById() {
        logger.info(dfsFileService.deleteFileById("61a9ccc70e454a6b9a792eed13f53752") + "");
    }


    @Test
    public void updateFile() {
        DFSFile oldDfsFile = new DFSFile(FastDFSClient.FILE_HOST, "group1", "11111111111");
        DFSFile newDfsFile = new DFSFile(FastDFSClient.FILE_HOST, "group2", "2222222222");
        logger.info(dfsFileService.updateFile(oldDfsFile, newDfsFile) + "");
    }
}
