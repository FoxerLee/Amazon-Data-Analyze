package edu.tongji.sse.DataWarehouse.DAL;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudioMapper {

    @Select("select count(*) from studio")
    Integer getStudioNumber();
}
