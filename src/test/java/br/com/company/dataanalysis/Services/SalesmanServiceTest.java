package br.com.company.dataanalysis.Services;

import br.com.company.dataanalysis.Entities.Client;
import br.com.company.dataanalysis.Entities.Sale;
import br.com.company.dataanalysis.Entities.Salesman;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SalesmanServiceTest {

   SalesmanService salesmanService = new SalesmanService();
   SaleService saleService = new SaleService();
   List<Salesman> salesmens = new ArrayList<>();
   File file = new File("/home/teste");
   List<Object> objects = new ArrayList<>();

   @Test
    void ifCreteClient(){
        String line = "001ç00100100165çPedroç1900.0";
        String line2 = "001ç00100100166çJoseç1900.0";

        Salesman salesman1 = salesmanService.createSalesman(line,objects, 1,file );
        Salesman salesman2 = salesmanService.createSalesman(line2,objects, 2,file );

        Assertions.assertEquals("00100100165", salesman1.getCpf());
        Assertions.assertEquals("00100100166", salesman2.getCpf());
        Assertions.assertEquals("Pedro", salesman1.getName());
        Assertions.assertEquals("Jose", salesman2.getName());
        Assertions.assertEquals(1900.0, salesman1.getSalary());
        Assertions.assertEquals(1900.0, salesman2.getSalary());
    }

    @Test
    void ifNotCreatedSalesman(){
        String line = "001ç00100100166çPedroç1900.0";
        String line2 = "001ç00100100166çJoseç1900.0";

        Salesman salesman1 = salesmanService.createSalesman(line,objects, 1,file );
        objects.add(salesman1);
        Salesman salesman2 = salesmanService.createSalesman(line2,objects, 2,file );
        objects.add(salesman2);
        Assertions.assertEquals("00100100166", salesman1.getCpf());
        Assertions.assertEquals(null, salesman2);
    }

    @Test
    void getAllSalesmans(){
        String line = "001ç00100100166çPedroç1900.0";
        String line2 = "001ç00100100166çJoseç1900.0";

        Salesman salesman1 = salesmanService.createSalesman(line,objects, 1,file );
        Salesman salesman2 = salesmanService.createSalesman(line2,objects, 2,file );

        objects.add(salesman1);
        objects.add(salesman2);
        objects.add(new Sale());
        objects.add(new Sale());
        objects.add(new Sale());
        objects.add(new Client());
        objects.add(new Client());
        objects.add(new Salesman());
        objects.add(new Salesman());

        List<Salesman> salesmens = salesmanService.getAllSalesmans(objects);

        Assertions.assertEquals(4, salesmens.size());


    }

    @Test
    void ifFindByName(){
        String line = "001ç00100100165çPedroç1900.0";
        String line2 = "001ç00100100166çJoseç1900.0";

        Salesman salesman1 = salesmanService.createSalesman(line,objects, 1,file );
        objects.add(salesman1);

        Salesman salesman2 = salesmanService.createSalesman(line2,objects, 2,file );
        objects.add(salesman2);

        Salesman salesmanTest = salesmanService.findByName("Pedro", objects);
        Salesman salesmanTest2 = salesmanService.findByName("Julio", objects);

        Assertions.assertEquals(salesmanTest.getCpf(), salesman1.getCpf());
        Assertions.assertEquals(null, salesmanTest2);
   }

    @Test
    void getWorstSalesman(){
        String line = "001ç00100100165çPedroç1900.0";
        String line2 = "001ç00100100166çJoseç1900.0";
        String line3 = "001ç00100100167çCassioç1900.0";
        String saleLine =  "003ç10ç[1-10-1,2-15-2.50,3-10-3.10]çPedro";
        String saleLine2 =  "003ç10ç[1-10-10,2-30-2.50,3-40-3.10]çJose";
        String saleLine3 =  "S003ç10ç[1-10-1000,2-30-2.50,3-40-300.10]çCassio";

        List<Sale> sales = new ArrayList<>();

        Salesman salesman1 = salesmanService.createSalesman(line,objects, 1,file );
        objects.add(salesman1);

        Salesman salesman2 = salesmanService.createSalesman(line2,objects, 2,file );
        objects.add(salesman2);

        Salesman salesman3 = salesmanService.createSalesman(line3,objects, 2,file );
        objects.add(salesman3);

        saleService.createSale(saleLine, sales, salesman1);
        saleService.createSale(saleLine2, sales, salesman2);
        saleService.createSale(saleLine3, sales, salesman3);

        List<Salesman> salesmens = salesmanService.getAllSalesmans(objects);

        Salesman wort = salesmanService.worstSalesman(salesmens);

        Assertions.assertEquals("Pedro", wort.getName());

    }

    @Test
    void ifExists(){
        Salesman salesman1 = new Salesman("00100100165", "pedro", 1900.0);
        objects.add(salesman1);

        Salesman salesman2 = new Salesman("00100100166", "pedro", 1900.0);

        boolean verify = salesmanService.ifExists(salesman1, objects);
        boolean verify2 = salesmanService.ifExists(salesman2,objects);
        Assertions.assertEquals(true, verify);
        Assertions.assertEquals(false, verify2);
    }
}
