package com.lastminute.doubles;

import com.lastminute.core.entity.DayPriceModificationResult;
import com.lastminute.core.usecase.searchflight.DayPriceModifier;

public class MockDayPriceModifier implements DayPriceModifier {

    private boolean modifierCalled = false;
    public DayPriceModificationResult priceToReturn;

    public boolean wasModifierCalled() {
        return  modifierCalled;
    }

    @Override
    public DayPriceModificationResult modifyPrice(double rawPrice, int days) {
        modifierCalled = true;
        return priceToReturn;
    }
}
