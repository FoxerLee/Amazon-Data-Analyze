package edu.tongji.sse.DataWarehouse.ServiceImpl.MySQL;

import edu.tongji.sse.DataWarehouse.DAL.MySQL.MySQLGenreMapper;
import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Genre;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MySQLGenreImpl implements MySQLGenreService {

    @Autowired
    private MySQLGenreMapper mySQLGenreMapper;

    @Override
    public List<Genre> getAllGenres(){
        return mySQLGenreMapper.getAll();
    }
}
