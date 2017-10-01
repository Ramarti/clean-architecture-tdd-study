package com.lastminute.doubles;

import com.lastminute.core.usecase.searchflight.DayPriceModifier;

public class MockDayPriceModifier implements DayPriceModifier {

    private boolean modifierCalled = false;
    public int priceToReturn = 1;

    public boolean wasModifierCalled() {
        return  modifierCalled;
    }

    @Override
    public double modifyPrice(double rawPrice, int days) {
        modifierCalled = true;
        return priceToReturn;
    }
}
