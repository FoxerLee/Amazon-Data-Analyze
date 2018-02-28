package edu.tongji.sse.DataWarehouse.ServiceImpl.MySQL;

import edu.tongji.sse.DataWarehouse.DAL.MySQL.MySQLStudioMapper;
import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Studio;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLStudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MySQLStudioServiceImpl implements MySQLStudioService {

    @Autowired
    private MySQLStudioMapper mySQLStudioMapper;

    @Override
    public List<Studio> getSummary(){
        return mySQLStudioMapper.getSummary();
    }
}
