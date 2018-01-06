package edu.tongji.sse.DataWarehouse.DAL.Hive;

import edu.tongji.sse.DataWarehouse.Model.HiveModel.HiveDirector;
import org.apache.ibatis.annotations.*;


@Mapper
public interface HiveDirectorMapper {

    @Select("select count(name) from director")
    Integer getDirectorNum();

    @Select("select movies from director where name = \'${name}\'")
    String getMoviesIdByName(@Param(value = "name") String name);

    @Select("select * from director where name = \'${name}\'")
    @Results({
            @Result(property = "name", column = "director.name"),
            @Result(property = "movies", column = "director.movies"),
            @Result(property = "count", column = "director.count"),
            @Result(property = "style", column = "director.style"),
            @Result(property = "actors", column = "director.actors"),
            @Result(property = "corporation", column = "director.corporation")
    })
    HiveDirector getDirectorStyleByName(@Param(value = "name") String name);
}
