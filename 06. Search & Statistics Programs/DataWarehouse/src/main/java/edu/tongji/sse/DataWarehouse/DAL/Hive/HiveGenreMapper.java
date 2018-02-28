package edu.tongji.sse.DataWarehouse.DAL.Hive;

import edu.tongji.sse.DataWarehouse.Model.HiveModel.HiveGenre;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HiveGenreMapper {

    @Select("select * from genre")
    @Results({
            @Result(property = "genre", column = "genre.genre"),
            @Result(property = "count", column = "genre.count"),
            @Result(property = "movies", column = "genre.movies"),
    })
    List<HiveGenre> getAll();

}
