package br.com.company.dataanalysis.Services;

import br.com.company.dataanalysis.Entities.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemService {

    public List<Item> createItemsForSale(String line){
        List<Item> items = new ArrayList<>();
        if(!ifIsSale(line)){
            return null;
        }
        String itemExtracted = extractsItem(line);
        String[] itens = itemExtracted.split(",");
        for(String item: itens){
            String[] values = item.split("-");
            items.add(new Item(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Double.parseDouble(values[2])));
        }
        if(items.isEmpty()){
            return null;
        }
        return items;
    }

    public String extractsItem(String line){
        String[] itens = line.split("ç");
        String item = itens[2];
        item = item.replaceAll("\\[|\\]", "");
        return item;
    }
    public Boolean ifIsSale(String line){
        ReaderService readerService = new ReaderService();
        if(readerService.getCod(line).equals("003")){
           return true;
       }
       return false;
    }
}
