package com.kaishengit.tms;


import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.Roles;
import com.kaishengit.tms.exception.ServiceException;


import java.util.List;

public interface RolesService {
    List<Roles> fandAll();

    void save(Roles roles,Integer[] permissionId);



    List<Roles> findAllRoles();

    List<Roles> findAllRolesPermission();

    Roles findRolesPermission(Integer id);

    void update(Roles roles, Integer[] permissionId);

    void delete(Integer id)throws ServiceException;

    List<Roles> findAccountRoles(Integer id);


}
