package com.myself.test.common.db;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 类名称：SystemDBConfigManager<br>
 * 类描述：<br>
 * 创建时间：2018年12月27日<br>
 *
 * @author maopanpan
 * @version 1.0.0
 */
@Configuration
@MapperScan(basePackages = "com.myself.test.mapper.system", sqlSessionTemplateRef = "systemSqlSessionTemplate")
public class SystemDBConfigManager {

    // 配置数据源
    @Primary
    @Bean(name = "systemDataSource")
    public DataSource testDataSource(SystemDBConfig dbConfig) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(dbConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(dbConfig.getPassword());
        mysqlXaDataSource.setUser(dbConfig.getUsername());

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName(dbConfig.getName());
        xaDataSource.setXaProperties(this.build(dbConfig));
        return xaDataSource;
    }

    @Primary
    @Bean(name = "systemSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("systemDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.myself.test.domain.system");//指定基包
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mappings/system/*.xml"));//指定xml文件位置
        return bean.getObject();
    }

    @Primary
    @Bean(name = "systemSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("systemSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    private Properties build(SystemDBConfig dbConfig) {
        Properties prop = new Properties();
        prop.put("url", dbConfig.getUrl());
        prop.put("username", dbConfig.getUsername());
        prop.put("password", dbConfig.getPassword());
        prop.put("driverClassName", "com.alibaba.druid.pool.xa.DruidXADataSource");
        prop.put("initialSize", dbConfig.getInitialSize());
        prop.put("maxActive", dbConfig.getMaxActive());
        prop.put("minIdle", dbConfig.getMinIdle());
        prop.put("maxWait", dbConfig.getMaxWait());
        prop.put("poolPreparedStatements", dbConfig.getPoolPreparedStatements());
        prop.put("maxPoolPreparedStatementPerConnectionSize", dbConfig.getMaxPoolPreparedStatementPerConnectionSize());
        prop.put("validationQuery", dbConfig.getValidationQuery());
        prop.put("validationQueryTimeout", dbConfig.getValidationQueryTimeout());
        prop.put("testOnBorrow", dbConfig.getTestOnBorrow());
        prop.put("testOnReturn", dbConfig.getTestOnReturn());
        prop.put("testWhileIdle", dbConfig.getTestWhileIdle());
        prop.put("timeBetweenEvictionRunsMillis", dbConfig.getTimeBetweenEvictionRunsMillis());
        prop.put("minEvictableIdleTimeMillis", dbConfig.getMinEvictableIdleTimeMillis());
        prop.put("filters", dbConfig.getFilters());
        return prop;
    }

}
