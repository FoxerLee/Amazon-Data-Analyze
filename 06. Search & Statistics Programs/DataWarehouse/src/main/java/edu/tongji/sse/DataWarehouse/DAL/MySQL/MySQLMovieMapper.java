package edu.tongji.sse.DataWarehouse.DAL.MySQL;

import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MySQLMovieMapper {

    @Select("select count(*) from movie")
    int getMovieNum();

    @Select("select * from movie where title = \'${name}\' limit 5")
    List<Movie> getMoviesByName(@Param(value = "name") String name);

    @Select("select * from movie where id = \'${movie_id}\'")
    Movie getMoviesById(@Param(value = "movie_id")String movie_id);

    @Select("select movies from genre where genre = \'${genre}\'")
    String[] getMoviesByGenre(@Param(value = "genre")String genre);

    @Select("select products from movie where id = \'${id}\'")
    String getMoviesStringById(@Param(value = "id")String movie_id);
}
