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
    private ActorMapper actorMapper;

    @Autowired
    private DirectorMapper directorMapper;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private StudioMapper studioMapper;

    @Autowired
    private BindingMapper bindingMapper;

    @Override
    public List<Integer> getSummaryNum(){
        List<Integer> result = new ArrayList<>();
        result.add(movieMapper.getMovieNum());
        result.add(actorMapper.getActorNum());
        result.add(directorMapper.getDirectorNum());
        result.add(studioMapper.getStudioNumber());
        result.add(bindingMapper.getBindingNum());
        return result;
    }
}
