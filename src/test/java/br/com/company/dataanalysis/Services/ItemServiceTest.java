package br.com.company.dataanalysis.Services;

import br.com.company.dataanalysis.Entities.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ItemServiceTest {
    ItemService itemService = new ItemService();


    @Test
    void ifCreateItens(){
        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çCassio";

        List<Item> items = itemService.createItemsForSale(line);

        Assertions.assertEquals(1, items.get(0).getIdItem());
        Assertions.assertEquals(2, items.get(1).getIdItem());
        Assertions.assertEquals(3, items.get(2).getIdItem());

        Assertions.assertEquals(3, items.size());
    }

    @Test
    void ifNotCreateItems(){
        String line = "001ç1234567891234çPedroç50000";
        String line2 = "002ç1234567891234çPedroç50000";

        List<Item> items = itemService.createItemsForSale(line);
        List<Item> items2 = itemService.createItemsForSale(line2);
        Assertions.assertEquals(null, items);
        Assertions.assertEquals(null, items2);
    }

    @Test
    void ifExtract(){
        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
        String item = itemService.extractsItem(line);
        Assertions.assertEquals("1-10-100,2-30-2.50,3-40-3.10", item);
    }

}
