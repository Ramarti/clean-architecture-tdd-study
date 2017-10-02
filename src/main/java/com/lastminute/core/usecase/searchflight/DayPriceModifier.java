package com.lastminute.core.usecase.searchflight;

import com.lastminute.core.entity.DayPriceModificationResult;

/**
 * Interface for business rule ruling prices and days
 * Having this separation is interesting if business rules change
 */
public interface DayPriceModifier {

    public DayPriceModificationResult modifyPrice(double rawPrice, int days);
}
