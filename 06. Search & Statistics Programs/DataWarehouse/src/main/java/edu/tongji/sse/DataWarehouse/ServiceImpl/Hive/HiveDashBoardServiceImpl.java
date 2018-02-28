package edu.tongji.sse.DataWarehouse.ServiceImpl.Hive;

import edu.tongji.sse.DataWarehouse.DAL.Hive.*;
import edu.tongji.sse.DataWarehouse.Service.Hive.HiveDashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HiveDashBoardServiceImpl implements HiveDashBoardService {

    @Autowired
    private HiveActorMapper mySQLActorMapper;

    @Autowired
    private HiveDirectorMapper mySQLDirectorMapper;

    @Autowired
    private HiveMovieMapper mySQLMovieMapper;

    @Autowired
    private HiveStudioMapper mySQLStudioMapper;

    @Autowired
    private HiveBindingMapper mySQLBindingMapper;

    @Override
    public List<Integer> getSummaryNum(){
        List<Integer> result = new ArrayList<>();
        result.add(mySQLMovieMapper.getMovieNum());
        result.add(mySQLActorMapper.getActorNum());
        result.add(mySQLDirectorMapper.getDirectorNum());
        result.add(mySQLStudioMapper.getStudioNumber());
        result.add(mySQLBindingMapper.getBindingNum());
        return result;
    }
}
