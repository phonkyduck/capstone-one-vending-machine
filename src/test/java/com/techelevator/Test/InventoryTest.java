package com.techelevator.Test;

import com.techelevator.Inventory;
import org.junit.Test;
import org.junit.Assert;

public class InventoryTest {

    @Test
    public void verify_map_size(){

        var invTest = new Inventory().inventoryLoader();
        Assert.assertEquals(16, invTest.size());
    }

    @Test
    public void verify_first_key_present(){

        var invTest = new Inventory().inventoryLoader();
        Assert.assertEquals(true ,invTest.containsKey("A1"));
    }

    @Test
    public void verify_last_key_present(){

        var invTest = new Inventory().inventoryLoader();
        Assert.assertEquals(true ,invTest.containsKey("D4"));
    }

    @Test
    public void verify_moonpie(){

        var invTest = new Inventory().inventoryLoader();
        Assert.assertEquals("Moonpie" ,invTest.get("B1").getName());
    }

}