package com.lastminute.core.usecase.searchflight;

import com.lastminute.core.entity.DayPriceModificationResult;

public interface DayPriceModifier {

    public DayPriceModificationResult modifyPrice(double rawPrice, int days);
}
