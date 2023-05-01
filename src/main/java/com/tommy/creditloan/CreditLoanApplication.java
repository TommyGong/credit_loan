package com.tommy.creditloan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication()
public class CreditLoanApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditLoanApplication.class, args);
    }

}
