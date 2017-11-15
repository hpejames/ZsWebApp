package com.james.zs.config.database;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 业务异常
 *
 * @author james.gao
 * @version v1.0
 * @description TODO
 * @create date 13:40 2017/11/10
 * @modified by james.gao
 * @modify date 13:40 2017/11/10
 */
@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = DataSourceClusterConfig.PACKAGE,sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class DataSourceClusterConfig {

    //cluster dao 所在的包
    public static final String PACKAGE = "com.james.zs.dao.cluster.";

    //mapper所在目录
    private static final String MAPPER_LOCATION = "classpath:mapper/cluster/*.xml";

    @Value("${cluster.datasource.driverClassName}")
    private String driverClassName;

    @Value("${cluster.datasource.url}")
    private String url;

    @Value("${cluster.datasource.username}")
    private String username;

    @Value("${cluster.datasource.password}")
    private String password;

    @Value("${cluster.datasource.maxWait}")
    private Long maxWait;

    @Value("${cluster.datasource.maxActive}")
    private Integer maxActive;

    @Value("${cluster.datasource.initialSize}")
    private Integer initialSize;

    @Value("${cluster.datasource.minIdle}")
    private Integer minIdle;

    @Value("${cluster.datasource.timeBetweenEvictionRunsMillis}")
    private Long timeBetweenEvictionRunsMillis;

    @Value("${cluster.datasource.testOnBorrow}")
    private Boolean testOnBorrow;

    @Value("${cluster.datasource.testWhileIdle}")
    private Boolean testWhileIdle;

    @Value("${cluster.datasource.validationQuery}")
    private String validationQuery;

    @Value("${cluster.datasource.filters}")
    private String filters;

    //初始化数据库连接
    @Bean(name="clusterDataSource")
    public DataSource clusterDataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxWait(maxWait);
        dataSource.setMaxActive(maxActive);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setFilters(filters);
        return dataSource;
    }

    //数据源事务管理器
    @Bean(name="clusterTransactionManager")
    public DataSourceTransactionManager clusterDataSourceTransactionManager() throws SQLException {
        return new DataSourceTransactionManager(clusterDataSource());
    }

    //创建Session
    @Bean(name="clusterSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource clusterDataSource) throws Exception{
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(clusterDataSource);
        //MapperLocations(Resource[] mapperLocations)
        Resource[] mapperLocations = new PathMatchingResourcePatternResolver().getResources(DataSourceClusterConfig.MAPPER_LOCATION);
        sqlSessionFactoryBean.setMapperLocations(mapperLocations);
        return sqlSessionFactoryBean.getObject();
    }
}
