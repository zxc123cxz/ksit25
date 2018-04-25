package com.kaishengit.tms;


import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountLoginLog;
import com.kaishengit.tms.exception.ServiceException;


import java.util.List;
import java.util.Map;

/*
 *
 * @date 2018/4/12
 * @param  登陆业务逻辑
 *          获取电话号码accountMobile
 *          获取密码 accountPassword
 *          获取ip地址 requestIp
 *          如果获取失败则手动抛出异常 ServiceException
 * @return
 */
public interface Accountservice {
    Account login(String accountMobile, String password, String requestIp)throws ServiceException;


    List<Account> findAllAccountRoles();

    List<Account> findParamType(Map<String, Object> map);

    void save(Account account, Integer[] rolesIds);

    void update(Account account, Integer[] rolesIds);

    void delete(Integer id) throws ServiceException;

    Account findByMobile(String userMobile);

    void saveAccountLoginLog(AccountLoginLog accountLoginLog);

   Account findByIdAccount(Integer id);
}
