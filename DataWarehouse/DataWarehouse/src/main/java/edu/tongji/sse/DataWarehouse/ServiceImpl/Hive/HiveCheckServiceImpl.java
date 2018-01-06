package edu.tongji.sse.DataWarehouse.ServiceImpl.Hive;

import edu.tongji.sse.DataWarehouse.DAL.Hive.HiveActorMapper;
import edu.tongji.sse.DataWarehouse.DAL.Hive.HiveDirectorMapper;
import edu.tongji.sse.DataWarehouse.DAL.Hive.HiveMovieMapper;
import edu.tongji.sse.DataWarehouse.DAL.Hive.HiveStarringMapper;
import edu.tongji.sse.DataWarehouse.Model.HiveModel.HiveDirector;
import edu.tongji.sse.DataWarehouse.Model.HiveModel.HiveMovie;
import edu.tongji.sse.DataWarehouse.Service.Hive.HiveCheckService;
import edu.tongji.sse.DataWarehouse.Service.Hive.HiveProductService;
import edu.tongji.sse.DataWarehouse.Service.Hive.HiveTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HiveCheckServiceImpl implements HiveCheckService {

    @Autowired
    private HiveMovieMapper mySQLMovieMapper;

    @Autowired
    private HiveDirectorMapper mySQLDirectorMapper;

    @Autowired
    private HiveActorMapper mySQLActorMapper;

    @Autowired
    private HiveStarringMapper mySQLStarringMapper;

    @Autowired
    private HiveTimeService mySQLTimeService;

    @Autowired
    private HiveProductService mySQLProductService;

    @Override
    public List<HiveMovie> checkMoviesByName(String name){
        return mySQLMovieMapper.getMoviesByName(name);
    }

    @Override
    public List<HiveMovie> checkMoviesByDirector(String name){
        String id = mySQLDirectorMapper.getMoviesIdByName(name);
        String[] movies_id = id.split(",");
        if (movies_id == null)
            return null;
        List<HiveMovie> result = new ArrayList<>();
        for(int i = 0; i < movies_id.length; i++){
            result.add(mySQLMovieMapper.getMoviesById(movies_id[i]));
        }
        return result;
    }

    @Override
    public List<HiveMovie> checkMoviesByGenre(String genre){
        String movies = mySQLMovieMapper.getMoviesByGenre(genre);
        System.out.println("movies:" + movies);
        if(movies == null)
            return new ArrayList<>();
        else{
            String[] ID = movies.split(",");
            List<HiveMovie> list = new ArrayList<>();
            for(int i = 0; i < ID.length; i++){
                HiveMovie hiveMovie = mySQLMovieMapper.getMoviesById(ID[i]);
                if(hiveMovie == null)
                    continue;
                list.add(hiveMovie);
            }
            return list;
        }
    }

    @Override
    public Object generateMovieAndProductsList(List<HiveMovie> hiveMovies){
        List<Object> result = new ArrayList<>();
        for(int i = 0; i < hiveMovies.size(); i++){
            HashMap<String, Object> data  = new HashMap<>();
            data.put("movie", hiveMovies.get(i));
            data.put("product", mySQLProductService.getProductByMovieId(hiveMovies.get(i).getId()));
            result.add(data);
        }
        return result;
    }

    @Override
    public List<HiveMovie> checkMoviesByActorName(String name){
        List<HiveMovie> hiveMovies = new ArrayList<>();
        String t_movies = mySQLActorMapper.getMovies(name);
        if(t_movies != null){
            String[] ID = t_movies.split(",");
            for(int i = 0; i < ID.length; i++){
                HiveMovie hiveMovie = mySQLMovieMapper.getMoviesById(ID[i]);
                if(hiveMovie == null)
                    continue;
                else{
                    hiveMovies.add(hiveMovie);
                }
            }
        }
        System.out.println("actor hiveMovies' number = " + hiveMovies.size());
        return hiveMovies;
    }

    @Override
    public List<HiveMovie> checkMoviesByStarringName(String name){
        List<HiveMovie> hiveMovies = new ArrayList<>();
        String t_movies = mySQLStarringMapper.getMoviesByName(name);
        if (t_movies != null){
            String[] ID = t_movies.split(",");
            for(int i = 0; i < ID.length; i++){
                HiveMovie hiveMovie = mySQLMovieMapper.getMoviesById(ID[i]);
                if(hiveMovie == null)
                    continue;
                else{
                    hiveMovies.add(hiveMovie);
                }
            }
        }
        System.out.println("starring hiveMovies' number = " + hiveMovies.size());
        return hiveMovies;
    }

    @Override
    public List<HiveMovie> checkMoviesByStarringOrActor(String actorName, String starringName){
        List<HiveMovie> result = new ArrayList<>();
        result.addAll(checkMoviesByStarringName(starringName));
        result.addAll(checkMoviesByActorName(actorName));
        return result;
    }

    public List<HiveMovie> JoinMovies(List<HiveMovie> movies1, List<HiveMovie> movies2){
        List<HiveMovie> result = new ArrayList<>();
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
    public List<HiveMovie> checkMoviesByMultipleOptions(String year, String director, String actor, String genre){
        List<HiveMovie> hiveMovies = new ArrayList<>();
        if(!actor.equals("")){
            List<HiveMovie> m = checkMoviesByActorName(actor);
            if(m == null)
                return new ArrayList<>();
            else{
                if(hiveMovies.size() == 0)
                    hiveMovies = m;
                else
                    hiveMovies = JoinMovies(hiveMovies, m);
            }
        }
        if(!director.equals("")){
            List<HiveMovie> m = checkMoviesByDirector(director);
            if(m == null)
                return new ArrayList<>();
            else{
                if(hiveMovies.size() == 0)
                    hiveMovies = m;
                else
                    hiveMovies = JoinMovies(hiveMovies, m);

            }
        }
        if(!year.equals("")){
            List<HiveMovie> m = mySQLTimeService.getMoviesByYear(year);
            if(m == null)
                return new ArrayList<>();
            else{
                if(hiveMovies.size() == 0)
                    hiveMovies = m;
                else
                    hiveMovies = JoinMovies(hiveMovies, m);
            }
        }
        if(!genre.equals("")){
            List<HiveMovie> m = checkMoviesByGenre(genre);
            if(m == null)
                return new ArrayList();
            else{
                if(hiveMovies.size() == 0)
                    hiveMovies = m;
                else
                    hiveMovies = JoinMovies(hiveMovies, m);
            }
        }
        return hiveMovies;
    }

    @Override
    public Object checkDirectorStyleByDirectorName(String name){
        HiveDirector hiveDirector = mySQLDirectorMapper.getDirectorStyleByName(name);
        if(hiveDirector == null)
            return new ArrayList<>();
        else{
            String DirectorName = hiveDirector.getName();
            String DirectorStyle = hiveDirector.getStyle();
            Map<String, String> list = new HashMap<>();
            String[] actors = hiveDirector.getActors().split(",");
            String[] co = hiveDirector.getCorporation().split(",");
            for(int i = 0; i < actors.length; i++)
                list.put(actors[i], co[i]);
            List<Object>list1 = new ArrayList<>();
            list1.add(DirectorName);
            list1.add(DirectorStyle);
            list1.add(list);
            return list1;
        }
    }
}
