package br.com.company.dataanalysis.Services;

import br.com.company.dataanalysis.Entities.Client;
import br.com.company.dataanalysis.Entities.Sale;
import br.com.company.dataanalysis.Entities.Salesman;
import br.com.company.dataanalysis.Utils.Filter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Logger;


@Service
public class AnalyzeService {
    File in = new File("/home/cassio/data/in/");

    private final Logger logger = Logger.getLogger(Salesman.class.getName());

    ReaderService readerService = new ReaderService();
    SaleService saleService = new SaleService();
    SalesmanService salesmanService = new SalesmanService();
    ClientService clientService = new ClientService();
    List<Object> objects = new ArrayList<>();

    Filter filter = new Filter();

    public List<Object> analyzer(List<File> files) {
        List<File> filtered = filter.filter(files);
        for (File file : filtered){
            int wichLine = 1;
            reader(file, wichLine);
        }
        return this.objects;
    }

    public void reader(File file, Integer wichLine){
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            for(String line: lines) {
                readerLine(line, wichLine, file);
                wichLine++;
            }

        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
        
        System.out.println(objects.size());
    }

    public void readerLine(String line, Integer wichLine, File file){
        if(readerService.linesValidator(line)){
            System.out.println("Valid line " + wichLine +" - "+ line );
            String cod = readerService.getCod(line);
            switch (cod) {
                case "001" :
                    Salesman salesman = salesmanService.createSalesman(line, objects, wichLine, file);
                    if(salesman != null) objects.add(salesman);
                    break;
                case "002":
                    Client client = clientService.createClient(line, objects, wichLine, file);
                    if(client != null) objects.add(client);
                    break;
                case "003":
                    Sale sale = saleService.createSale(line, objects, wichLine, file);
                    if(sale != null) objects.add(sale);
                    break;
                default:
                    logger.info("something was not right");
                    break;
            }
        }else{
            logger.info("Line with invalid information");
            wichLine++;
        }
    }
}