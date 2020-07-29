package br.com.company.dataanalysis.Services;

import br.com.company.dataanalysis.Entities.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ClientServiceTest {

    ClientService clientService = new ClientService();
    List<Client> clients = new ArrayList<>();

    @Test
    void ifCreteClient(){
        String client = "002ç2345675434544346çJose da SilvaçRural";
        String client2 = "002ç2345675434544366çJose RibeiroçComercio";

        clientService.createClient(client, clients);
        clientService.createClient(client2, clients);

        Assertions.assertEquals("2345675434544346", clients.get(0).getCnpj());
        Assertions.assertEquals("2345675434544366", clients.get(1).getCnpj());
        Assertions.assertEquals("Jose da Silva", clients.get(0).getName());
        Assertions.assertEquals("Jose Ribeiro", clients.get(1).getName());
        Assertions.assertEquals("Rural", clients.get(0).getBusiness());
        Assertions.assertEquals("Comercio", clients.get(1).getBusiness());
    }

    @Test
    void ifNotCreateClient(){
        String client = "002ç2345675434544366çJose da SilvaçRural";
        String client2 = "002ç2345675434544366çJose RibeiroçComercio";

        clientService.createClient(client, clients);
        boolean verify = clientService.createClient(client2, clients);

        Assertions.assertEquals(false, verify);
    }

}
