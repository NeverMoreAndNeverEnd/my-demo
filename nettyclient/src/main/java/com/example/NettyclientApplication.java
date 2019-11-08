package com.example;

import com.soul.netty.NettyClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.soul.netty"})
public class NettyclientApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(NettyclientApplication.class, args);
		NettyClient nettyClient = context.getBean(NettyClient.class);
		EventLoopGroup work = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		nettyClient.doConnect(bootstrap,work);
		System.out.println("hello world");
		System.out.println("test2");

	}

}
