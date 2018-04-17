package com.kaishengit.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Kaola;
import com.kaishengit.entity.KaolaType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/* 根据ID（主键） 查询商品
 * @param id 主键
 * @return  Kaola 商品
 */

public interface KaolaService {
   Kaola findById(Integer id);


   /*
    *
    * @date 2018/4/10
    * @param  pageNo 当前的页数
    * @return  根据当前的页数查询出Kaola的商品
    */
   PageInfo<Kaola> findAllPageNo(Integer pageNo);

     List<Kaola> findAll();

     /*
      *
      * @date 2018/4/11
      * @param   根据id删除商品
      * @return
      */
    void findIdDelete(Integer id);

    void save(Kaola kaola);

    List<KaolaType> findType();

    void  updade(Kaola kaola);

    PageInfo<Kaola> findAllPagedMAP(Integer pageNo, Map<String, Object> map);
}
