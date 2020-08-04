package com.shiyan.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: jiahuiyang
 * @Date: Created in 14:48 2020/7/30
 */
@Lazy(false)
@Component
public class DemoService {

    public DemoService() {
        System.out.println("demoService is a lazy bean ...................");
    }
    public String operate() {
        return "demo service operate";
    }
}
