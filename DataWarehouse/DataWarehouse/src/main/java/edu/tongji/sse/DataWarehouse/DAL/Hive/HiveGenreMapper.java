package edu.tongji.sse.DataWarehouse.DAL.Hive;

import edu.tongji.sse.DataWarehouse.Model.Genre;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HiveGenreMapper {

    @Select("select * from genre")
    List<Genre> getAll();

}
