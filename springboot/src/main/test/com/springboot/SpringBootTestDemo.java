package com.springboot;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)                        //底层用junit  SpringJUnit4ClassRunner
@SpringBootTest(classes = {Application.class})      //启动整个SpringBoot工程
public class SpringBootTestDemo {

    @Test
    public void testOne(){
        System.out.println("test hello");
        TestCase.assertEquals(1,1);
    }



}
