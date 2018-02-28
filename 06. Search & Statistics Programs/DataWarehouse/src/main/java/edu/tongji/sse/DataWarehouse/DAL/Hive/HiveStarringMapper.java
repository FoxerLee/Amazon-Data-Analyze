package edu.tongji.sse.DataWarehouse.DAL.Hive;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HiveStarringMapper {

    @Select("select movies from starring where name = \'${name}\'")
    String getMoviesByName(@Param(value = "name") String name);

}
