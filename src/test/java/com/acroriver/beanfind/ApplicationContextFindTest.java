package com.acroriver.beanfind;

import com.acroriver.server.ServerApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
public class ApplicationContextFindTest {

    @Autowired
    private ApplicationContext ac;

    @Test
    @DisplayName("빈 조회")
    void findAllBean() {
        if (ac != null) {
            String[] beanDefinitionNames = ac.getBeanDefinitionNames();
            for (String beanDefinitionName : beanDefinitionNames) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("bean = " + bean);
            }
        }
    }
}
