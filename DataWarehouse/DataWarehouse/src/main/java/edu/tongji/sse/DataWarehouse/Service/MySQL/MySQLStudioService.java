package edu.tongji.sse.DataWarehouse.Service.MySQL;

import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Studio;

import java.util.ArrayList;
import java.util.List;

public interface MySQLStudioService {

    List<Studio> getSummary();
}
