package com.lastminute.core.entity;

public interface FlightPriceModifier {

    Double modify(Double rawprice,PriceParameters parameter);
}
