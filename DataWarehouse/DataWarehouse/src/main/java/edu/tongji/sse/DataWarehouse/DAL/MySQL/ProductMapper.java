package edu.tongji.sse.DataWarehouse.DAL.MySQL;

import edu.tongji.sse.DataWarehouse.Model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

//    @Select("select id, price, publication_date, sales_rank, languages, studios " +
//            "from product where movie_id = \'${id}\'")
//    List<Product> getProductsById(@Param(value = "id")String id);

}
