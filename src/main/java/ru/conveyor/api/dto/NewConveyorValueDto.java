package ru.conveyor.api.dto;

import ru.conveyor.api.validation.PrimeNumberConstraint;

public class NewConveyorValueDto {

    @PrimeNumberConstraint
    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
