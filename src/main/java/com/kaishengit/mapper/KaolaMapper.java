package com.kaishengit.mapper;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Kaola;

import java.util.List;
import java.util.Map;

public interface KaolaMapper {

    Kaola findById(Integer id);

    List<Kaola> findAll();

    void Delete(Integer id);

    void save(Kaola kaola);

    void update(Kaola kaola);

    List<Kaola> findTypeAll(Map<String,Object> map);
}
