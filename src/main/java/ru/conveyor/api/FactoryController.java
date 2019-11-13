package ru.conveyor.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.conveyor.api.dto.NewConveyorValueDto;
import ru.conveyor.service.FactoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FactoryController {

    private final FactoryService factoryManager;

    public FactoryController(FactoryService factoryManager) {
        this.factoryManager = factoryManager;
    }

    @PostMapping("/factory/conveyor/a/push")
    public Integer pushValueToConveyorA(@RequestBody @Valid NewConveyorValueDto dto) {
        return factoryManager.pushA(dto.getValue());
    }

    @PostMapping("/factory/conveyor/b/push")
    public Integer pushValueToConveyorB(@RequestBody @Valid NewConveyorValueDto dto) {
        return factoryManager.pushB(dto.getValue());
    }

    @GetMapping("/factory/status")
    public Object getStatusForFactory() {
        return factoryManager.getFactoryStatus();
    }

    @GetMapping("/factory/conveyor/a/status")
    public List<Integer> getStatusForConveyorA() {
        return factoryManager.getStatusConveyorA();
    }

    @GetMapping("/factory/conveyor/b/status")
    public List<Integer> getStatusForConveyorB() {
        return factoryManager.getStatusConveyorB();
    }
}