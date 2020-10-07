package com.iflytek.manager.api;

import com.iflytek.common.exception.UserNameNotFoundException;
import com.iflytek.common.model.Account;
import com.iflytek.common.model.Permission;
import com.iflytek.common.model.Role;
import com.iflytek.common.model.vo.ManagerUser;
import org.apache.shiro.authc.IncorrectCredentialsException;

import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
public interface AccountService {
    /**
     * @param account
     * @return
     * @author zgzhao
     * @describe 添加用户信息，不包括权限和角色信息，但是会生成默认角色信息
     */
    int addAccount(Account account);

    int updateAccount(Account account);

    Account findAccountByMwId(String mwId);

    Account findAccountByPhone(String phone);

    /**
     * @param username
     * @return 通过MwId 或者手机查找 用户
     */
    Account getAccount(String username) throws IncorrectCredentialsException, UserNameNotFoundException;

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 生成mwId
     */
    String generateMwId();

    /**
     * 该手机号是否已经 注册过了
     *
     * @param phone
     * @return
     */
    boolean isPhoneRegister(String phone);

    int getUserCount();

    /**
     * @param mwId roleId
     * @return
     * @author zgzhao
     * @describe 为某个用户添加角色
     */
    int addRole(String mwId, List<Integer> roleIdList);

    /**
     * @param mwId ==null 获取所有角色列表，否色获取某个用户的角色列表
     * @return
     * @author zgzhao
     * @describe 获取所有角色列表
     */
    List<Role> getRoleList(String mwId);

    /**
     * @param map mwId roleId
     * @return 存在返回true
     * @author zgzhao
     * @describe 判断某个用户是否拥有某个角色
     */
    boolean isExistRole(Map<String, Object> map);

    /**
     * @param mwId roleId
     * @return
     * @author zgzhao
     * @describe 移除某个用户角色
     */
    int removeRole(String mwId, List<Integer> roleIdList);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 移除用户所有的角色
     */
    int removeAllRole(String mwId);

    /**
     * @param mwId permissionId
     * @return
     * @author zgzhao
     * @describe 为某个用户添加权限
     */
    int addPermission(String mwId, List<Integer> permissionIdList);

    /**
     * @param mwId permissionId
     * @return
     * @author zgzhao
     * @describe 某个用户是否已经有了某个权限
     */
    boolean isExistPermission(String mwId, long permissionId);

    /**
     * @param mwId permissionId
     * @return
     * @author zgzhao
     * @describe 为某个用户移除权限
     */
    int removePermission(String mwId, List<Integer> permissionIdList);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 移除用户所有权限
     */
    int removeAllPermission(String mwId);

    /**
     * 获取用户列表包括登录次数
     *
     * @param startNum       必须
     * @param pageSize       必须
     * @param roleName       非必须
     * @param permissionName 非必须
     * @param mwId           非必须
     * @return
     */
    List<ManagerUser> getManagerUserList(int startNum,
                                         int pageSize,
                                         String roleName,
                                         String permissionName,
                                         String mwId, String alias);


    /**
     * 获取用户个数
     *
     * @param roleName       非必须
     * @param permissionName 非必须
     * @param mwId           非必须
     * @return
     */
    int getManagerUserNumber(String roleName,
                             String permissionName,
                             String mwId,
                             String alias);

    /**
     * @param
     * @return
     * @author zgzhao
     * @describe 获取权限列表
     */
    List<Permission> getPermissionList();
}
