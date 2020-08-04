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
        Salesman salesmanTest2 = salesmanService.findByName("Cassio", objects);

        Assertions.assertEquals(salesmanTest.getCpf(), salesman1.getCpf());
        Assertions.assertEquals(null, salesmanTest2);
   }

    @Test
    void getWorstSalesman(){
        String salesmanLine1 = "001ç00100100165çPedroç1900.0";
        String salesmanLine2 = "001ç00100100166çJoseç1900.0";
        String salesmanLine3 = "001ç00100100167çCassioç1900.0";
        String salesmanLine4 = "001ç00100100168çEduardoç1900.0";

        Salesman salesman1 = salesmanService.createSalesman(salesmanLine1,objects, 1,file );
        objects.add(salesman1);
        Salesman salesman2 = salesmanService.createSalesman(salesmanLine2,objects, 2,file );
        objects.add(salesman2);
        Salesman salesman3 = salesmanService.createSalesman(salesmanLine3,objects, 3,file );
        objects.add(salesman3);
        Salesman salesman4 = salesmanService.createSalesman(salesmanLine4,objects, 4,file );
        objects.add(salesman4);

        String saleLine =  "003ç10ç[1-100-100,2-150-200,3-100-3000]çPedro";
        String saleLine2 =  "003ç11ç[1-10-10,2-30-2.50,3-40-3.10]çCassio";
        String saleLine3 =  "003ç12ç[1-10-1,2-30-2,3-40-3]çCassio";
        String saleLine4 =  "003ç13ç[1-100-100,2-150-200,3-100-3000]çPedro";
        String saleLine5 =  "003ç14ç[1-100-100,2-150-200,3-100-3000]çCassio";
        String saleLine6 =  "003ç15ç[1-100-100,2-150-200,3-100-3000]çJose";


        objects.add(saleService.createSale(saleLine, objects, 1, file));
        objects.add(saleService.createSale(saleLine2, objects, 2, file));
        objects.add(saleService.createSale(saleLine3, objects, 3, file));
        objects.add(saleService.createSale(saleLine4, objects, 4, file));
        objects.add(saleService.createSale(saleLine5, objects, 5, file));
        objects.add(saleService.createSale(saleLine6, objects, 6, file));

        List<Salesman> salesmens = salesmanService.getAllSalesmans(objects);
        Salesman wort = salesmanService.worstSalesman(salesmens);

        Assertions.assertEquals("Eduardo", wort.getName());
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
