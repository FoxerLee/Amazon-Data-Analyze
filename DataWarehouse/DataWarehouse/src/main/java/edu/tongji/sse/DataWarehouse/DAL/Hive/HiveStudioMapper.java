package edu.tongji.sse.DataWarehouse.DAL.Hive;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HiveStudioMapper {

    @Select("select count(*) from studio")
    Integer getStudioNumber();
}
