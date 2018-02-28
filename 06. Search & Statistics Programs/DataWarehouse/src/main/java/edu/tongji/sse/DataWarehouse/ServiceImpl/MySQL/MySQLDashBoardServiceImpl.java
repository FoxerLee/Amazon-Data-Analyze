package edu.tongji.sse.DataWarehouse.ServiceImpl.MySQL;

import edu.tongji.sse.DataWarehouse.DAL.MySQL.*;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLDashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MySQLDashBoardServiceImpl implements MySQLDashBoardService {

    @Autowired
    private MySQLActorMapper mySQLActorMapper;

    @Autowired
    private MySQLDirectorMapper mySQLDirectorMapper;

    @Autowired
    private MySQLMovieMapper mySQLMovieMapper;

    @Autowired
    private MySQLStudioMapper mySQLStudioMapper;

    @Autowired
    private MySQLBindingMapper mySQLBindingMapper;

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
