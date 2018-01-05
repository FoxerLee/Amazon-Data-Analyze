package edu.tongji.sse.DataWarehouse.ServiceImpl.MySQL;

import edu.tongji.sse.DataWarehouse.DAL.MySQL.*;
import edu.tongji.sse.DataWarehouse.Model.Movie;
import edu.tongji.sse.DataWarehouse.Service.MySQL.CheckService;
import edu.tongji.sse.DataWarehouse.Service.MySQL.ProductService;
import edu.tongji.sse.DataWarehouse.Service.MySQL.TimeService;
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

    @Autowired
    private TimeService timeService;

    @Autowired
    private ProductService productService;

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

//    @Override
//    public List<Product> checkProductsByMovieId(String id){
//        List<Product> products = productMapper.getProductsById(id);
//        if(products == null)
//            return new ArrayList<>();
//        else
//            return productMapper.getProductsById(id);
//    }

    @Override
    public Object generateMovieAndProductsList(List<Movie> movies){
        List<Object> result = new ArrayList<>();
        for(int i = 0; i < movies.size(); i++){
            HashMap<String, Object> data  = new HashMap<>();
            data.put("movie", movies.get(i));
//            System.out.println(movies.get(i).getId());
//            checkProductsByMovieId(movies.get(i).getId());
//            if (products == null)
//                data.put("product", new ArrayList<>());
//            else
            data.put("product", productService.getProductByMovieId(movies.get(i).getId()));
//            Map<String, Object> temp_data = new HashMap<>();
//            List<Product> products = productService.getProductByMovieId(movies.get(i).getId());
//            if(products != null){
//                for(Integer j = 0; j < products.size(); j++){
//                    temp_data.put(j.toString(), products.get(i));
//                }
//            }
//            data.put("product", temp_data);
            result.add(data);
        }
        return result;
    }

    @Override
    public List<Movie> checkMoviesByActorName(String name){
        List<Movie> movies = new ArrayList<>();
        String t_movies = actorMapper.getMovies(name);
        if(t_movies != null){
            String[] ID = t_movies.split(",");
            for(int i = 0; i < ID.length; i++){
                Movie movie = movieMapper.getMoviesById(ID[i]);
                System.out.println(movie.getActors());
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
        String t_movies = starringMapper.getMoviesByName(name);
        if (t_movies != null){
            String[] ID = t_movies.split(",");
            for(int i = 0; i < ID.length; i++){
                Movie movie = movieMapper.getMoviesById(ID[i]);
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

    @Override
    public List<Movie> checkMoviesByMultipleOptions(String year, String director, String actor, String genre){
        List<Movie> movies = new ArrayList<>();
        if(!actor.equals("")){
            if(movies.size() == 0)
                movies = checkMoviesByActorName(actor);
            else
                movies.retainAll(checkMoviesByActorName(actor));
        }
        if(!director.equals("")){
            if(movies.size() == 0)
                movies = checkMoviesByDirector(director);
            else
                movies.retainAll(checkMoviesByDirector(director));
        }
        if(!year.equals("")){
            if(movies.size() == 0)
                movies = timeService.getMoviesByYear(year);
            else
                movies.retainAll(timeService.getMoviesByYear(year));
        }
        if(!genre.equals("")){
            if(movies.size() == 0)
                movies = checkMoviesByGenre(genre);
            else
                movies.retainAll(checkMoviesByGenre(genre));
        }
        return movies;
    }
}
