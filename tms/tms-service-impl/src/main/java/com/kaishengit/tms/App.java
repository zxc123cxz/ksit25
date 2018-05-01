package com.kaishengit.tms;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {

        public static void main(String[] args) throws IOException{

            ClassPathXmlApplicationContext con = new ClassPathXmlApplicationContext("spring.xml");
            con.start();

            System.out.println("。。。服务启动完毕。。。");
            System.in.read();

        }



}
