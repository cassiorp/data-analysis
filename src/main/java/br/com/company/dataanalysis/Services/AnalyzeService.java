package br.com.company.dataanalysis.Services;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.*;


@Service
public class AnalyzeService {
    File in = new File("/home/cassio/data/in/");

    ReaderService readerService = new ReaderService();
    SaleService saleService = new SaleService();
    SalesmanService salesmanService = new SalesmanService();

    public void analyzer( List<File> files)  {
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
                if(readerService.linesValidator(line)){
                    System.out.println("Linha valida " +line + wichLine);
                    wichLine++;

                    String cod = readerService.getCod(line);

                    switch (cod) {
                        case "001" :
                            objs.add(salesmanService.createSalesman(line, objs));
                            break;
                        case "002":
                            break;
                        case "003":
                            break;
                        default:
                         
                            break;
                    }



                }else{
                    System.out.println("Linha invalida " + line + wichLine);
                    wichLine++;
                }
            }

        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
        return objs;
    }
}