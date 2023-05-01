package com.tommy.creditloan.configuration;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DBConfig {

	@Primary
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		DruidDataSource ds = DruidDataSourceBuilder.create().build();
		ds.setUrl("jdbc:mysql://localhost:3306/credit_loan?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT%2B8");
		ds.setConnectionProperties("druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000");
		ds.setUsername("root");
		ds.setPassword("root");
		ds.setMaxActive(50);
		ds.setMinIdle(10);
		ds.setInitialSize(5);
		ds.setMaxWait(60000);
		ds.setTimeBetweenEvictionRunsMillis(30000);
		ds.setMinEvictableIdleTimeMillis(30000);
		ds.setValidationQuery("SELECT 1");
		ds.setTestOnBorrow(false);
		ds.setTestOnReturn(false);
		ds.setTestWhileIdle(true);
		ds.setAsyncInit(true);
		ds.setRemoveAbandoned(true);
		ds.setRemoveAbandonedTimeout(60);
		ds.setPoolPreparedStatements(true);
		ds.setMaxOpenPreparedStatements(30);
		ds.setName("rcDataSource");

		return ds;
	}

}
