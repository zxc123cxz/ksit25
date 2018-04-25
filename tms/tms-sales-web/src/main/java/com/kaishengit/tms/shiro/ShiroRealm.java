package com.kaishengit.tms.shiro;

import com.kaishengit.tms.Accountservice;
import com.kaishengit.tms.RolesService;
import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountLoginLog;
import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.Roles;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class ShiroRealm extends AuthorizingRealm{

    @Autowired
    private Accountservice accountservice;

    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private RolesService rolesService;

    /*
     * 判断角色和权限
     * @date 2018/4/17
     * @param 刘文龙
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*//获取当前的登陆的对象
       Account account = (Account) principalCollection.getPrimaryPrincipal();
       //获取当前登陆对象拥有的角色
        List<Roles> rolesList = rolesService.findAccountRoles(account.getId());
        //获取当前对象拥有的权限
          List<Permission> permissionList = new ArrayList<>();
          for(Roles roles : rolesList){
              //根据角色id查询出拥有的所有权限
              Roles rolesPermission = rolesService.findRolesPermission(roles.getId());
              permissionList.addAll(rolesPermission.getPermissionList());
          }



          //封装到一个有序的集合中set
          Set<String>  Accountroles  = new HashSet<>();
          for(Roles roles : rolesList){
                Accountroles.add(roles.getRolesCode());
          }

          Set<String> AccountPermission = new HashSet<>();
          for(Permission permission : permissionList){
              AccountPermission.add(permission.getPermissionCode());
          }
        //相当于把值封装到SimpleAuthentizationInfo
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
          //当前用户拥有的角色
          simpleAuthorizationInfo.setRoles(Accountroles);
          //当前用户拥有的权限
          simpleAuthorizationInfo.setStringPermissions(AccountPermission);
*/
        return null;
    }

    /*
     *  判断登陆
     * @date 2018/4/17
     * @param 刘文龙
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

       String accountMobile =  usernamePasswordToken.getUsername();
       if(accountMobile != null){
        Account account = accountservice.findByMobile(accountMobile);

            if(account != null){
                //判断状态
                if(account.getAccountState().equals(Account.STATE_NORMAL)){
                    //getHost 是获取Ip地址
                    logger.info("{},登陆成功",account,usernamePasswordToken.getHost());
                    //把日志添加到Account_login中
                    AccountLoginLog accountLoginLog = new AccountLoginLog();
                    accountLoginLog.setAccountId(account.getId());
                    accountLoginLog.setLoginIp(usernamePasswordToken.getHost());
                    accountLoginLog.setLoginTime(new Date());
                    accountservice.saveAccountLoginLog(accountLoginLog);
                    return new SimpleAuthenticationInfo(account,account.getAccountPassword(),getName());
                }else {
                    throw new LockedAccountException("账号已被锁定或禁用" + account.getAccountState());
                }

            }else{
                throw new UnknownAccountException("找不到账号"+accountMobile);
            }


       }
        return null;
      /*  UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userMobile = usernamePasswordToken.getUsername();
        if(userMobile != null) {
            Account account = accountservice.findByMobile(userMobile);
            if(account == null) {
                throw new UnknownAccountException("找不到该账号:" + userMobile);
            } else {
                if(Account.STATE_NORMAL.equals(account.getAccountState())) {
                    logger.info("{} 登录成功: {}",account,usernamePasswordToken.getHost());

                    //保存登录日志
                    AccountLoginLog accountLoginLog = new AccountLoginLog();
                    accountLoginLog.setLoginTime(new Date());
                    accountLoginLog.setLoginIp(usernamePasswordToken.getHost());
                    accountLoginLog.setAccountId(account.getId());
                    accountservice.saveAccountLoginLog(accountLoginLog);

                    return new SimpleAuthenticationInfo(account,account.getAccountPassword(),getName());
                } else {
                    throw new LockedAccountException("账号被禁用或锁定:" + account.getAccountState());
                }
            }
        }
        return null;
*/
    }
}
