package br.com.company.dataanalysis.Services;

import br.com.company.dataanalysis.Entities.Client;
import br.com.company.dataanalysis.Entities.Item;
import br.com.company.dataanalysis.Entities.Sale;
import br.com.company.dataanalysis.Entities.Salesman;

import java.util.ArrayList;
import java.util.List;

public class SaleService {

    ItemService itemService = new ItemService();

    public Boolean createSale(String line, List<Sale> sales, Salesman salesman){

        Sale sale = new Sale();
        List<Item> itemList = new ArrayList<>();

        String[] separedLine = line.split("ç");
        int idSale = this.strToInt(separedLine[1]);
        sale.setIdSale(idSale);

        String itemExtracted = itemService.extractsItem(line);
        String[] itens = itemExtracted.split(",");

        for(String item: itens){
            String[] values = item.split("-");
            itemList.add(new Item(Integer.parseInt(values[0]), Integer.parseInt(values[1]), this.strToDouble(values[2])));
        }

        sale.setSalesman(salesman);
        sale.setItens(itemList);
        sales.add(sale);

        salesman.setSales(sale);

        if(!extractsName(line).equals(salesman.getName()) || salesValidator(sale, sales)) {
            return false;
        }
        return true;
    }

    public List<Sale> getAllSales(List<Object> objects){
            List<Sale> sales = new ArrayList<>();
            for(Object obj: objects){
                if(obj instanceof Sale){
                    sales.add((Sale) obj);
                }
            }
            return sales;
    }

    private Boolean salesValidator(Sale sale, List<Sale> sales){
        for(Sale s: sales){
            if(s.getIdSale().equals(sale.getIdSale())){
                return false;
            }
        }
        return true;
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

    private Double totalSaleValue(Sale sale){
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
