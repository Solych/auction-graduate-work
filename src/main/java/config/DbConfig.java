package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "repository", entityManagerFactoryRef = "EntityManagerFactoryBean")
@EnableTransactionManagement
public class DbConfig {

    private static final String URL_DB =
            "jdbc:mysql://localhost:3306/auctionSchema?useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String MYSQL_DIALECT = "org.hibernate.dialect.MySQL57Dialect";
    private static final String PROP_VALIDATE = "hibernate.hbm2ddl.auto";
    private static final String VALUE_VALIDATE_SCHEMA = "validate";
    private static final String LAZY_LOAD = "hibernate.enable_lazy_load_no_trans";
    private static final String VALUE_LAZY_LOAD_PROP = "true";
    private static final String SHOW_SQL = "hibernate.show_sql";
    private static final String VALUE_SHOW_SQL = "true";

    private static final String MODEL_PACKAGE = "model";

    public DataSource restDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(URL_DB);
        dataSource.setUsername(USERNAME);
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    private Properties hibernateProps() {
        Properties properties = new Properties();
        properties.put(HIBERNATE_DIALECT, MYSQL_DIALECT);
        properties.put(PROP_VALIDATE, VALUE_VALIDATE_SCHEMA);
        properties.put(LAZY_LOAD, VALUE_LAZY_LOAD_PROP);
        properties.put(SHOW_SQL, VALUE_SHOW_SQL);
        return properties;
    }

    @Bean
    JpaTransactionManager transactionManager() throws SQLException {
        JpaTransactionManager jtm = new JpaTransactionManager();
        jtm.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return jtm;
    }

    @Bean(value = "EntityManagerFactoryBean")
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() throws SQLException {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(restDataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(hibernateProps());
        entityManagerFactoryBean.setPackagesToScan(MODEL_PACKAGE);
        return entityManagerFactoryBean;
    }
}

