package com.kaishengit.tms.serviceimpl;

import com.kaishengit.tms.RolesService;
import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.AccountRolesMapper;
import com.kaishengit.tms.mapper.RolesMapper;
import com.kaishengit.tms.mapper.RolesPermissionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;



@Service
public class RolesServiceimpl implements RolesService {

    @Autowired
    private RolesMapper rolesMapper;

    @Autowired
    private RolesPermissionMapper rolesPermissionMapper;

    private static final Logger logger = LoggerFactory.getLogger(RolesServiceimpl.class);

    @Autowired
    private AccountRolesMapper accountRolesMapper;

    @Override
    public List<Roles> fandAll() {
        RolesExample rolesExample = new RolesExample();
        return rolesMapper.selectByExample(rolesExample);
    }


    /*
     *
     * @date 2018/4/14
     * @param   新增角色 Roles
     *          permissionId 给新增的角色添加那些权限
     *
     *          添加事务 联合主键
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void save(Roles roles, Integer[] permissionId) {
        if(roles != null){
            roles.setUpdateTime(new Date());
            rolesMapper.insertSelective(roles);
        }

            if( permissionId  !=null){
                //添加关联关系
                for(Integer pId : permissionId){
                    RolesPermissionKey rolesPermissionKey = new RolesPermissionKey();
                    rolesPermissionKey.setPermissionId(pId);
                    rolesPermissionKey.setRolesId(roles.getId());

                    rolesPermissionMapper.insertSelective(rolesPermissionKey);
                    logger.info("{},保存角色",roles);
                }
            }


    }


    /*
     *
     * @date 2018/4/15
     * @param  获取所有的角色人物
     * @return
     */
    @Override
    public List<Roles> findAllRoles() {
        RolesExample rolesExample = new RolesExample();
        return rolesMapper.selectByExample(rolesExample);
    }


    /*
     *  查询所有权限管理和角色关联关系的所有数据
     *
     * @date 2018/4/16
     * @param
     * @return
     */
    @Override
    public List<Roles> findAllRolesPermission() {

        return rolesMapper.findAllRolesPermission();
    }

    /*
     * 根据角色id 查询出 角色以及拥有的权限管理
     *
     * @date 2018/4/16
     * @param
     * @return
     */
    @Override
    public Roles findRolesPermission(Integer id) {

        return rolesMapper.findRolesPermission(id);
    }

    /*
     *  修改角色
     * @date 2018/4/16
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void update(Roles roles, Integer[] permissionId) {
        //保存角色

       rolesMapper.updateByPrimaryKeySelective(roles);

        logger.info("{},更新成功",roles);
        //删除角色和权限的关联表数据
        RolesPermissionExample rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andPermissionIdEqualTo(roles.getId());

        rolesPermissionMapper.deleteByExample(rolesPermissionExample);


        for(Integer pid : permissionId){
            RolesPermissionKey rolesPermissionKey = new RolesPermissionKey();
            rolesPermissionKey.setRolesId(roles.getId());
            rolesPermissionKey.setPermissionId(pid);

            rolesPermissionMapper.insertSelective(rolesPermissionKey);

        }

        logger.info("{},更新成功",permissionId);

    }

    /* 根据角色id删除角色
     *和角色和permission权限的关联
     * @date 2018/4/17
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(Integer id) throws ServiceException {
        //查看 角色有没人使用

        RolesPermissionExample rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andPermissionIdEqualTo(id);

        List<RolesPermissionKey> rolesPermissionKeyList =  rolesPermissionMapper.selectByExample(rolesPermissionExample);
        if(rolesPermissionKeyList != null && !rolesPermissionKeyList.isEmpty()){
                throw new ServiceException("该角色有人使用，删除失败");
        }

        //删除角色 和关联关系

       RolesPermissionExample rolesPermissionExample1 = new RolesPermissionExample();
        rolesPermissionExample1.createCriteria().andRolesIdEqualTo(id);

        rolesPermissionMapper.deleteByExample(rolesPermissionExample1);

        Roles roles = rolesMapper.selectByPrimaryKey(id);
        rolesMapper.deleteByPrimaryKey(id);

        logger.info("{},删除成功",roles);
    }


    /*
     * 当前账号用有的角色
     * @date 2018/4/18
     * @param
     * @return
     */
    @Override
    public List<Roles> findAccountRoles(Integer id) {
        return rolesMapper.findRolesByAccountIds(id);
    }




}
