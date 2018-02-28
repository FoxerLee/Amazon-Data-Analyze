package edu.tongji.sse.DataWarehouse.DAL.MySQL;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MySQLStarringMapper {

    @Select("select movies from starring where name = \'${name}\'")
    String getMoviesByName(@Param(value = "name") String name);

}
