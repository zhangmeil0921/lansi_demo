//package config;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
///**
// * 菜单和按钮
// */
//@Configuration
//@MapperScan("demo.dao.mapper")
//public class DataSourceFunctionConfig {
//
//    @Bean(name = "masterDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
//    public DataSource functionDataSource() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "masterSqlSessionFactory")
//    public MybatisSqlSessionFactoryBean functionSqlSessionFactory(@Qualifier("masterDataSource") DataSource callCenterDataSource) throws Exception {
//        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
//        bean.setDataSource(callCenterDataSource);
//        return bean;
//    }
//}
