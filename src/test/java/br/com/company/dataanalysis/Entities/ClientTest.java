package br.com.company.dataanalysis.Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientTest {

    @Test
    void getID(){
        Client client = new Client();
        Integer IDtest = client.getID();
        Integer ID = 002;

        Assertions.assertEquals(ID, IDtest);
    }

    @Test
    void getCnpj(){
        Client client = new Client();
        client.setCnpj("2345675433444345");
        String cnpj = "2345675433444345";

        Assertions.assertEquals(cnpj, client.getCnpj());
    }

    @Test
    void getName(){
        Client client = new Client();
        client.setName("Jose da Silva");
        String name = "Jose da Silva";

        Assertions.assertEquals(name, client.getName());

    }

    @Test
    void getBusiness(){
        Client client = new Client();
        client.setBusiness("Informatica");
        String business = "Informatica";

        Assertions.assertEquals(business, client.getBusiness());
    }
}
