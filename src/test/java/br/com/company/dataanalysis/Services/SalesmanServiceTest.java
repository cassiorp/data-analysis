package br.com.company.dataanalysis.Services;

import br.com.company.dataanalysis.Entities.Sale;
import br.com.company.dataanalysis.Entities.Salesman;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SalesmanServiceTest {

   SalesmanService salesmanService = new SalesmanService();
   SaleService saleService = new SaleService();
   List<Salesman> salesmens = new ArrayList<>();

    @Test
    void ifCreteClient(){
        String salesman = "001ç00100100165çPedroç1900.0";
        String salesman2 = "001ç00100100166çJoseç1900.0";

        salesmanService.createSalesman(salesman, salesmens);
        salesmanService.createSalesman(salesman2, salesmens);

        Assertions.assertEquals("00100100165", salesmens.get(0).getCpf());
        Assertions.assertEquals("00100100166", salesmens.get(1).getCpf());
        Assertions.assertEquals("Pedro", salesmens.get(0).getName());
        Assertions.assertEquals("Jose", salesmens.get(1).getName());
        Assertions.assertEquals(1900.0, salesmens.get(0).getSalary());
        Assertions.assertEquals(1900.0, salesmens.get(0).getSalary());
    }

    @Test
    void ifNotCreatedSalesman(){
        String salesman = "001ç00100100165çPedroç1900.0";
        String salesman2 = "001ç00100100165çJoseç1900.0";

        salesmanService.createSalesman(salesman, salesmens);
        boolean verify = salesmanService.createSalesman(salesman2, salesmens);

        Assertions.assertEquals(false, verify);
    }

    @Test
    void ifFindByName(){
        String salesman = "001ç00100100165çPedroç1900.0";
        String salesman2 = "001ç00100100166çJoseç1900.0";

        salesmanService.createSalesman(salesman, salesmens);
        salesmanService.createSalesman(salesman2, salesmens);

        Salesman salesmanTest = salesmanService.findByName("Pedro", salesmens);

        Assertions.assertEquals(salesmanTest.getName(), salesmens.get(0).getName());
    }

    @Test
    void getWorstSalesman(){
        String salesman = "001ç00100100165çPedroç1900.0";
        String salesman2 = "001ç00100100166çJoseç1900.0";
        String salesman3 = "001ç00100100167çCassioç1900.0";

        List<Sale> sales = new ArrayList<>();

        salesmanService.createSalesman(salesman, salesmens);
        salesmanService.createSalesman(salesman2, salesmens);
        salesmanService.createSalesman(salesman3, salesmens);

        String sale = "003ç10ç[1-10-100,2-10-100,3-10-100]çPedro";
        String sale2 = "003ç11ç[4-10-100,5-10-100,6-10-100]çPedro";
        saleService.createSale(sale,sales,salesmens.get(0));
        saleService.createSale(sale2,sales,salesmens.get(0));


        String sale3 = "003ç12ç[1-1-2,2-11-0.6,3-11-1.99]çJose";
        String sale4 = "003ç13ç[1-1-2,2-11-0.6,3-11-1.99]çJose";
        saleService.createSale(sale3,sales,salesmens.get(1));
        saleService.createSale(sale4,sales,salesmens.get(1));

        String sale5 = "003ç14ç[3-12-0,2-20-0,1-20-0]çCassio";
        String sale6 = "003ç15ç[3-12-0,2-20-0,1-20-0]çCassio";
        saleService.createSale(sale5,sales,salesmens.get(2));
        saleService.createSale(sale6,sales,salesmens.get(2));

        Salesman wort = salesmanService.worstSalesman(salesmens);

        Assertions.assertEquals("Cassio", wort.getName());

    }

    @Test
    void ifExists(){
        String salesman = "001ç00100100165çPedroç1900.0";
        String salesman2 = "001ç00100100166çJoseç1900.0";

        salesmanService.createSalesman(salesman, salesmens);
        salesmanService.createSalesman(salesman2, salesmens);

        Boolean veriry = salesmanService.ifExists("Jose", salesmens);

        Assertions.assertEquals(true, veriry);
    }
}
