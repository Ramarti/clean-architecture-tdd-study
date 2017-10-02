package com.lastminute.core.entity;

import java.util.Arrays;
import java.util.Optional;

/**
 * Individual result of a search -> price modifications
 */
public class FlightResult {

    private String code;
    private double originalPrice;
    private double finalPrice;
    private double dayModifierApplied;

    private String currency;

    private FlightResult(Builder builder) {
        this.code = builder.getCode().get();
        this.originalPrice = builder.getOriginalPrice().get();
        this.finalPrice = builder.getCurrentPrice().get();
        this.dayModifierApplied = builder.getDayModifierApplied().get();
        this.currency = builder.getCurrency().get();
    }

    public String getCode() {
        return code;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getDayModifierApplied() {
        return dayModifierApplied;
    }

    /**
     * Kind of a Step Builder
     */
    public static class Builder {
        private Optional<String> code;
        private Optional<Double> originalPrice;
        private Optional<Double> currentPrice;
        private Optional<Double> dayModifierApplied;
        private Optional<String> currency;

        /**
         * Constructor with first required params
         * @param code flightCode
         * @param originalPrice
         */
        public Builder(String code, double originalPrice) {
            this.code = Optional.of(code);
            this.originalPrice = Optional.of(originalPrice);
            this.currentPrice = Optional.empty();
            this.dayModifierApplied = Optional.empty();
            this.currency = Optional.empty();
        }

        /**
         * Will check if everything is there, could throw IllegalStateException
         * @return the FlightResult
         */
        public FlightResult build() {
            checkIfBuilt();
            return new FlightResult(this);
        }

        private void checkIfBuilt() {
            boolean allPresent = code.isPresent()
                    && originalPrice.isPresent()
                    && currentPrice.isPresent()
                    && dayModifierApplied.isPresent()
                    && currency.isPresent();
            if (!allPresent) {
                throw new IllegalStateException("FlightResult.Builder incomplete");
            }
        }
        public Optional<Double> getOriginalPrice() {
            return originalPrice;
        }

        public Optional<Double> getCurrentPrice() {
            return currentPrice;
        }

        public Optional<Double> getDayModifierApplied() {
            return dayModifierApplied;
        }

        public Optional<String> getCurrency() {
            return currency;
        }

        public Optional<String > getCode() {
            return code;
        }

        /**
         * Step of price modification according to days
         * @param modifier
         * @param price new price
         * @return
         */
        public Builder dayModifierApplied(double modifier, double price){
            this.currentPrice = Optional.of(price);
            this.dayModifierApplied = Optional.of(modifier);
            return this;
        }

        /**
         * Step of price modification according to passenger number
         * @param currentPrice
         * @return
         */
        public Builder passengerModifierApplied(double currentPrice) {
            this.currentPrice = Optional.of(currentPrice);
            return this;
        }

        /**
         * Currency is cosmetic at the moment
         * @param currency symbol
         * @return
         */
        public Builder currency(String currency) {
            this.currency = Optional.of(currency);
            return this;
        }



    }
}
