package br.com.company.dataanalysis.Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemTest {

    @Test
    void getIdSale(){
        Item item = new Item();
        item.setIdItem(1);

        Assertions.assertEquals(1, item.getIdItem());
    }

    @Test
    void getQuant(){
        Item item = new Item();
        item.setQuant(190);

        int quantTest = 190;

        Assertions.assertEquals(quantTest, item.getQuant());
    }

    @Test
    void getPrice(){
        Item item = new Item();
        item.setPrice(99.99);

        Double priceTest = 99.99;

        Assertions.assertEquals(priceTest, item.getPrice());
    }
}
