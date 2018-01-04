package edu.tongji.sse.DataWarehouse.ServiceImpl;

import edu.tongji.sse.DataWarehouse.DAL.*;
import edu.tongji.sse.DataWarehouse.Service.DashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashBoardServiceImpl implements DashBoardService {

    @Autowired
    private ActorMapper actorMapper;

    @Autowired
    private DirectorMapper directorMapper;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private StudioMapper studioMapper;

    @Override
    public List<Integer> getSummaryNum(){
        List<Integer> result = new ArrayList<>();
        result.add(movieMapper.getMovieNum());
        result.add(actorMapper.getActorNum());
        result.add(directorMapper.getDirectorNum());
        result.add(studioMapper.getStudioNumber());
        return result;
    }
}
