package com.shiyan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @descript
 *
 * Eureka服务器没有后端存储，但是注册中心中的服务实例都必须发送心跳来保持注册的最新(因此这可以在内存中完成)。客户端也有一个Eureka注册的内存缓存(因此他们不必为每个服务请求去注册中心
 * @author youyou
 * @date
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {
//        SpringApplication.run(EurekaServerApplication.class, args);
        new SpringApplicationBuilder(EurekaServerApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

}
