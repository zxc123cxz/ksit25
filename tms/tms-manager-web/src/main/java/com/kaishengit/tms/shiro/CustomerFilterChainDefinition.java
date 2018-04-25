package com.kaishengit.tms.shiro;

import com.kaishengit.tms.PermissionService;
import com.kaishengit.tms.RolesService;
import com.kaishengit.tms.entity.Permission;
import org.apache.shiro.config.Ini;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.util.List;
import java.util.Map;

public class CustomerFilterChainDefinition{

    private String filterChainDefinitions;

    private AbstractShiroFilter shiroFilter;
    @Autowired
    private RolesService rolesService;

    private Logger logger = LoggerFactory.getLogger(CustomerFilterChainDefinition.class);

    @Autowired
    private PermissionService permissionService;
   public void setFilterChainDefinitions(String filterChainDefinitions){
       this.filterChainDefinitions = filterChainDefinitions;
  }

  public void setShiroFilter(AbstractShiroFilter shiroFilter){
       this.shiroFilter = shiroFilter;
  }


    //Spring 容器启动时调用 PostConstruct指的是 容器启动后启动
    @PostConstruct
    public synchronized void init(){

        logger.info("启。。。。动");
        getFilterChainManager().getFilterChains().clear();
        load();
        logger.info("结。。。。束");

    }

    public synchronized  void updateUrlPermission(){

       logger.info("。。..刷新url权限..。。");
       //清除原来的url权限
        getFilterChainManager().getFilterChains().clear();

        //加载现有的url权限
        load();

        logger.info("。。。刷新url结束。。。");

    }



    public synchronized  void load(){
       Ini ini = new Ini();
       ini.load(filterChainDefinitions);

        //从数据库中查找所有的权限对象
        List<Permission> permissionList = permissionService.findAllPermission();
        Ini.Section  section = ini.get(Ini.DEFAULT_SECTION_NAME);

        //把url和code拼装进去，形成一个键值对 section（相当于一个map集合）
        for(Permission permission :permissionList){
            section.put(permission.getUrl(),"perms["+permission.getPermissionCode()+"]");
        }

        //  /**代表的是被认证，或者被记住状态都可以登陆
        // user代表认证或者记住
        section.put("/**","user");

        //url和权限的关系设置到shiroFilter中
        DefaultFilterChainManager defaultFilterChainManager = getFilterChainManager();
        for(Map.Entry<String,String> entry : section.entrySet()){
                defaultFilterChainManager.createChain(entry.getKey(),entry.getValue());
        }


    }


    public DefaultFilterChainManager getFilterChainManager() {
        PathMatchingFilterChainResolver pathMatchingFilterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
        DefaultFilterChainManager defaultFilterChainManager = (DefaultFilterChainManager) pathMatchingFilterChainResolver.getFilterChainManager();

        return defaultFilterChainManager;
    }
}
