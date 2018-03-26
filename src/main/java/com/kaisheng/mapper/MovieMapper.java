package com.kaisheng.mapper;

import com.kaisheng.entity.Movie;
import org.apache.ibatis.annotations.*;

import java.util.List;
@CacheNamespace
public interface MovieMapper {
    @Select("select * from movie where id = #{id}")
    Movie findById(int id);
    @Select("select * from movie")
    List<Movie> findAll();
    @Insert("insert into movie (title,rate,release_year,director) values (#{title},#{rate},#{releaseyear},#{director})")
    @Options(useGeneratedKeys = true ,keyProperty = "id")
    int save(Movie movie);
    @Update("update movie set title = #{title}, rate = #{rate} where id = #{id}")
    Movie update(Movie movie);

}
