package edu.tongji.sse.DataWarehouse.ServiceImpl.MySQL;

import edu.tongji.sse.DataWarehouse.DAL.MySQL.MySQLLanguageMapper;
import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Language;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MySQLLanguageServiceImpl implements MySQLLanguageService {

    @Autowired
    private MySQLLanguageMapper mySQLLanguageMapper;

    @Override
    public List<Language> getSummary(){
        return mySQLLanguageMapper.getSummary();
    }
}
