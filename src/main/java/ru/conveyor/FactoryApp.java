package ru.conveyor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.conveyor.config.FactoryConfig;
import ru.conveyor.data.SimpleConveyor;
import ru.conveyor.util.PrimeNumberUtils;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class FactoryApp {

    public static void main(String[] args) {
        SpringApplication.run(FactoryApp.class, args);
    }

}
