package com.techelevator.Test;

import com.techelevator.Beverages;
import org.junit.Test;
import org.junit.Assert;

import java.math.BigDecimal;

public class BeverageTest {

    @Test
    public void displays_vending_message() {

        var testBev = new Beverages("TestName", BigDecimal.valueOf(111), "testslot", "testtype", 111);
        Assert.assertEquals("Glug Glug, Yum!", testBev.getVendingPhrase());
    }

    @Test
    public void displays_correct_price() {

        var testBev = new Beverages("TestName", BigDecimal.valueOf(111), "testslot", "testtype", 111);
        Assert.assertEquals(BigDecimal.valueOf(111), testBev.getPrice());
    }

    @Test
    public void displays_correct_name() {

        var testBev = new Beverages("TestName", BigDecimal.valueOf(111), "testslot", "testtype", 111);
        Assert.assertEquals("TestName", testBev.getName());
    }


}