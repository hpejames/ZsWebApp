package com.james.zs.config.database;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@MapperScan(basePackages = DataSourceMasterConfig.PACKAGE,sqlSessionFactoryRef = "masterSqlSessionFactory")
public class DataSourceMasterConfig {

    //master dao 所在的包
    public static final String PACKAGE = "com.james.zs.dao.master";

    //mapper所在目录
    private static final String MAPPER_LOCATION = "classpath:mapper/master/*.xml";

    @Value("${master.datasource.driverClassName}")
    private String driverClassName;

    @Value("${master.datasource.url}")
    private String url;

    @Value("${master.datasource.username}")
    private String username;

    @Value("${master.datasource.password}")
    private String password;

    @Value("${master.datasource.maxWait}")
    private Long maxWait;

    @Value("${master.datasource.maxActive}")
    private Integer maxActive;

    @Value("${master.datasource.initialSize}")
    private Integer initialSize;

    @Value("${master.datasource.minIdle}")
    private Integer minIdle;

    @Value("${master.datasource.timeBetweenEvictionRunsMillis}")
    private Long timeBetweenEvictionRunsMillis;

    @Value("${master.datasource.testOnBorrow}")
    private Boolean testOnBorrow;

    @Value("${master.datasource.testWhileIdle}")
    private Boolean testWhileIdle;

    @Value("${master.datasource.validationQuery}")
    private String validationQuery;

    @Value("${master.datasource.filters}")
    private String filters;

    //初始化数据库连接
    @Bean(name="masterDataSource")
    @Primary
    public DataSource masterDataSource() throws SQLException {
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
    @Bean(name="masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterDataSourceTransactionManager() throws SQLException {
        return new DataSourceTransactionManager(masterDataSource());
    }

    //创建Session
    @Bean(name="masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource) throws Exception{
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(masterDataSource);
        //MapperLocations(Resource[] mapperLocations)
        Resource[] mapperLocations = new PathMatchingResourcePatternResolver().getResources(DataSourceMasterConfig.MAPPER_LOCATION);
        sqlSessionFactoryBean.setMapperLocations(mapperLocations);
        return sqlSessionFactoryBean.getObject();
    }
}
