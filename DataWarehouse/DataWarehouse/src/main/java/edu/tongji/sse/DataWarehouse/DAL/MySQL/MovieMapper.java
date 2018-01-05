package edu.tongji.sse.DataWarehouse.DAL.MySQL;

import edu.tongji.sse.DataWarehouse.Model.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MovieMapper {

    @Select("select count(*) from movie")
    int getMovieNum();

    @Select("select * from movie where title = \'${name}\' limit 5")
    List<Movie> getMoviesByName(@Param(value = "name") String name);

    @Select("select * from movie where id = \'${movie_id}\'")
    Movie getMoviesById(@Param(value = "movie_id")String movie_id);

    @Select("select * from movie where genres = \'${genre}\'")
    List<Movie> getMoviesByGenre(@Param(value = "genre")String genre);

    @Select("select products from movie where id = \'${id}\'")
    String getMoviesStringById(@Param(value = "id")String movie_id);
}
