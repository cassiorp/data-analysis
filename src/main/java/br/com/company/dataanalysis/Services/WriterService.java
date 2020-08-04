package br.com.company.dataanalysis.Services;


import br.com.company.dataanalysis.Entities.Client;
import br.com.company.dataanalysis.Entities.Sale;
import br.com.company.dataanalysis.Entities.Salesman;
import br.com.company.dataanalysis.Utils.PathCreator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class WriterService {

    Date data = new Date(System.currentTimeMillis());
    PathCreator pathCreator = new PathCreator();
    File pathOut = pathCreator.createPath("out");

    SalesmanService salesmanService = new SalesmanService();
    ClientService clientService = new ClientService();
    SaleService saleService = new SaleService();

    public void reportWriter(List<Object> objects){
        List<Sale> sales = saleService.getAllSales(objects);
        List<Salesman> salesmens = salesmanService.getAllSalesmans(objects);
        List<Client> clients = clientService.getAllClients(objects);
        String report = "";
        report= report + ("Number of clients: " + Integer.toString(clients.size())+"\n");
        report= report + ("Number of salesmans: " + Integer.toString(salesmens.size())+"\n");
        report= report + ("The Best Sale id: " +Integer.toString(saleService.bestSale(sales))+"\n");
        if(sales.size() > 0){
            Salesman worst = salesmanService.worstSalesman(salesmens);
            report= report + ("Worst salesman:( : " + worst.getName()
                    +" CPF: "+ worst.getCpf() + "\n");
        }else{
            report= report + ("without sales, it wouldn't be fair :)"+"\n");
        }

            writer(report, pathOut);
    }

    public void writer( String str, File path ){
        File out = combine(path.getAbsolutePath(), "processed.done.dat");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(out))) {
            bw.write(str);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static File combine(String path, String file) {
        return new File(path, file);
    }

}
