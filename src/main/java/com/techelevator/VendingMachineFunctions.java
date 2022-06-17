package com.techelevator;

import java.math.BigDecimal;

public class VendingMachineFunctions {

    private BigDecimal currentBalance = BigDecimal.valueOf(0);

    public void insertBills(BillBox bill){
        currentBalance = currentBalance.add(bill.getBillDenomination());
    }

}
