package edu.tongji.sse.DataWarehouse.Service.MySQL;

import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Language;

import java.util.List;

public interface MySQLLanguageService {

    List<Language> getSummary();
}
