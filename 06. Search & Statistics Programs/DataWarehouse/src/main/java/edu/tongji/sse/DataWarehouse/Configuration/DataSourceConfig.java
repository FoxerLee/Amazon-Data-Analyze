package edu.tongji.sse.DataWarehouse.Configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
@ComponentScan(basePackages = "edu.tongji.sse.DataWarehouse")
public class DataSourceConfig {

    @Bean(name = "firstDataSource")
    //@Qualifier("firstDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.first")
    public DataSource firstDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondDataSource")
    //@Qualifier("secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource secondDataSource(){
        return DataSourceBuilder.create().build();
    }

}
