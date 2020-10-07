package com.iflytek.manager.service.imp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.iflytek.common.enumeration.user.UserRole;
import com.iflytek.common.enumeration.user.UserStatus;
import com.iflytek.common.exception.DBException;
import com.iflytek.common.model.Account;
import com.iflytek.common.model.Article;
import com.iflytek.common.model.UserBaseInfo;
import com.iflytek.config.service.other.FastDFSClient;
import com.iflytek.manager.api.AccountService;
import com.iflytek.manager.api.ArticleService;
import com.iflytek.manager.api.UserBaseInfoService;
import com.iflytek.manager.api.UserService;
import com.iflytek.manager.dao.AccountDao;
import com.iflytek.search.api.SearchUserBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
@Service
public class UserServiceImp implements UserService {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserBaseInfoService userBaseInfoService;

    @Autowired
    private AccountDao accountDao;
    @Override
    public int getUserPraiseNumber(final String mwId) {
        Map<String, Object> map = new HashMap<String, Object>() {
            private static final long serialVersionUID = -9120036990338036735L;
            {
                put("startNum", 0);
                put("pageSize", Long.MAX_VALUE);
                put("mwId", mwId);
            }
        };
        List<Article> articleList = new ArrayList<>();
        try {
            articleList = articleService.getArticleList(map);
        } catch (DBException e) {
            e.printStackTrace();
        }
        int praiseNumber = 0;
        for (Article article : articleList) {
            praiseNumber += article.getPraiseNumber();
        }
        return praiseNumber;
    }

    @Override
    public void addUser(Account account)  {
        account.setStatus(UserStatus.NOEMAL.getValue());
        accountService.addAccount(account);
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setMwId(account.getMwId());
        userBaseInfo.setAlias("用户" + userBaseInfo.getMwId());
        userBaseInfo.setCover(FastDFSClient.DEFAULT_USER_COVER);
        userBaseInfo.setHeader(FastDFSClient.DEFAULT_USER_HEADER);
        userBaseInfo.setIntroduction("主人很懒什么都没有留下");
        userBaseInfo.setSex("未知");
        List<Integer> list = new ArrayList<Integer>() {
            private static final long serialVersionUID = 1250519244953743544L;

            {
                add(UserRole.Ordinary.getValue());
            }
        };
        accountDao.addRole(account.getMwId(), list);
        userBaseInfoService.addUserBaseInfo(userBaseInfo);
    }
}
