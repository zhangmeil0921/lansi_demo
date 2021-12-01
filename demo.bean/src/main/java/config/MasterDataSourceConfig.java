package demo.common.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 乐享主数据源配置
 * @author Sean
 * @createDate 2021-05-11
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"demo.dao.mapper"},sqlSessionFactoryRef ="masterSqlSessionFactory")
public class MasterDataSourceConfig {

    /**
     * 使用德鲁伊配置主数据源
     *
     * @return
     */
    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置主数据源的JdbcTemplate
     *
     * @param dataSource 主数据源
     * @return
     */
    @Bean(name = "masterJdbcTemplate")
    public JdbcTemplate masterJdbcTemplate(@Qualifier("masterDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 配置Mybatis-Plus的SqlSessionFactory
     *
     * @param dataSource 主数据源
     * @return
     */
    @Bean(name = "masterSqlSessionFactory")
    public MybatisSqlSessionFactoryBean masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        //设置数据源
        factoryBean.setDataSource(dataSource);
        //设置Mybatis Enum配置
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultEnumTypeHandler(MybatisEnumTypeHandler.class);
        //添加Mybatis 插件
        MybatisPlusInterceptor plusInterceptor = new MybatisPlusInterceptor();
        //添加分页插件
        plusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        //添加防止全表更新与删除插件
        plusInterceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        //添加乐观锁插件
        plusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        configuration.addInterceptor(plusInterceptor);
        factoryBean.setConfiguration(configuration);
        //设置Mybatis 全局配置
        GlobalConfig globalConfig = GlobalConfigUtils.defaults();
//        globalConfig.setMetaObjectHandler(new MetaObjectHandlerConfig());
        factoryBean.setGlobalConfig(globalConfig);
        return factoryBean;
    }

    /**
     * 配置主数据源事务管理
     *
     * @param dataSource 主数据源
     * @return
     */
    @Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager masterTransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}

