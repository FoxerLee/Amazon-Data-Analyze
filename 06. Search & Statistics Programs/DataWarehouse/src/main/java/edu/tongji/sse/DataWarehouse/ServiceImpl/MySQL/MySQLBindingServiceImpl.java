package edu.tongji.sse.DataWarehouse.ServiceImpl.MySQL;

import edu.tongji.sse.DataWarehouse.DAL.MySQL.MySQLBindingMapper;
import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Binding;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLBindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MySQLBindingServiceImpl implements MySQLBindingService {

    @Autowired
    private MySQLBindingMapper mySQLBindingMapper;

    @Override
    public List<Binding> getSummary(){
        return mySQLBindingMapper.getSummary();
    }
}
