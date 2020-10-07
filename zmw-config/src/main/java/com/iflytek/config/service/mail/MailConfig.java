package com.iflytek.config.service.mail;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;

/**
 * @author AZhao
 */
public class MailConfig {
    public static void main(String[] args) {
        MailAccount account = new MailAccount();
        account.setHost("smtp.163.com");
        account.setPort(25);
        account.setAuth(true);
        account.setFrom("");
        account.setUser("");
        account.setPass("");
        MailUtil.send(account, CollUtil.newArrayList(""), "", "", false);
        System.out.println("发送成功");
    }
}
