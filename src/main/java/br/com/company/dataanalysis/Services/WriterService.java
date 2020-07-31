package br.com.company.dataanalysis.Services;


import br.com.company.dataanalysis.Entities.Client;
import br.com.company.dataanalysis.Entities.Sale;
import br.com.company.dataanalysis.Entities.Salesman;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WriterService {

    Date data = new Date(System.currentTimeMillis());
    File pathLog = new File("/home/cassio/data/log/logs.dat");
    File pathOut = new File("/home/cassio/data/out/processed.done.dat");

    SalesmanService salesmanService = new SalesmanService();
    ClientService clientService = new ClientService();
    SaleService saleService = new SaleService();

    public void reportWriter(List<Object> objects){
        List<Salesman> salesmens = salesmanService.getAllSalesmans(objects);
        List<Client> clients = clientService.getAllClients(objects);
        List<Sale> sales = saleService.getAllSales(objects);
        String report = "";
        report= report + ("Number of salesmans: " + Integer.toString(salesmens.size())+"\n");
        report= report + ("Number of clients: " + Integer.toString(clients.size())+"\n");
        report= report + ("The Best Sale id: " +Integer.toString(saleService.bestSale(sales))+"\n");
        if(sales.size() > 0){
            report= report + ("Worst salesman :( : " + salesmanService.worstSalesman(salesmens).getName()+"\n");
        }else{
            report= report + ("without sales, it wouldn't be fair :)"+"\n");
        }

            writer(report, pathOut);
    }

    public void writer( String str, File path ){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(str);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lineLogWriter(Integer wichLine, String tipo, File file){

        String str =   data +" Line: " + wichLine + " - File: " + file.getAbsoluteFile() +
                " - " + tipo +" with wrong information";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathLog, true))) {
            bw.write(str);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileLogWriter(File file){
        String str =   data +" - File: " + file.getAbsoluteFile() + " invalid format file!";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathLog, true))) {
            bw.write(str);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
