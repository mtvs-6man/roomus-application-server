package com.sixman.roomus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories
public class RoomusApplicaitonSeverApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomusApplicaitonSeverApplication.class, args);}

}
