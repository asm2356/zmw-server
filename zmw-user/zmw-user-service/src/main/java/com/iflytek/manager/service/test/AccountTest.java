package com.iflytek.manager.service.test;

import com.alibaba.fastjson.JSON;
import com.iflytek.common.model.Account;
import com.iflytek.manager.api.AccountService;
import com.iflytek.manager.dao.AccountDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AZhao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/managerServiceContext.xml"})
public class AccountTest {
    private Logger logger = LoggerFactory.getLogger(AccountTest.class);
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountDao accountDao;
    @Test
    public void getUserCount() {
        logger.info(accountService.getUserCount() + "");
    }
    @Test
    public void testMybatisPlus(){
        Account account = new Account();
        account.setMwId(accountService.generateMwId());
        account.setPhone("1231231");
        account.setPwd("487");
        account.setStatus(123);
        accountDao.insert(account);
    }
    @Test
    public void addAccount() {
        Account account = new Account();
        account.setPhone("1231231");
        account.setPwd("487");
        account.setStatus(123);
        logger.info(accountService.addAccount(account) + "");
    }

    @Test
    public void updateAccount() {
        Account account = new Account();
        account.setMwId("mw11014");
        account.setPhone("11111111");
        account.setPwd("22222222");
        account.setStatus(33333);
        logger.info(accountService.updateAccount(account) + "");
    }

    @Test
    public void findAccountByMwId() {
        logger.info(JSON.toJSONString(accountService.findAccountByMwId("mw11011")));
    }

    @Test
    public void findAccountByPhone() {
        logger.info(accountService.findAccountByPhone("15755336799") + "");
    }

    @Test
    public void isPhoneRegister() {
        logger.info("====================" + accountService.isPhoneRegister("15755336796"));
    }

    @Test
    public void addRole() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(4);
        logger.info(accountService.addRole("mw11018", list) + "");
    }

    @Test
    public void removeRole() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(4);
        logger.info(accountService.removeRole("mw11018", list) + "");
    }

    @Test
    public void addPermission() {
        List<Integer> list = new ArrayList<>();
        list.add(7);
        list.add(8);
        logger.info(accountService.addPermission("mw11012", list) + "");
    }

    @Test
    public void getRoleList() {
        logger.info( JSON.toJSONString(accountService.getRoleList(null)));
    }


    @Test
    public void removeAllRole() {
        logger.info(accountService.removeAllRole("mw11018") + "");
    }

    @Test
    public void removePermission() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        logger.info(accountService.removePermission("mw11012", list) + "");
    }

    @Test
    public void isExistPermission() {
        logger.info(accountService.isExistPermission("mw11011", 1) + "");
    }

    @Test
    public void getPermissionList() {
        logger.info(JSON.toJSONString(accountService.getPermissionList()));
    }

    @Test
    public void removeAllPermission() {
        logger.info(accountService.removeAllPermission("mw11018") + "");
    }

    @Test
    public void getManagerUserList() {
        logger.info(JSON.toJSONString(accountService.getManagerUserList(0, 10, null, null, null, null)));
    }
    @Test
    public void getManagerUserNumber() {
        logger.info(JSON.toJSONString(accountService.getManagerUserNumber(null, null, null, "")));
    }
}