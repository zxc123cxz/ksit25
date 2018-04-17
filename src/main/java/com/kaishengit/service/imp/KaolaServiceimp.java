package com.kaishengit.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Kaola;
import com.kaishengit.entity.KaolaType;
import com.kaishengit.mapper.KaolaMapper;
import com.kaishengit.mapper.KaolaTypeMapper;
import com.kaishengit.service.KaolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KaolaServiceimp implements KaolaService {

    @Autowired
    private KaolaMapper kaolaMapper;

    @Autowired
    private KaolaTypeMapper kaolaTypeMapper;

    /*
     * 根据主键Id查询Kaola商品
     * @date 2018/4/10
     * @param  id（主键）
     * @return  Kaola对象
     */
    @Override
    public Kaola findById(Integer id) {
        return kaolaMapper.findById(id);
    }

   /*
     *  根据当前页数 查询当前的商品
     * @date 2018/4/10
     * @param   pageNo当前页数 pageSize当前页数多少商品
     * @return  kaola
     */
    @Override
    public PageInfo<Kaola> findAllPageNo(Integer pageNo) {
        PageHelper.startPage(pageNo,15);
        List<Kaola> kaolaList = kaolaMapper.findAll();
        return new PageInfo<>(kaolaList);
    }


    /*
    * 查询所有
    *   kaola
    * */
    @Override
    public List<Kaola> findAll() {
        return kaolaMapper.findAll();
    }
    /*
    * 根据id删除
    * */
    @Override
    public void findIdDelete(Integer id) {
         kaolaMapper.Delete(id);
    }
    /*
     *
     * @date 2018/4/11
     * @param   新增
     * @return
     */
    @Override
    public void save(Kaola kaola) {
        kaola.setCommentNum(Kaola.COMMENTNUM);
        kaolaMapper.save(kaola);
    }

    @Override
    public List<KaolaType> findType() {
        return kaolaTypeMapper.findType();
    }

    @Override
    public void updade(Kaola kaola) {
        kaolaMapper.update(kaola);
    }

    @Override
    public PageInfo<Kaola> findAllPagedMAP(Integer pageNo, Map<String, Object> map) {

        PageHelper.startPage(pageNo,10);
        List<Kaola> kaolaList = kaolaMapper.findTypeAll(map);
        return new PageInfo<>(kaolaList);
    }




}
