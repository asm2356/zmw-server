package com.iflytek.manager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iflytek.common.model.Config;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author AZhao
 */
@Repository
public interface ConfigDao extends BaseMapper<Config> {
    List<Config> getConfigList(
            @Param("startNum") int startNum,
            @Param("pageSize") int pageSize,
            @Param("key") String key,
            @Param("value") String value,
            @Param("describe") String describe);

    int getConfigNumber(@Param("key") String key,
                        @Param("value") String value,
                        @Param("describe") String describe);
}
