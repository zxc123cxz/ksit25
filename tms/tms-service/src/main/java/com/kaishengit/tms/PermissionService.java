package com.kaishengit.tms;

import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.exception.ServiceException;


import java.util.List;

public interface PermissionService {

    List<Permission> findAll();

    List<Permission> findtype(String permissionType);

    void PerSave(Permission permission);

    void delete(Integer id)throws ServiceException;

    //void update(Integer id)throws ServiceException;

    Permission findById(Integer id);

    void update(Permission permission);

    List<Permission> findAllPermission();
}
