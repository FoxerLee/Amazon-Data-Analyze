package edu.tongji.sse.DataWarehouse.DAL.MySQL;

import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Binding;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MySQLBindingMapper {

    @Select("select count(*) from binding")
    Integer getBindingNum();

    @Select("select * from binding")
    List<Binding> getSummary();
}
