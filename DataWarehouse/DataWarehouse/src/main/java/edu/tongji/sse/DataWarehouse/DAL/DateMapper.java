package edu.tongji.sse.DataWarehouse.DAL;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DateMapper {

    @Select("select movies from date where year = ${year}")
    String getMoviesByYear(@Param(value = "year")String year);
}
