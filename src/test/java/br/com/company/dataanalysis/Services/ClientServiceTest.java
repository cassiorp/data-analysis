package br.com.company.dataanalysis.Services;

import br.com.company.dataanalysis.Entities.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClientServiceTest {

    File path = new File("/home/teste");
    ClientService clientService = new ClientService();
    List<Client> clients = new ArrayList<>();
    List<Object> objects = new ArrayList<>();

    @Test
    void ifCreteClient(){
        String line = "002ç2345675434544346çJose da SilvaçRural";
        String line2 = "002ç2345675434544366çJose RibeiroçComercio";

        Client client1 = clientService.createClient(line, objects, 1, path);
        objects.add(client1);
        Client client2 = clientService.createClient(line2, objects, 2, path);
        objects.add(client2);


        List<Client> clients = clientService.getAllClients(objects);

        Assertions.assertEquals("2345675434544346", clients.get(0).getCnpj());
        Assertions.assertEquals("2345675434544366", clients.get(1).getCnpj());
        Assertions.assertEquals("Jose da Silva", clients.get(0).getName());
        Assertions.assertEquals("Jose Ribeiro", clients.get(1).getName());
        Assertions.assertEquals("Rural", clients.get(0).getBusiness());
        Assertions.assertEquals("Comercio", clients.get(1).getBusiness());
    }

    @Test
    void ifNotCreateClient(){
        String line = "002ç2345675434544346çJose da SilvaçRural";
        String line2 = "002ç2345675434544346çJose RibeiroçComercio";

        Client client1 = clientService.createClient(line, objects, 1, path);
        objects.add(client1);
        Client client2 = clientService.createClient(line2, objects, 2, path);
        objects.add(client2);


        List<Client> clients = clientService.getAllClients(objects);
        Assertions.assertEquals("2345675434544346", clients.get(0).getCnpj());
        Assertions.assertEquals("Jose da Silva", clients.get(0).getName());
        Assertions.assertEquals("Rural", clients.get(0).getBusiness());
        Assertions.assertEquals(null, client2);

    }

}
