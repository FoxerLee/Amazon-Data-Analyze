package edu.tongji.sse.DataWarehouse.ServiceImpl;

import edu.tongji.sse.DataWarehouse.DAL.*;
import edu.tongji.sse.DataWarehouse.Model.Movie;
import edu.tongji.sse.DataWarehouse.Model.Product;
import edu.tongji.sse.DataWarehouse.Service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CheckServiceImpl implements CheckService{

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private DirectorMapper directorMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ActorMapper actorMapper;

    @Autowired
    private StarringMapper starringMapper;

    @Override
    public List<Movie> checkMoviesByName(String name){
        return movieMapper.getMoviesByName(name);
    }

    @Override
    public List<Movie> checkMoviesByDirector(String name){
        String id = directorMapper.getMoviesIdByName(name);
        String[] movies_id = id.split(",");
        if (movies_id == null)
            return null;
        List<Movie> result = new ArrayList<>();
        for(int i = 0; i < movies_id.length; i++){
            result.add(movieMapper.getMoviesById(movies_id[i]));
        }
        return result;
    }

    @Override
    public List<Movie> checkMoviesByGenre(String genre){
        return movieMapper.getMoviesByGenre(genre);
    }

    @Override
    public List<Product> checkProductsByMovieId(String id){
        return productMapper.getProductsById(id);
    }

    @Override
    public Object generateMovieAndProductsList(List<Movie> movies){
        List<Object> result = new ArrayList<>();
        for(int i = 0; i < movies.size(); i++){
            HashMap<String, Object> data  = new HashMap<>();
            data.put("movie", movies.get(i));
            data.put("product", checkProductsByMovieId(movies.get(i).getId()));
            result.add(data);
        }
        return result;
    }

    @Override
    public List<Movie> checkMoviesByActorName(String name){
        String[] ID = actorMapper.getMovies(name).split(",");
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < ID.length; i++){
            movies.add(movieMapper.getMoviesById(ID[i]));
        }
        return movies;
    }

    @Override
    public List<Movie> checkMoviesByStarringName(String name){
        String[] temp = starringMapper.getMoviesByName(name).split(",");
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < temp.length; i++){
            movies.add(movieMapper.getMoviesById(temp[i]));
        }
        return movies;
    }

    @Override
    public List<Movie> checkMoviesByStarringOrActor(String actorName, String starringName){
        List<Movie> result = new ArrayList<>();
        result.addAll(checkMoviesByStarringName(starringName));
        result.addAll(checkMoviesByActorName(actorName));
        return result;
    }
}
