package edu.tongji.sse.DataWarehouse.ServiceImpl.MySQL;

import edu.tongji.sse.DataWarehouse.DAL.MySQL.GenreMapper;
import edu.tongji.sse.DataWarehouse.Model.Genre;
import edu.tongji.sse.DataWarehouse.Service.MySQL.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreImpl implements GenreService {

    @Autowired
    private GenreMapper genreMapper;

    @Override
    public List<Genre> getAllGenres(){
        return genreMapper.getAll();
    }
}
