package ru.conveyor.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class FactoryController {

    @PostMapping("/factory/conveyor/b/push")
    public Integer pushValueToConveyorA(@RequestBody String value) {
        return 0; // todo: реализовать
    }

    @PostMapping("/factory/conveyor/b/push")
    public Integer pushValueToConveyorB(@RequestBody String value) {
        return 0; // todo: реализовать
    }

    @GetMapping("/factory/status")
    public Object getStatusForFactory() {
        return null; // todo: реализовать
    }

    @GetMapping("/factory/conveyor/a/status")
    public List<Integer> getStatusForConveyorA() {
        return Collections.emptyList(); // todo: реализовать
    }

    @GetMapping("/factory/conveyor/b/status")
    public List<Integer> getStatusForConveyorB() {
        return Collections.emptyList(); // todo: реализовать
    }
}