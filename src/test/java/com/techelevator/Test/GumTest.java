package com.techelevator.Test;

import com.techelevator.Gum;
import org.junit.Test;
import org.junit.Assert;

import java.math.BigDecimal;

public class GumTest {

    @Test
    public void displays_vending_message() {

        var testGum = new Gum("TestName", BigDecimal.valueOf(111), "testslot", "testtype", 111);
        Assert.assertEquals("Chew Chew, Yum!", testGum.getVendingPhrase());
    }

    @Test
    public void displays_correct_price() {

        var testGum = new Gum("TestName", BigDecimal.valueOf(111), "testslot", "testtype", 111);
        Assert.assertEquals(BigDecimal.valueOf(111), testGum.getPrice());
    }

    @Test
    public void displays_correct_name() {

        var testGum = new Gum("TestName", BigDecimal.valueOf(111), "testslot", "testtype", 111);
        Assert.assertEquals("TestName", testGum.getName());
    }

}
