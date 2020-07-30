package br.com.company.dataanalysis.Services;

public class ItemService {

    public String extractsItem(String line){
        String[] itens = line.split("ç");
        String item = itens[2];
        item = item.replaceAll("\\[|\\]", "");
        return item;
    }
}
