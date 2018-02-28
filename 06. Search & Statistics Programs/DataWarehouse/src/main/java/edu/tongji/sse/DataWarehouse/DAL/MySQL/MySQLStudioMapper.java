package edu.tongji.sse.DataWarehouse.DAL.MySQL;

import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Studio;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MySQLStudioMapper {

    @Select("select count(*) from studio")
    Integer getStudioNumber();

    @Select("select * from studio")
    List<Studio> getSummary();
}
