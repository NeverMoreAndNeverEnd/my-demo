package com.soul;


import com.soul.job.MyJobFactory;
import com.soul.netty.NettyServer;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.soul.index")
@EnableJpaRepositories(basePackages = "com.soul.dao")
//@EnableScheduling
public class SuredarApplication {

	public static void main(String[] args) {
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件

		ApplicationContext context = SpringApplication.run(SuredarApplication.class, args);
		NettyServer server = context.getBean(NettyServer.class);
		server.run();

	}

	@Autowired
	private MyJobFactory myJobFactory;

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setJobFactory(myJobFactory);
		System.out.println("myJobFactory:"+myJobFactory);
		return schedulerFactoryBean;
	}
	@Bean
	public Scheduler scheduler() {
		return schedulerFactoryBean().getScheduler();
	}


}
