package com.tw.config;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.tw.entity.Person;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Date;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties.bk")
public class DBConfig {

	private static final Logger logger = LoggerFactory.getLogger(DBConfig.class);

	@Autowired
	private Environment env;

	@Bean
	public HibernateTemplate hibernateTemplate() {
		return new HibernateTemplate(sessionFactory());
	}
	@Bean
	public SessionFactory sessionFactory() {
		return new LocalSessionFactoryBuilder(getDataSource())
		   .addAnnotatedClasses(Person.class)
		   .buildSessionFactory();
	}
	@Bean
	public DataSource getDataSource() {
		String aaa = env.getProperty("logging.file");


		logger.info("Hello Logger !" + aaa);
		logger.info("Hello Logger !" + new Date());
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/mytest");
	    dataSource.setUsername("user2");
	    dataSource.setPassword("user2");
	    return dataSource;
	}
	@Bean
	public HibernateTransactionManager hibTransMan(){
		return new HibernateTransactionManager(sessionFactory());
	}
}
