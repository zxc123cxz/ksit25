package com.kaishengit.tms.serviceimpl;

import com.kaishengit.tms.Accountservice;
import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.AccountLoginLogMapper;
import com.kaishengit.tms.mapper.AccountMapper;
import com.kaishengit.tms.mapper.AccountRolesMapper;
import com.kaishengit.tms.mapper.RolesMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class AccountServiceimpl implements Accountservice {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountLoginLogMapper accountLoginLogMapper;

    @Autowired
    private AccountRolesMapper accountRolesMapper;

    @Autowired
    private RolesMapper rolesMapper;


    private Logger logger = LoggerFactory.getLogger(AccountServiceimpl.class);


    /*
     *
     * @date 2018/4/12
     * @param  登陆业务逻辑
     *          获取电话号码accountMobile
     *          获取密码 accountPassword
     *          获取ip地址 requestIp
     *          如果获取失败则手动抛出异常 ServiceException
     *
     *
     *          如果成功：登陆的日志以及ip地址添加到mysql数据库中
     *          添加系统登陆日志
     * @return
     */
    @Override
    public Account login(String accountMobile, String password, String requestIp) throws ServiceException {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andAccountMobileEqualTo(accountMobile);

        List<Account> accountList = accountMapper.selectByExample(accountExample);
        Account account = null;
        if(accountList != null && !accountList.isEmpty()) {
            account = accountList.get(0);
            //匹配密码
            if(account.getAccountPassword().equals(password)) {
                //判断状态
                if(Account.STATE_NORMAL.equals(account.getAccountState())) {
                    //添加登录的日志
                    AccountLoginLog loginLog = new AccountLoginLog();
                    loginLog.setAccountId(account.getId());
                    loginLog.setLoginIp(requestIp);
                    loginLog.setLoginTime(new Date());
                    accountLoginLogMapper.insertSelective(loginLog);

                    logger.info("{} 登录系统",account);
                    return account;
                } else if(Account.STATE_LOCKING.equals(account.getAccountState())) {
                    throw new ServiceException("账号被锁定");
                } else {
                    throw new ServiceException("账号被禁用");
                }
            } else {
                throw new ServiceException("账号或密码错误2");
            }
        } else {
            throw new ServiceException("账号或密码错误1");
        }
    }

    /*
     *  查询关于用户和角色的所有信息
     *  多表联查
     * @date 2018/4/16
     * @param
     * @return
     */
    @Override
    public List<Account> findAllAccountRoles() {
        return accountMapper.findAllAccountRoles();
    }
    /*
     * 从页面获取模糊查询的属性
     * 然后从模糊查询
     * 多表联查
     * @date 2018/4/16
     * @param
     * @return
     */
    @Override
    public List<Account> findParamType(Map<String, Object> map) {

        return  accountMapper.findaccountParmType(map);
    }
    /*
     *  添加用户 以及关联的角色
     *
     *
     *  比较懒先不加
     *  DigestUils.md5Hex
     * @date 2018/4/16
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void save(Account account, Integer[] rolesIds) {
        //保存用户
        if(account != null){
            account.setOreateTime(new Date());
            account.setAccountState(Account.STATE_NORMAL);
            //设置默认密码 手机号后六位
            String password;
            if(account.getAccountMobile().length()<=6){
                password = account.getAccountMobile();
            }else {
                password = account.getAccountMobile().substring(6);
            }
            account.setAccountPassword(password);

            accountMapper.insertSelective(account);
            logger.info("{},添加成功",account);
        }


        //保存用户和角色的关联关系表
        if(rolesIds != null){
            for(Integer rid : rolesIds){
                AccountRolesKey accountRolesKey = new AccountRolesKey();
                System.out.println(account.getId());
                accountRolesKey.setRolesId(rid);
                accountRolesKey.setAccountId(account.getId());

                accountRolesMapper.insertSelective(accountRolesKey);
            }

            logger.info("{},添加成功",rolesIds);
        }


    }

    /*
     * 修改（更新）用户
     *
     * 先删除关联关系 ，在添加关联关系
     * @date 2018/4/16
     * @param
     * @return
     */
    @Override
    public void update(Account account, Integer[] rolesIds) {
        //更新用户
        account.setOreateTime(new Date());
        accountMapper.updateByPrimaryKeySelective(account);

        //更新账号角色关系表

        AccountRolesExample accountRolesExample = new AccountRolesExample();
        accountRolesExample.createCriteria().andAccountIdEqualTo(account.getId());

        accountRolesMapper.deleteByExample(accountRolesExample);

        logger.info("{},删除成功",rolesIds);

        if(rolesIds != null){
            //添加角色和用户的关系
            for(Integer pid : rolesIds){
                AccountRolesKey accountRolesKey = new AccountRolesKey();
                accountRolesKey.setAccountId(account.getId());
                accountRolesKey.setRolesId(pid);

                accountRolesMapper.insertSelective(accountRolesKey);
            }

        }

        logger.info("{}，修改成功",account);

    }

    /*
     * 根据id 删除用户account
     * 和角色相关的关系表
     * @date 2018/4/17
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(Integer id)throws ServiceException {
        // 删除用户account
        Account account = accountMapper.selectByPrimaryKey(id);
        accountMapper.deleteByPrimaryKey(id);

        logger.info("{},删除成功",account);

        //删除用户的角色关系
        AccountRolesExample accountRolesExample = new AccountRolesExample();
        accountRolesExample.createCriteria().andAccountIdEqualTo(id);
        accountRolesMapper.deleteByExample(accountRolesExample);

        logger.info("{},删除成功",account.getAccountName());
    }


    /*
     * 根据accountMobile查询
     * @date 2018/4/17
     * @param
     * @return
     */
    @Override
    public Account findByMobile(String userMobile) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andAccountMobileEqualTo(userMobile);

        List<Account>  accountList =  accountMapper.selectByExample(accountExample);
        Account account = new Account();
        if(accountList != null && !accountList.isEmpty()){
            account = accountList.get(0);
            return account;
        }
        return null;
    }


    /*
     *
     * @date 2018/4/17
     * @param
     * @return
     */
    @Override
    public void saveAccountLoginLog(AccountLoginLog accountLoginLog) {
          accountLoginLogMapper.insertSelective(accountLoginLog);
          logger.info("{},保存日志",accountLoginLog);
    }

    @Override
    public Account findByIdAccount(Integer id) {


        return accountMapper.selectByPrimaryKey(id);
    }


}
