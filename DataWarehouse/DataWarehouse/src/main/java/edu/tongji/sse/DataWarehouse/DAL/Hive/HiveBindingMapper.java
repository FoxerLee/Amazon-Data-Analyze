package edu.tongji.sse.DataWarehouse.DAL.Hive;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HiveBindingMapper {

    @Select("select count(*) from binding")
    Integer getBindingNum();
}
