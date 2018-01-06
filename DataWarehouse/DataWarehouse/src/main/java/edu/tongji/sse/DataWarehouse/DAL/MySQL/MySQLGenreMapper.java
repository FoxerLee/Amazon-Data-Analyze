package edu.tongji.sse.DataWarehouse.DAL.MySQL;

import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Genre;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MySQLGenreMapper {

    @Select("select * from genre")
    List<Genre> getAll();

}
