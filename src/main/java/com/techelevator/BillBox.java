package com.techelevator;

import java.math.BigDecimal;

public enum BillBox {

    ONE_DOLLAR_BILL (BigDecimal.valueOf(1.00)), TWO_DOLLAR_BILL(BigDecimal.valueOf(2.00)), FIVE_DOLLAR_BILL(BigDecimal.valueOf(5.00)), TEN_DOLLAR_BILL(BigDecimal.valueOf(10.00));

    private BigDecimal billDenomination;

    private BillBox(BigDecimal billDenomination){
        this.billDenomination = billDenomination;
    }

    public BigDecimal getBillDenomination(){
        return billDenomination;
    }
}


