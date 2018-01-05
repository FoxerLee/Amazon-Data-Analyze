package edu.tongji.sse.DataWarehouse.DAL.MySQL;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface DirectorMapper {

    @Select("select count(name) from director")
    Integer getDirectorNum();

    @Select("select movies from director where name = \'${name}\'")
    String getMoviesIdByName(@Param(value = "name")String name);
}
