package br.com.company.dataanalysis.Services;

import br.com.company.dataanalysis.Entities.Salesman;
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

    public void analyzer(List<File> files) {
        List<Objects> objects = new ArrayList<>();
        for (File file : files){
            System.out.println(file.getAbsoluteFile());
            int wichLine = 1;
            reader(file, wichLine);
        }

    }

    public List<Object> reader(File file, Integer wichLine){
        List<Object> objs = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            for(String line: lines) {
                readerLine(line, wichLine, objs, file);
            }

        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
        System.out.println(objs.size());
        return objs;
    }

    public void readerLine(String line, Integer wichLine, List<Object> objs, File file){
        if(readerService.linesValidator(line)){
            System.out.println("Linha valida " +line + wichLine);
            wichLine++;
            String cod = readerService.getCod(line);
            switch (cod) {
                case "001" :
                    objs.add(salesmanService.createSalesman(line, objs, wichLine, file));
                    break;
                case "002":
                    objs.add(clientService.createClient(line, objs, wichLine, file));
                    break;
                case "003":

                    break;
                default:

                    break;
            }
        }else{
            logger.info("Line with invalid information");
            wichLine++;
        }
    }
}