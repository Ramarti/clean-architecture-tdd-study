package com.lastminute.core.entity;

public class PriceParameters {

    private Integer daysPriorToDeparture;

    public PriceParameters(Integer daysPriorToDeparture) {
        if (daysPriorToDeparture < 0) {
            throw new IllegalArgumentException("need positive daysPriorToDeparture");
        }
        this.daysPriorToDeparture = daysPriorToDeparture;
    }


    public Integer getDaysPriorToDeparture() {
        return daysPriorToDeparture;
    }

}
