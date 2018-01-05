package edu.tongji.sse.DataWarehouse.Configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"edu.tongji.sse.DataWarehouse.DAL.MySQL"}, sqlSessionFactoryRef = "sqlSessionFactory1")
public class MybatisMySQLConfig {

    @Autowired
    @Qualifier("firstDataSource")
    private DataSource sql;

    @Bean
    public SqlSessionFactory sqlSessionFactory1() throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(sql);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate1() throws  Exception{
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory1()); //使用上面配置的Factory
        return template;
    }
}
