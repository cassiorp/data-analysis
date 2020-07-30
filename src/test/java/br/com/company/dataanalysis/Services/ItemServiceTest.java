package br.com.company.dataanalysis.Services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemServiceTest {
    ItemService itemService = new ItemService();

    @Test
    void ifExtract(){
        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
        String item = itemService.extractsItem(line);
        Assertions.assertEquals("1-10-100,2-30-2.50,3-40-3.10", item);
    }
}
