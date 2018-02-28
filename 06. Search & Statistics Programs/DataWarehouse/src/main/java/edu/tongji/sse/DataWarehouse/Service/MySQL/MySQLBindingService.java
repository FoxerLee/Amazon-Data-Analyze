package edu.tongji.sse.DataWarehouse.Service.MySQL;

import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Binding;

import java.util.List;

public interface MySQLBindingService {

    List<Binding> getSummary();
}
