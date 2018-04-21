package com.kaishengit.tms.serviceimpl;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.kaishengit.tms.PermissionService;
import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.PermissionExample;
import com.kaishengit.tms.entity.RolesPermissionExample;
import com.kaishengit.tms.entity.RolesPermissionKey;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.PermissionMapper;
import com.kaishengit.tms.mapper.RolesPermissionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PermissionServiceimpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolesPermissionMapper rolesPermissionMapper;


    private static final Logger logger = LoggerFactory.getLogger(PermissionServiceimpl.class);

    /*
     *
     * @date 2018/4/13
     * @param   查询所有permission的所有数据
     *
     * @return
     */
    @Override
    public List<Permission> findAll() {
        PermissionExample permissionExample = new PermissionExample();
        List<Permission>  permissionList =  permissionMapper.selectByExample(permissionExample);
        List<Permission> resultList = new ArrayList<>();
        treeList(permissionList,resultList,0);
        return resultList;
    }

    /*
     * 递归过滤(塞选)
     * @date 2018/4/13
     *
     * @param   permissionList :  查询出来的所有permission数据
     * @return  resultList   转换结束的集合
     */
    private void treeList(List<Permission> permissionList, List<Permission> resultList, int parentId) {
        List<Permission> permissions = Lists.newArrayList(Collections2.filter(permissionList,permission ->permission.getParentId().equals(parentId)));
        for(Permission permission : permissions){
            resultList.add(permission);
            treeList(permissionList,resultList,permission.getId());
        }
    }

    /*
     *
     * @date 2018/4/13
     * @param   查询所有是菜单类型的数据
     *          MENU 菜单
     * @return
     */
    @Override
    public List<Permission> findtype(String permissionType) {
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andPermissionTypeEqualTo(permissionType);
        return permissionMapper.selectByExample(permissionExample);
    }


    /*
     *
     * @date 2018/4/13
     * @param   保存permission( 权限管理)
     * @return
     */
    @Override
    public void PerSave(Permission permission) {
        permission.setCreateTime(new Date());
        permissionMapper.insertSelective(permission);
        logger.info("{},添加权限",permission);
    }


    /* 根据Id删除permission权限管理
     *
     * @date 2018/4/15
     * @param
     * @return
     */
    @Override
    public void delete(Integer id) throws ServiceException {
        //查询有没有角色在使用
        RolesPermissionExample  rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andPermissionIdEqualTo(id);

        List<RolesPermissionKey> rolesPermissionKeyList = rolesPermissionMapper.selectByExample(rolesPermissionExample);
        if(rolesPermissionKeyList !=null &&  !rolesPermissionKeyList.isEmpty()){
            throw new ServiceException("该管理有角色使用不能删除");
        }

        //查询有没有子节点
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andParentIdEqualTo(id);

        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
        if(permissionList !=null && !permissionList.isEmpty()){
            throw new ServiceException("该权限有字节点");
        }

        //如果没有被使用可以删除
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        permissionMapper.deleteByPrimaryKey(id);
        logger.info("{},删除权限",permission);
    }


    /*
     *  根据Id
     * @date 2018/4/15
     * @param
     * @return
     */
    @Override
    public Permission findById(Integer id) {
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        return permission;
    }

    /*
     *  根据id修改 查出 permission 的数据
     * @date 2018/4/15
     * @param
     * @return
     */

    @Override
    public void update(Permission permission) {
        permission.setCreateTime(new Date());
        permission.setUrl(permission.getUrl());
        permissionMapper.updateByPrimaryKeySelective(permission);
       logger.info("{},修改权限",permission);
    }

    @Override
    public List<Permission> findAllPermission() {
        PermissionExample permissionExample = new PermissionExample();

        return permissionMapper.selectByExample(permissionExample);
    }


}
