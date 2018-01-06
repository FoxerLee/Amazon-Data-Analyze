package edu.tongji.sse.DataWarehouse.Service.MySQL;

public interface MySQLDiagramService {

    //choice = 1 表示年， choice = 2 表示月， choice = 3表示周
    Object getDagramByGenreAndTimeChoice(Integer choice, String genre);

}
