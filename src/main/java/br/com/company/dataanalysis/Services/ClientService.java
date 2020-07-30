package br.com.company.dataanalysis.Services;

import br.com.company.dataanalysis.Entities.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientService {

    public Boolean createClient(String line, List<Client> clients){
        String[] separedLine = line.split("ç");
        int size = clients.size();
        Client client = new Client(separedLine[1], separedLine[2], separedLine[3]);
        if(!clientsValidator(client, clients)){
            return false;
        }
        clients.add(client);
        return size < clients.size() ? true : false;
    }

    public List<Client> getAllClients(List<Object> objects){
        List<Client> clients = new ArrayList<>();
        for(Object obj: objects){
            if(obj instanceof Client){
                clients.add((Client) obj);
            }
        }
        return clients;
    }

    private Boolean clientsValidator(Client client, List<Client> clients){
        for(Client c: clients){
            if(client.getCnpj().equals(c.getCnpj())){
                return false;
            }
        }
        return true;
    }
}
