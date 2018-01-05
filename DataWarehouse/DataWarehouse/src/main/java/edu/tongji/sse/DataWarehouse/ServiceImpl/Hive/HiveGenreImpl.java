package edu.tongji.sse.DataWarehouse.ServiceImpl.Hive;

import edu.tongji.sse.DataWarehouse.DAL.Hive.HiveGenreMapper;
import edu.tongji.sse.DataWarehouse.DAL.MySQL.MySQLGenreMapper;
import edu.tongji.sse.DataWarehouse.Model.Genre;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLGenreService;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HiveGenreImpl implements MySQLGenreService {

    @Autowired
    private HiveGenreMapper mySQLGenreMapper;

    @Override
    public List<Genre> getAllGenres(){
        return mySQLGenreMapper.getAll();
    }
}
