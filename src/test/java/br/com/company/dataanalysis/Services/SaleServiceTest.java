package br.com.company.dataanalysis.Services;

import br.com.company.dataanalysis.Entities.Sale;
import br.com.company.dataanalysis.Entities.Salesman;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SaleServiceTest {

    SaleService saleService = new SaleService();
    List<Sale> sales = new ArrayList<>();

    @Test
    void ifCreateSale(){
        Salesman salesman = new Salesman("00100100165", "Cassio", 1900.0);
        String saleStr = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çCassio";

        boolean verify = saleService.createSale(saleStr,sales,salesman);

        Assertions.assertEquals(true, verify);
        Assertions.assertEquals(10, sales.get(0).getIdSale());
        Assertions.assertEquals("Cassio", sales.get(0).getSalesman().getName());

    }

    @Test
    void ifNotCreateSale(){
        Salesman salesman = new Salesman("00100100165", "Pedro", 1900.0);
        String saleStr = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çCassio";

        boolean verify = saleService.createSale(saleStr,sales,salesman);

        Assertions.assertEquals(false, verify);
    }

    @Test
    void bestSale(){
        String saleStr = "003ç10ç[1-10-100000,2-30-2.50,3-40-3.10]çCassio";
        String saleStr2 = "003ç11ç[1-10-98,2-30-2.50,3-40-3.10]çCassio";
        String saleStr3 = "003ç12ç[1-10-1000,2-30-2.50,3-40-3.10]çCassio";
        Salesman salesman = new Salesman("00100100165", "Cassio", 1900.0);

        saleService.createSale(saleStr,sales,salesman);
        saleService.createSale(saleStr2,sales,salesman);
        saleService.createSale(saleStr3,sales,salesman);

        int best = saleService.bestSale(sales);

        Assertions.assertEquals(10, best);

    }

    @Test
    void priceAllSalesTotal(){
        String saleStr = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çCassio";
        String saleStr2 = "003ç11ç[1-10-100,2-30-2.50,3-40-3.10]çCassio";
        Salesman salesman = new Salesman("00100100165", "Cassio", 1900.0);
        saleService.createSale(saleStr,sales,salesman);
        saleService.createSale(saleStr2,sales,salesman);

        Assertions.assertEquals(2398, saleService.totalAllSaleValue(sales));
    }

    @Test
    void getNameSalesman(){
        String saleStr = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çCassio";
        String name = saleService.extractsName(saleStr);

        Assertions.assertEquals("Cassio", name);
    }

}
