package io.wanyxkhalil.abstinence.wub.consul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class SampleApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    @Autowired
    private Environment env;

    @Override
    public void run(String... args) throws Exception {
        String property = env.getProperty("test.name");
        System.out.println(property);
    }
}
