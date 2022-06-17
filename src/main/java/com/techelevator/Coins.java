package com.techelevator;

import java.math.BigDecimal;

public enum Coins {

        PENNY(BigDecimal.valueOf(.01)), NICKLE(BigDecimal.valueOf(.05)), DIME(BigDecimal.valueOf(.10)), QUARTER(BigDecimal.valueOf(.25));

        private BigDecimal coinDenomination;

        private Coins(BigDecimal coinDenomination){
            this.coinDenomination = coinDenomination;
        }

        public BigDecimal getDenomination(){
            return coinDenomination;
        }
}

