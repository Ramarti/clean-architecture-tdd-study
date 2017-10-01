package com.lastminute.doubles;

import com.lastminute.core.usecase.searchflight.PassengerPriceModifier;

public class MockPassengersPriceModifier implements PassengerPriceModifier {

    private boolean modifierCalled = false;

    private double priceToReturn = 0;

    public boolean wasModifierCalled() {
        return modifierCalled;
    }

    @Override
    public double modifyPrice(double rawPrice, int passengers) {
        modifierCalled = true;
        return priceToReturn;
    }
}
