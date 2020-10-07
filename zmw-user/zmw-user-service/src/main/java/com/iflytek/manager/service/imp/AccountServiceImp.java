package com.iflytek.manager.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.iflytek.common.constant.UserConstant;
import com.iflytek.common.enumeration.user.UserStatus;
import com.iflytek.common.exception.UserNameNotFoundException;
import com.iflytek.common.model.Account;
import com.iflytek.common.model.Permission;
import com.iflytek.common.model.Role;
import com.iflytek.common.model.vo.ManagerUser;
import com.iflytek.manager.api.AccountService;
import com.iflytek.manager.dao.AccountDao;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author AZhao
 */
@Service
public class AccountServiceImp implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public int addAccount(Account account) {
        account.setMwId(generateMwId());
        account.setStatus(UserStatus.NOEMAL.getValue());
        return accountDao.insert(account);
    }

    @Override
    public int updateAccount(Account account) {
        return accountDao.updateById(account);
    }

    @Override
    public Account findAccountByMwId(String mwId) {
        return accountDao.findAccountByMwId(mwId);
    }

    @Override
    public Account findAccountByPhone(String phone) {
        return accountDao.findAccountByPhone(phone);
    }

    @Override
    public Account getAccount(String username) throws IncorrectCredentialsException, UserNameNotFoundException {
        if (Pattern.compile(UserConstant.MWID_REGEXP).matcher(username).matches()) {
            return findAccountByMwId(username);
        } else if (Pattern.compile(UserConstant.PHONE_REGEXP).matcher(username).matches()) {
            return findAccountByPhone(username);
        } else {
            throw new UserNameNotFoundException();
        }
    }

    @Override
    public String generateMwId() {
        int userNameSuffix = getUserCount() + 11011;
        return "mw" + userNameSuffix;
    }

    @Override
    public boolean isPhoneRegister(String phone) {
        return accountDao.findUserPhoneCount(phone) > 0;
    }

    @Override
    public int getUserCount() {
        return accountDao.getUserCount();
    }

    @Override
    public int addRole(String mwId, List<Integer> roleIdList) {
        return accountDao.addRole(mwId, roleIdList);
    }

    @Override
    public List<Role> getRoleList(String mwId) {
        if (mwId == null) {
            return accountDao.getAllRoleList();
        } else {
            return accountDao.getUserRoleList(mwId);
        }

    }

    @Override
    public boolean isExistRole(Map<String, Object> map) {
        return accountDao.isExistRole(map) > 0;
    }

    @Override
    public int removeRole(String mwId, List<Integer> roleIdList) {
        return accountDao.removeRole(mwId, roleIdList);
    }

    @Override
    public int removeAllRole(String mwId) {
        return accountDao.removeAllRole(mwId);
    }

    @Override
    public int addPermission(String mwId, List<Integer> permissionIdList) {
        return accountDao.addPermission(mwId, permissionIdList);
    }

    @Override
    public boolean isExistPermission(String mwId, long permissionId) {
        Map<String, Object> map = new HashMap<>();
        map.put("mwId", mwId);
        map.put("permissionId", permissionId);
        return accountDao.isExistPermission(map) > 0;
    }


    @Override
    public int removePermission(String mwId, List<Integer> permissionIdList) {
        return accountDao.removePermission(mwId, permissionIdList);
    }

    @Override
    public int removeAllPermission(String mwId) {
        return accountDao.removeAllPermission(mwId);
    }


    @Override
    public List<ManagerUser> getManagerUserList(int startNum, int pageSize, String roleName, String permissionName, String mwId, String alias) {
        return accountDao.getManagerUserList(startNum, pageSize, roleName, permissionName, mwId, alias);
    }

    @Override
    public int getManagerUserNumber(String roleName, String permissionName, String mwId, String alias) {
        return accountDao.getManagerUserNumber(roleName, permissionName, mwId, alias);
    }

    @Override
    public List<Permission> getPermissionList() {
        return accountDao.getPermissionList();
    }
}
