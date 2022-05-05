package com.jgz.gateway.load;

import com.jgz.gateway.manage.service.ReloadService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class ReloadAspect {

    @Resource
    private ReloadService reloadService;


    @Pointcut("@annotation(com.jgz.gateway.load.ReLoad)")
    public void annotationPointcut() {
    }

    @After("annotationPointcut()")
    public void commit() {
        reloadService.refreshRoute();
    }

}
