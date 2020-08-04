package com.shiyan.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shiyan.dto.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.naming.Name;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author: jiahuiyang
 * @Date: Created in 21:00 2020/7/29
 */
@Lazy
@Component
public class UserService {

    public UserService() {
        System.out.println("userService is a lazy..............");
    }


    @HystrixCommand(fallbackMethod = "defaultUser",commandProperties = {@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")})
    public User getUsers() {
        throw new RuntimeException("user service method getUsers breakdown");
    }

    public User defaultUser() {
        return new User(1L, "", "", 18, BigDecimal.ONE);
    }

}
