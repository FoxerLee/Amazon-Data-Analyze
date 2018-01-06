package edu.tongji.sse.DataWarehouse.DAL.Hive;

import edu.tongji.sse.DataWarehouse.Model.HiveModel.HiveMovie;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HiveMovieMapper {

    @Select("select count(*) from movie")
    int getMovieNum();

    @Select("select * from movie where title = \'${name}\'")
    @Results({
            @Result(property = "id", column = "movie.id"),
            @Result(property = "actors", column = "movie.actors"),
            @Result(property = "bindings", column = "movie.binding"),
            @Result(property = "directors", column = "movie.directors"),
            @Result(property = "genres", column = "movie.genres"),
            @Result(property = "languages", column = "movie.languages"),
            @Result(property = "starrings", column = "movie.starrings"),
            @Result(property = "studios", column = "movie.studios"),
            @Result(property = "title", column = "movie.title"),
            @Result(property = "date", column = "movie.r_date"),
            @Result(property = "products", column = "movie.products"),
            @Result(property = "year", column = "movie.year"),
            @Result(property = "month", column = "movie.month"),
            @Result(property = "week", column = "movie.week")
    })
    List<HiveMovie> getMoviesByName(@Param(value = "name") String name);

    @Select("select * from movie where id = \'${movie_id}\'")
    @Results({
            @Result(property = "id", column = "movie.id"),
            @Result(property = "actors", column = "movie.actors"),
            @Result(property = "bindings", column = "movie.binding"),
            @Result(property = "directors", column = "movie.directors"),
            @Result(property = "genres", column = "movie.genres"),
            @Result(property = "languages", column = "movie.languages"),
            @Result(property = "starrings", column = "movie.starrings"),
            @Result(property = "studios", column = "movie.studios"),
            @Result(property = "title", column = "movie.title"),
            @Result(property = "date", column = "movie.r_date"),
            @Result(property = "products", column = "movie.products"),
            @Result(property = "year", column = "movie.year"),
            @Result(property = "month", column = "movie.month"),
            @Result(property = "week", column = "movie.week")
    })
    HiveMovie getMoviesById(@Param(value = "movie_id") String movie_id);

    @Select("select movies from genre where genre = \'${genre}\'")
    String getMoviesByGenre(@Param(value = "genre") String genre);

    @Select("select products from movie where id = \'${id}\'")
    String getMoviesStringById(@Param(value = "id") String movie_id);
}
