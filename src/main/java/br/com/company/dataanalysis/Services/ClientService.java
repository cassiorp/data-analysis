package br.com.company.dataanalysis.Services;

import br.com.company.dataanalysis.Entities.Client;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ClientService {

    private final Logger logger = Logger.getLogger(Client.class.getName());

    public Client createClient(String line, List<Object> obj, Integer wichLine, File file){
        String[] separedLine = line.split("ç");
        Client client = new Client(separedLine[1], separedLine[2], separedLine[3]);
        if(ifExists(client, obj)){
            logger.info("Line: "+ wichLine+" Path: "+ file.getAbsolutePath() +" Salesman with cpf already registred");
            return null;
        }
        return client;
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
    private Boolean ifExists(Client client, List<Object> objs){
        List<Client> clients = this.getAllClients(objs);
        for(Client c: clients){
            if(client.getCnpj().equals(c.getCnpj())){
                return true;
            }
        }
        return false;
    }
}
