package com.techelevator.Test;

import com.techelevator.Candy;
import org.junit.Test;
import org.junit.Assert;

import java.math.BigDecimal;

public class CandyTest {

    @Test
    public void displays_vending_message() {

        var testCandy = new Candy("TestName", BigDecimal.valueOf(111), "testslot", "testtype", 111);
        Assert.assertEquals("Munch Munch, Yum!", testCandy.getVendingPhrase());
    }

    @Test
    public void displays_correct_price() {

        var testCandy = new Candy("TestName", BigDecimal.valueOf(111), "testslot", "testtype", 111);
        Assert.assertEquals(BigDecimal.valueOf(111), testCandy.getPrice());
    }

    @Test
    public void displays_correct_name() {

        var testCandy = new Candy("TestName", BigDecimal.valueOf(111), "testslot", "testtype", 111);
        Assert.assertEquals("TestName", testCandy.getName());
    }


}
