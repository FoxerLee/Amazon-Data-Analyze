package edu.tongji.sse.DataWarehouse.DAL.MySQL;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MySQLStudioMapper {

    @Select("select count(*) from studio")
    Integer getStudioNumber();
}
