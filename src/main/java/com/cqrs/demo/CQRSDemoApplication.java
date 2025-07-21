package com.cqrs.demo;

import com.filipmikolajzeglen.cqrs.spring.EnableCqrs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCqrs
@SpringBootApplication
@EnableTransactionManagement
public class CQRSDemoApplication
{
   public static void main(String[] args)
   {
      SpringApplication.run(CQRSDemoApplication.class, args);
   }
}