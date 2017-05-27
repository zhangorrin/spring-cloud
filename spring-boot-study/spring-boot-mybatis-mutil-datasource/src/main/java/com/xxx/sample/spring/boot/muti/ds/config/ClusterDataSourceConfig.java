package com.xxx.sample.spring.boot.muti.ds.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author Orrin on 2017/5/27.
 */
@Configuration
@MapperScan(basePackages = ClusterDataSourceConfig.PACKAGE, sqlSessionTemplateRef = "clusterSqlSessionTemplate")
public class ClusterDataSourceConfig {
	// 精确到 cluster 目录，以便跟其他数据源隔离
	static final String PACKAGE = "com.xxx.sample.spring.boot.muti.ds.dao.cluster";
	static final String MAPPER_LOCATION = "classpath:mapper/cluster/*.xml";

	@Value("${cluster.datasource.url}")
	private String url;

	@Value("${cluster.datasource.username}")
	private String user;

	@Value("${cluster.datasource.password}")
	private String password;

	@Value("${cluster.datasource.driverClassName}")
	private String driverClass;

	@Bean(name = "clusterDataSource")
	public DataSource clusterDataSource() {
		/*DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		return dataSource;*/

		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
		mysqlXaDataSource.setUrl(url);
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXaDataSource.setPassword(password);
		mysqlXaDataSource.setUser(user);

		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlXaDataSource);
		xaDataSource.setUniqueResourceName("clusterDataSource");

		/*
		xaDataSource.setMinPoolSize(test2Config.getMinPoolSize());
		xaDataSource.setMaxPoolSize(test2Config.getMaxPoolSize());
		xaDataSource.setMaxLifetime(test2Config.getMaxLifetime());
		xaDataSource.setBorrowConnectionTimeout(test2Config.getBorrowConnectionTimeout());
		xaDataSource.setLoginTimeout(test2Config.getLoginTimeout());
		xaDataSource.setMaintenanceInterval(test2Config.getMaintenanceInterval());
		xaDataSource.setMaxIdleTime(test2Config.getMaxIdleTime());
		xaDataSource.setTestQuery(test2Config.getTestQuery());
		*/
		return xaDataSource;


	}

	@Bean(name = "clusterSqlSessionFactory")
	public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource clusterDataSource)
			throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(clusterDataSource);
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources(ClusterDataSourceConfig.MAPPER_LOCATION));
		return sessionFactory.getObject();
	}

	@Bean(name = "clusterSqlSessionTemplate")
	public SqlSessionTemplate clusterSqlSessionTemplate(
			@Qualifier("clusterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
