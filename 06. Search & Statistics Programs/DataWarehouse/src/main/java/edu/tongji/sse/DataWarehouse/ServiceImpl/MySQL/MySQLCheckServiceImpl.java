package edu.tongji.sse.DataWarehouse.ServiceImpl.MySQL;

import edu.tongji.sse.DataWarehouse.DAL.MySQL.*;
import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Director;
import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Movie;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLCheckService;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLProductService;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MySQLCheckServiceImpl implements MySQLCheckService {

    @Autowired
    private MySQLMovieMapper mySQLMovieMapper;

    @Autowired
    private MySQLDirectorMapper mySQLDirectorMapper;

    @Autowired
    private MySQLActorMapper mySQLActorMapper;

    @Autowired
    private MySQLStarringMapper mySQLStarringMapper;

    @Autowired
    private MySQLTimeService mySQLTimeService;

    @Autowired
    private MySQLProductService mySQLProductService;

    @Override
    public List<Movie> checkMoviesByName(String name){
        return mySQLMovieMapper.getMoviesByName(name);
    }

    @Override
    public List<Movie> checkMoviesByDirector(String name){
        String id = mySQLDirectorMapper.getMoviesIdByName(name);
        String[] movies_id = id.split(",");
        if (movies_id == null)
            return null;
        List<Movie> result = new ArrayList<>();
        for(int i = 0; i < movies_id.length; i++){
            result.add(mySQLMovieMapper.getMoviesById(movies_id[i]));
        }
        return result;
    }

    @Override
    public List<Movie> checkMoviesByGenre(String genre){
        String movies = mySQLMovieMapper.getMoviesByGenre(genre)[0];
        if(movies == null)
            return new ArrayList<>();
        else{
            String[] ID = movies.split(",");
            List<Movie> list = new ArrayList<>();
            for(int i = 0; i < ID.length; i++){
                Movie movie = mySQLMovieMapper.getMoviesById(ID[i]);
                list.add(movie);
            }
            return list;
        }
    }

    @Override
    public Object generateMovieAndProductsList(List<Movie> movies){
        List<Object> result = new ArrayList<>();
        for(int i = 0; i < movies.size(); i++){
            HashMap<String, Object> data  = new HashMap<>();
            data.put("movie", movies.get(i));
            data.put("product", mySQLProductService.getProductByMovieId(movies.get(i).getId()));
            result.add(data);
        }
        return result;
    }

    @Override
    public List<Movie> checkMoviesByActorName(String name){
        List<Movie> movies = new ArrayList<>();
        String t_movies = mySQLActorMapper.getMovies(name);
        if(t_movies != null){
            String[] ID = t_movies.split(",");
            for(int i = 0; i < ID.length; i++){
                Movie movie = mySQLMovieMapper.getMoviesById(ID[i]);
                if(movie == null)
                    continue;
                else{
                    movies.add(movie);
                }
            }
        }
        System.out.println("actor movies' number = " + movies.size());
        return movies;
    }

    @Override
    public List<Movie> checkMoviesByStarringName(String name){
        List<Movie> movies = new ArrayList<>();
        String t_movies = mySQLStarringMapper.getMoviesByName(name);
        if (t_movies != null){
            String[] ID = t_movies.split(",");
            for(int i = 0; i < ID.length; i++){
                Movie movie = mySQLMovieMapper.getMoviesById(ID[i]);
                if(movie == null)
                    continue;
                else{
                    movies.add(movie);
                }
            }
        }
        System.out.println("starring movies' number = " + movies.size());
        return movies;
    }

    @Override
    public List<Movie> checkMoviesByStarringOrActor(String actorName, String starringName){
        List<Movie> result = new ArrayList<>();
        result.addAll(checkMoviesByStarringName(starringName));
        result.addAll(checkMoviesByActorName(actorName));
        return result;
    }

    public List<Movie> JoinMovies(List<Movie> movies1, List<Movie> movies2){
        List<Movie> result = new ArrayList<>();
        for(int i = 0; i < movies1.size(); i++){
            for(int j = 0; j < movies2.size(); j++){
                if(movies1.get(i).getId().equals(movies2.get(j).getId())){
                    result.add(movies1.get(i));
                    continue;
                }
            }
        }
        return result;
    }

    @Override
    public List<Movie> checkMoviesByMultipleOptions(String year, String director, String actor, String genre){
        List<Movie> movies = new ArrayList<>();
        if(!actor.equals("")){
            List<Movie> m = checkMoviesByActorName(actor);
            if(m == null)
                return new ArrayList<>();
            else{
                if(movies.size() == 0)
                    movies = m;
                else
                    movies = JoinMovies(movies, m);
            }
        }
        if(!director.equals("")){
            List<Movie> m = checkMoviesByDirector(director);
            if(m == null)
                return new ArrayList<>();
            else{
                if(movies.size() == 0)
                    movies = m;
                else
                    movies = JoinMovies(movies, m);

            }
        }
        if(!year.equals("")){
            List<Movie> m = mySQLTimeService.getMoviesByYear(year);
            if(m == null)
                return new ArrayList<>();
            else{
                if(movies.size() == 0)
                    movies = m;
                else
                    movies = JoinMovies(movies, m);
            }
        }
        if(!genre.equals("")){
            List<Movie> m = checkMoviesByGenre(genre);
            if(m == null)
                return new ArrayList();
            else{
                if(movies.size() == 0)
                    movies = m;
                else
                    movies = JoinMovies(movies, m);
            }
        }
        return movies;
    }

    @Override
    public Object checkDirectorStyleByDirectorName(String name){
        Director director = mySQLDirectorMapper.getDirectorStyleByName(name);
        if(director == null)
            return new ArrayList<>();
        else{
            String DirectorName = director.getName();
            String DirectorStyle = director.getStyle();
            Map<String, Object> list = new HashMap<>();
            String[] actors = director.getActors().split(",");
            String[] co = director.getCorporation().split(",");
            List<Object> temp1 = new ArrayList<>();
            for(int i = 0; i < actors.length; i++){
                Map<String, Object> temp = new HashMap<>();
                temp.put("name", actors[i]);
                temp.put("count", co[i]);
                temp1.add(temp);
            }
            list.put("actors", temp1);
            List<Object>list1 = new ArrayList<>();
            list1.add(DirectorName);
            list1.add(DirectorStyle);
            list1.add(list);
            return list1;
        }
    }
}
