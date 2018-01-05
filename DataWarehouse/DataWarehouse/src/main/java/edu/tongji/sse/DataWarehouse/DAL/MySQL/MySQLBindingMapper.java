package edu.tongji.sse.DataWarehouse.DAL.MySQL;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MySQLBindingMapper {

    @Select("select count(*) from binding")
    Integer getBindingNum();
}
