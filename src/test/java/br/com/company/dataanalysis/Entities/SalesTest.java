package br.com.company.dataanalysis.Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SalesTest {

    @Test
    void getID(){
        Sale sale = new Sale();
        Integer IDtest = sale.getID();
        Integer ID = 003;

        Assertions.assertEquals(ID, IDtest);
    }


    @Test
    void getIdSale(){
        Sale sale = new Sale();
        sale.setIdSale(1);

        Assertions.assertEquals(1, sale.getIdSale());
    }


    @Test
    void getItems(){
        Item item1 = new Item(1, 10, 102.98);
        Item item2 = new Item(2, 2, 10.15);
        List<Item> itens = new ArrayList<>();
        itens.add(item1);
        itens.add(item2);

        Sale sale = new Sale();
        sale.setIdSale(1);
        sale.setItens(itens);

        Assertions.assertEquals(1, sale.getItens().get(0).getIdItem());
        Assertions.assertEquals(2, sale.getItens().get(1).getIdItem());

        Assertions.assertEquals(10, sale.getItens().get(0).getQuant());
        Assertions.assertEquals(2, sale.getItens().get(1).getQuant());

        Assertions.assertEquals(102.98, sale.getItens().get(0).getPrice());
        Assertions.assertEquals(10.15, sale.getItens().get(1).getPrice());
    }

    @Test
    void getSalesmam(){
        Salesman salesman = new Salesman("00100100156", "Pedro", 1900.0);

        Sale sale = new Sale();
        sale.setIdSale(1);
        sale.setSalesman(salesman);

        Assertions.assertEquals("00100100156", sale.getSalesman().getCpf() );
        Assertions.assertEquals("Pedro", sale.getSalesman().getName());
        Assertions.assertEquals(1900.0, sale.getSalesman().getSalary());



    }







}
