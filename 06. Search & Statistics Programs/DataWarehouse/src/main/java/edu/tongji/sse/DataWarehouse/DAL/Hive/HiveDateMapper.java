package edu.tongji.sse.DataWarehouse.DAL.Hive;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface HiveDateMapper {

    @Select("select movies from date where year = ${year}")
    ArrayList<String> getMoviesByYear(@Param(value = "year") String year);
}
