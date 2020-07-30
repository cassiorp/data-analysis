package br.com.company.dataanalysis.Services;

import br.com.company.dataanalysis.Entities.Client;
import br.com.company.dataanalysis.Entities.Sale;
import br.com.company.dataanalysis.Entities.Salesman;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SaleServiceTest {

    File path = new File("/home/teste");

    SaleService saleService = new SaleService();
    SalesmanService salesmanService = new SalesmanService();
    List<Sale> sales = new ArrayList<>();
    List<Object> objects = new ArrayList<>();


    @Test
    void ifCreateSaleWithDiferentId(){

        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çCassio";
        String line2 = "003ç11ç[1-10-100,2-30-2.50,3-40-3.10]çCassio";

        String salesmanLine = "001ç1234567891234çCassioç50000";
        String salesmanLine2 = "001ç3245678865434çPedroç40000.99";

        Salesman salesman = salesmanService.createSalesman(salesmanLine, objects, 1, path);
        Salesman salesman2 = salesmanService.createSalesman(salesmanLine2, objects, 2, path);
        objects.add(salesman);
        objects.add(salesman2);


        Sale sale = saleService.createSale(line, objects, 1, path);
        objects.add(sale);

        Sale sale2 = saleService.createSale(line2, objects, 1, path);
        objects.add(sale2);

        List<Sale> sales = saleService.getAllSales(objects);

        Assertions.assertEquals(10, sales.get(0).getIdSale());
        Assertions.assertEquals(11, sales.get(1).getIdSale());
    }

    @Test
    void ifNotCreateWithSameId(){
        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çCassio";
        String line2 = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çCassio";

        String salesmanLine = "001ç1234567891234çCassioç50000";

        Salesman salesman = salesmanService.createSalesman(salesmanLine, objects, 1, path);
        objects.add(salesman);


        Sale sale = saleService.createSale(line, objects, 1, path);
        objects.add(sale);

        Sale sale2 = saleService.createSale(line2, objects, 1, path);
        objects.add(sale2);

        Assertions.assertEquals(10, sale.getIdSale());
        Assertions.assertEquals(null, sale2);
    }

    @Test
    void ifNotCreateWithSameNotFoundSalesman(){
        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çCassio";
        String line2 = "003ç11ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";

        String salesmanLine = "001ç1234567891234çCassioç50000";

        Salesman salesman = salesmanService.createSalesman(salesmanLine, objects, 1, path);
        objects.add(salesman);


        Sale sale = saleService.createSale(line2, objects, 1, path);
        objects.add(sale);

        System.out.println(sale);

        Sale sale2 = saleService.createSale(line, objects, 2, path);
        objects.add(sale2);

        List<Sale> sales = saleService.getAllSales(objects);

        Assertions.assertEquals(null, sale);
        Assertions.assertEquals(1, sales.size());
        Assertions.assertEquals(10,sale2.getIdSale());

    }

    @Test
    void getAllSales(){

        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
        String line2 = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";

        //Sale sale1 = saleService.createSale(line, objects, 1, file);

        Sale sale = new Sale(1, null, null);
        Sale sale2 = new Sale(2, null, null);
        Sale sale3 = new Sale(3, null, null);

        objects.add(new Salesman());
        objects.add(new Client());
        objects.add(new  Salesman());
        objects.add(sale);
        objects.add(new Client());
        objects.add(sale2);
        objects.add(new Client());
        objects.add(new Salesman());
        objects.add(new Salesman());

        List<Sale> sales = saleService.getAllSales(objects);

        Assertions.assertEquals(2, sales.size());
    }

    @Test
    void ifExists(){
        Sale sale = new Sale(1, null, null);
        Sale sale2 = new Sale(2, null, null);
        Sale sale3 = new Sale(5, null, null);

        objects.add(sale);
        boolean verify = saleService.ifExists(sale, objects);

        objects.add(sale2);
        boolean verify2 = saleService.ifExists(sale2, objects);

        boolean verify3 = saleService.ifExists(sale3, objects);
        boolean verify4 = saleService.ifExists(new Sale(6, null, null), objects);


        Assertions.assertEquals(true, verify);
        Assertions.assertEquals(true, verify2);
        Assertions.assertEquals(false, verify3);
        Assertions.assertEquals(false, verify4);
    }



    @Test
    void bestSale(){
        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çCassio";
        String line2 = "003ç11ç[1-10-98,2-30-2.50,3-40-3.10]çCassio";
        String line3 = "003ç12ç[1-10-100000,2-30-2.50,3-40-3.10]çCassio";

        String salesmanLine = "001ç1234567891234çCassioç50000";
        String salesmanLine2 = "001ç3245678865434çPedroç40000.99";

        Salesman salesman = salesmanService.createSalesman(salesmanLine, objects, 1, path);
        Salesman salesman2 = salesmanService.createSalesman(salesmanLine2, objects, 2, path);
        objects.add(salesman);
        objects.add(salesman2);

        Sale sale = saleService.createSale(line, objects, 1, path);
        objects.add(sale);

        Sale sale2 = saleService.createSale(line2, objects, 2, path);
        objects.add(sale2);

        Sale sale3 = saleService.createSale(line3, objects, 1, path);
        objects.add(sale3);

        List<Sale> sales = saleService.getAllSales(objects);
        int best = saleService.bestSale(sales);

        Assertions.assertEquals(12, best);

    }

    @Test
    void getNameSalesman(){
        String saleStr = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çCassio";
        String name = saleService.extractsName(saleStr);

        String saleStr2 = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
        String name2 = saleService.extractsName(saleStr2);

        Assertions.assertEquals("Cassio", name);
        Assertions.assertEquals("Pedro", name2);
    }

    @Test
    void totalSaleValue(){
        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çCassio";
        String salesmanLine = "001ç1234567891234çCassioç50000";


        Salesman salesman = salesmanService.createSalesman(salesmanLine, objects, 1, path);
        objects.add(salesman);

        Sale sale = saleService.createSale(line, objects, 1, path);
        objects.add(sale);

        Double total = saleService.totalSaleValue(sale);

        Assertions.assertEquals(1199, total);
    }

    @Test
    void totalAllSaleValue() {
        String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çCassio";
        String line2 = "003ç11ç[1-10-100,2-30-2.50,3-40-3.10]çCassio";
        String line3 = "003ç12ç[1-10-100,2-30-2.50,3-40-3.10]çCassio";

        String salesmanLine = "001ç1234567891234çCassioç50000";


        Salesman salesman = salesmanService.createSalesman(salesmanLine, objects, 1, path);
        objects.add(salesman);

        Sale sale = saleService.createSale(line, objects, 1, path);
        objects.add(sale);

        Sale sale2 = saleService.createSale(line2, objects, 2, path);
        objects.add(sale2);

        Sale sale3 = saleService.createSale(line3, objects, 1, path);
        objects.add(sale3);

        List<Sale> sales = saleService.getAllSales(objects);
        Double total = saleService.totalAllSaleValue(sales);

        Assertions.assertEquals(3597, total);


    }
}
