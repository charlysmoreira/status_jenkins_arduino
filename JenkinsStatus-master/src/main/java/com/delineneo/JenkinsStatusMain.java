package com.delineneo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JenkinsStatusMain {

    public JenkinsStatusMain() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:*/jenkinsStatusSystem.xml"});
    }

    public static void main(String[] args) throws Exception {
    	System.out.println("Rodando..");
        JenkinsStatusMain jenkinsStatusMain = new JenkinsStatusMain();
    }
}
