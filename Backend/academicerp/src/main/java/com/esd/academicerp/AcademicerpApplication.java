package com.esd.academicerp;

import com.esd.academicerp.securityconfig.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AcademicerpApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcademicerpApplication.class, args);
    }

}
