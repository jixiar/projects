package com.shiyan;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.List;

/**
 * @descript
 * 当客户端向Eureka注册时，它会提供关于自身的元数据——如主机、端口、运行状况指示器URL、主页和其他细节。Eureka从属于某个服务的每个实例接收心跳消息。在一个可配置的时间表,如果心跳失败实例通常从注册表中删除。
 *
 * 一旦有了作为发现客户机的应用程序，就可以使用它从Eureka服务器发现服务实例。一种方法是使用本地com.netflix.discovery.EurekaClient(相对于Spring Cloud DiscoveryClient)，如下例所示:
 *
 * @author youyou
 * @date
 */
@SpringBootApplication
@RestController
@EnableCircuitBreaker
public class ConsumerMovieApplication implements ApplicationRunner, CommandLineRunner {

//    @Autowired
//    private EurekaClient eurekaClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
//        SpringApplication.run(ConsumerMovieApplication.class, args);
        new SpringApplicationBuilder(ConsumerMovieApplication.class).web(WebApplicationType.SERVLET).lazyInitialization(true).run(args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {
//        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("CONSUMER-MOVIE", false);
//        System.out.println("home page url========" + instanceInfo.getHomePageUrl());

        List<ServiceInstance> list = discoveryClient.getInstances("CONSUMER-MOVIE");
        if (list != null && list.size() > 0 ) {
            System.out.println("service url ======" + list.get(0).getUri());
        }
    }
//    @Bean
    public Customizer<HystrixCircuitBreakerFactory> defaultConfig() {
        return factory -> factory.configureDefault(id -> HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(id))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(4000)));
    }

    @Override
    public void run(String... args) throws Exception {

    }
}