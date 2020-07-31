package br.com.company.dataanalysis.Services;

import br.com.company.dataanalysis.Entities.Item;
import br.com.company.dataanalysis.Entities.Sale;
import br.com.company.dataanalysis.Entities.Salesman;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SaleService {

    ItemService itemService = new ItemService();
    SalesmanService salesmanService = new SalesmanService();
    private final Logger logger = Logger.getLogger(Salesman.class.getName());
    Salesman salesman;

    public Sale createSale(String line, List<Object> obj, Integer wichLine, File file){

        Sale sale = new Sale();

        String[] separedLine = line.split("ç");
        int idSale = this.strToInt(separedLine[1]);
        sale.setIdSale(idSale);
        if(ifExists(sale, obj)){
            logger.info("Line: "+ wichLine+" Path: "+ file.getAbsolutePath() +" Sale with Id already registered");
            return null;
        }

        List<Item> items = itemService.createItemsForSale(line);

        String salesmanName = extractsName(line);

        List<Salesman> salesmens = salesmanService.getAllSalesmans(obj);
        this.salesman = salesmanService.findByName(salesmanName, obj);
        if (salesman == null) {
            logger.info("Line: "+ wichLine+" Path: "+ file.getAbsolutePath() +" Salesman not found ");
            return null;

        }

        sale.setItens(items);
        sale.setSalesman(salesman);
        salesman.setSales(sale);
        return sale;
    }

    public Boolean ifExists(Sale sale, List<Object> objs){
        List<Sale> sales = this.getAllSales(objs);
        return sales.stream().anyMatch(s -> s.getIdSale().equals(sale.getIdSale()));
    }

    public List<Sale> getAllSales(List<Object> objs){
        List<Sale> sales = new ArrayList<>();
        return sales = objs.stream().filter(s -> s instanceof Sale).
                map(s -> (Sale) s).collect(Collectors.toList());
    }

    public Integer bestSale(List<Sale> sales){
        if(sales.size() == 0){
            return 0;
        }
        Sale best = sales.get(0);
        double salesPrice;
        for(Sale sale: sales){
            salesPrice = this.totalSaleValue(sale);
            if(salesPrice > this.totalSaleValue(best)){
                best = sale;
            }
        }
        return best.getIdSale();
    }

    public Double totalSaleValue(Sale sale){
        Double total = 0.0;
        List<Item> items = sale.getItens();
        for(Item i: items){
            total = total + i.getPrice() * i.getQuant();
        }
        return total;
    }

    public Double totalAllSaleValue(List<Sale> sales){
        Double total = 0.0;
        for(Sale sale: sales){
            total = total + this.totalSaleValue(sale);
        }
        return total;
    }

    public String extractsName(String line){
        String[] itemsGetName = line.split("ç");
        return itemsGetName[3];
    }

    private Integer strToInt(String idStr){
        return Integer.parseInt(idStr);
    }
    private double strToDouble(String priceStr){
        return Double.parseDouble(priceStr);
    }


}
