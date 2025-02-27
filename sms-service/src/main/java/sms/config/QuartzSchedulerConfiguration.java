package sms.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.annotation.Id;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class QuartzSchedulerConfiguration {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public SpringBeanJobFactory jdbcFactory() {
        ContextAwareSpringBeanJobFactory jobFactory = new ContextAwareSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    @Primary
    public SchedulerFactoryBean scheduler(DataSource dataSource, PlatformTransactionManager transactionManager) {

        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setSchedulerName("scheduler");
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));
        schedulerFactory.setDataSource(dataSource);
        schedulerFactory.setTransactionManager(transactionManager);

        schedulerFactory.setJobFactory(jdbcFactory());

        return schedulerFactory;
    }

}
