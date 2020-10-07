package com.iflytek.manager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iflytek.common.model.Account;
import com.iflytek.common.model.Permission;
import com.iflytek.common.model.Role;
import com.iflytek.common.model.vo.ManagerUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
@Repository
public interface AccountDao extends BaseMapper<Account> {
    Account findAccountByMwId(String mwId);

    Account findAccountByPhone(String phone);

    int findUserPhoneCount(String phone);

    int getUserCount();

    List<ManagerUser> getManagerUserList(@Param("startNum") int startNum,
                                         @Param("pageSize") int pageSize,
                                         @Param("roleName") String roleName,
                                         @Param("permissionName") String permissionName,
                                         @Param("mwId") String mwId,
                                         @Param("alias") String alias);


    int getManagerUserNumber(@Param("roleName") String roleName,
                             @Param("permissionName") String permissionName,
                             @Param("mwId") String mwId,
                             @Param("alias") String alias);

    int addRole(@Param("mwId") String mwId, @Param("roleIdList") List<Integer> roleIdList);

    List<Role> getAllRoleList();


    List<Role> getUserRoleList(@Param("mwId") String mwId);

    int isExistRole(Map<String, Object> map);

    int removeRole(@Param("mwId") String mwId, @Param("roleIdList") List<Integer> roleIdList);

    int removeAllRole(String mwId);

    int addPermission(@Param("mwId") String mwId, @Param("permissionIdList") List<Integer> permissionIdList);

    int isExistPermission(Map<String, Object> map);

    int removePermission(@Param("mwId") String mwId, @Param("permissionIdList") List<Integer> permissionIdList);

    int removeAllPermission(String mwId);

    List<Permission> getPermissionList();
}
