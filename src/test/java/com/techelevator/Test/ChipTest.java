package com.techelevator.Test;

import com.techelevator.Chips;
import org.junit.Test;
import org.junit.Assert;

import java.math.BigDecimal;

public class ChipTest {

    @Test
    public void displays_vending_message() {

        var testChip = new Chips("TestName", BigDecimal.valueOf(111), "testslot", "testtype", 111);
        Assert.assertEquals("Crunch Crunch, Yum!", testChip.getVendingPhrase());
    }

    @Test
    public void displays_correct_price() {

        var testChip = new Chips("TestName", BigDecimal.valueOf(111), "testslot", "testtype", 111);
        Assert.assertEquals(BigDecimal.valueOf(111), testChip.getPrice());
    }

    @Test
    public void displays_correct_name() {

        var testChip = new Chips("TestName", BigDecimal.valueOf(111), "testslot", "testtype", 111);
        Assert.assertEquals("TestName", testChip.getName());
    }

}
