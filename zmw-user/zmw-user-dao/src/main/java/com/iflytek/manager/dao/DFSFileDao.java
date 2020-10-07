package com.iflytek.manager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iflytek.common.model.DFSFile;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author AZhao
 */
@Repository
public interface DFSFileDao extends BaseMapper<DFSFile> {
    int updateFile(Map<String, DFSFile> map);
}
