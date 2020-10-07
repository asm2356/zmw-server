package com.iflytek.manager.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.iflytek.common.model.Article;
import com.iflytek.manager.api.CollectService;
import com.iflytek.manager.dao.CollectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
@Service
public class CollectServiceImp implements CollectService {
    @Autowired
    private CollectDao collectDao;

    @Override
    public List<Article> getCollectArticleList(final String mwId, final long startNum, final int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("mwId", mwId);
        map.put("startNum", startNum);
        map.put("pageSize", pageSize);
        return collectDao.getCollectArticleList(map);
    }

    @Override
    public int collectArticle(final String mwId, final String articleId) {
        Map<String, Object> map = new HashMap<String, Object>() {
            private static final long serialVersionUID = -7518261196326308959L;

            {
                put("mwId", mwId);
                put("articleId", articleId);
            }
        };
        try {
            return collectDao.addCollectArticle(map);
        } catch (DuplicateKeyException e) {
            //忽略 异常
            return -1;
            //throw new DBException(ResultCode.Data_Duplicate.getCode(), ResultCode.Data_Duplicate.getMessage());
        }
    }

    @Override
    public int cancelCollectArticle(final String mwId, final String articleId) {
        Map<String, Object> map = new HashMap<String, Object>() {
            private static final long serialVersionUID = 4915401922790763143L;

            {
                put("mwId", mwId);
                put("articleId", articleId);
            }
        };
        return collectDao.deleteCollectArticle(map);
    }

    @Override
    public int getCollectArticleNumber(String mwId) {
        return collectDao.getCollectArticleNumber(mwId);
    }

    @Override
    public int deleteCollectByArticleId(String articleId) {
        return collectDao.deleteCollectByArticleId(articleId);
    }
}
