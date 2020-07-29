package br.com.company.dataanalysis.Services;

import br.com.company.dataanalysis.Entities.Client;
import br.com.company.dataanalysis.Entities.Sale;
import br.com.company.dataanalysis.Entities.Salesman;
import br.com.company.dataanalysis.Utils.Filter;
import br.com.company.dataanalysis.Utils.Writer;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class AnalyzeService {

    private final String[] cod = {"001", "002", "003"};

    private final Logger logger = Logger.getLogger(AnalyzeService.class
            .getName());

    List<Salesman> salesmans = new ArrayList<>();
    List<Client> clients = new ArrayList<>();
    List<Sale> sales = new ArrayList<>();

    Filter filter = new Filter();
    Writer writer = new Writer();

    SalesmanService salesmanService = new SalesmanService();
    ClientService clientService = new ClientService();
    SaleService saleService = new SaleService();

    public void reader(List<File> files, File path){

        List<File> filtered;
        filtered = filter.filter(files);

        for (File file : filtered) {
            try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
                String line = br.readLine();
                int wichLine = 1;
                while (line != null) {
                    lineReader(line, wichLine, file);
                    line = br.readLine();
                    wichLine++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writer.writerLine( "Quantidade de Clientes: " +  Integer.toString(clients.size()), path );
        writer.writerLine( "Quantidade de vendedores: " +  Integer.toString(salesmans.size()), path );
        int idBest = saleService.bestSale(sales);
        if(idBest == 0 ){
            writer.writerLine( "Nenhuma venda registrada ", path );
            writer.writerLine( "Nenhuma venda registrada. Não seria justo. ", path );
        }else{
            writer.writerLine( "Nenhuma venda registrada" +  saleService.bestSale(sales), path );
            writer.writerLine("Pior vendedor: " + salesmanService.worstSalesman(salesmans).getName(), path );
        }

    }

    private void lineReader(String line, Integer wichLine, File file) throws IOException {

        String[] separedLine = line.split("ç");

        if (separedLine[0].equals(cod[0])) {

            boolean add = salesmanService.createSalesman(line, salesmans);
            if(!add){
                writer.lineLogWriter( wichLine , "Salesman",file.getAbsoluteFile());
                logger.info("Line: " + wichLine + " - " + file.getAbsoluteFile() + " with wrong information");

            }

        }else if(separedLine[0].equals(cod[1])){

            boolean add = clientService.createClient(line, clients);
            if(!add){
                writer.lineLogWriter( wichLine , "Client",file.getAbsoluteFile());
                logger.info("Line: " + wichLine + " - " + file.getAbsoluteFile() + " with wrong information");
            }

        }else if(separedLine[0].equals(cod[2])){

            String salesmanName = saleService.extractsName(line);
            if(salesmanService.ifExists(salesmanName, salesmans)){
                Salesman salesman = salesmanService.findByName(salesmanName, salesmans);
                boolean add = saleService.createSale(line, sales, salesman);
                if(!add){
                    writer.lineLogWriter(wichLine, "Sale", file.getAbsoluteFile());
                    logger.info("Line: " + wichLine + " - " + file.getAbsoluteFile() + " with wrong information");
                }
            }
        }
    }

}
