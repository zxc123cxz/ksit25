package com.kaishengit.tms.shiro;

import com.kaishengit.tms.Accountservice;
import com.kaishengit.tms.RolesService;

import com.kaishengit.tms.TicketAccountService;
import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.entity.Account;
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

    @Autowired
    private TicketAccountService ticketAccountService;

    /*
     * 判断角色和权限
     * @date 2018/4/17
     * @param 刘文龙
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    /**
     * 判断登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userMobile = usernamePasswordToken.getUsername();
        if(userMobile != null) {
            StoreAccount storeAccount = ticketAccountService.findByMobiles(userMobile);
            if(storeAccount == null) {
                throw new UnknownAccountException("找不到该账号:" + userMobile);
            } else {
                if(StoreAccount.NOR_MAL.equals(storeAccount.getStoreState())) {
                    logger.info("{} 登录成功: {}",storeAccount);

                    //查找售票点对象
                    TicketStore ticketStore = ticketAccountService.findById(storeAccount.getId());


                  /*  //保存登录日志
                    StoreLoginLog storeLoginLog = new StoreLoginLog();
                    storeLoginLog.setLoginIp(usernamePasswordToken.getHost());
                    storeLoginLog.setLoginTime(new Date());
                    storeLoginLog.setStoreAccountId(storeAccount.getId());

                    ticketStoreService.saveStoreAccountLoginLog(storeLoginLog);
*/
                    //将ticketStore放入Shiro
                    return new SimpleAuthenticationInfo(ticketStore,storeAccount.getStorePassword(),getName());
                } else {
                    throw new LockedAccountException("账号被禁用或锁定:" + storeAccount.getStoreState());
                }
            }
        }
        return null;

    }
}
