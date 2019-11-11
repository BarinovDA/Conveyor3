package ru.conveyor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import ru.conveyor.config.FactoryConfig;
import ru.conveyor.data.SimpleConveyor;
import ru.conveyor.util.PrimeNumberUtils;

import java.util.Collections;
import java.util.List;

@PropertySource("classpath:config.properties")
@SpringBootApplication
public class FactoryApp {

    public static void main(String[] args) {
        SpringApplication.run(FactoryApp.class, args);
    }
}
