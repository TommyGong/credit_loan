package com.tommy.creditloan.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {

	@Autowired
	@Qualifier("dataSource")
	DataSource dataSource;

	@Primary
  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactory sqlSessionFactoryBean() {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    bean.setTypeAliasesPackage("com.tommy.creditloan.model");
    Properties properties = new Properties();
    properties.setProperty("dialect", "mysql");
    properties.setProperty("offsetAsPageNum", "true");
    properties.setProperty("rowBoundsWithCount", "true");
    properties.setProperty("pageSizeZero", "true");
    properties.setProperty("reasonable", "true");
    properties.setProperty("supportMethodsArguments", "true");

    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    try {
      bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
      bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
      return bean.getObject();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @Bean
  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

  @Override
  public PlatformTransactionManager annotationDrivenTransactionManager() {
    return new DataSourceTransactionManager(dataSource);
  }

}
