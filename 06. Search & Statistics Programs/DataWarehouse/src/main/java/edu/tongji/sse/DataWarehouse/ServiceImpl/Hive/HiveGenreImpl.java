package edu.tongji.sse.DataWarehouse.ServiceImpl.Hive;

import edu.tongji.sse.DataWarehouse.DAL.Hive.HiveGenreMapper;
import edu.tongji.sse.DataWarehouse.Model.HiveModel.HiveGenre;
import edu.tongji.sse.DataWarehouse.Service.Hive.HiveGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HiveGenreImpl implements HiveGenreService {

    @Autowired
    private HiveGenreMapper mySQLGenreMapper;

    @Override
    public List<HiveGenre> getAllGenres(){
        return mySQLGenreMapper.getAll();
    }
}
