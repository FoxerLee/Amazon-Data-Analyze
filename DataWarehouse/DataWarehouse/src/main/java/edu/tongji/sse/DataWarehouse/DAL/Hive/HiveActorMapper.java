package edu.tongji.sse.DataWarehouse.DAL.Hive;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface HiveActorMapper {

    @Select("select count(name) from actor")
    Integer getActorNum();

    @Select("select movies from actor where name = \'${name}\'")
    String getMovies(@Param(value = "name") String name);

}
