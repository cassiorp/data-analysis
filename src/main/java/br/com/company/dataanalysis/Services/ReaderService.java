package br.com.company.dataanalysis.Services;

import br.com.company.dataanalysis.Entities.Client;
import br.com.company.dataanalysis.Entities.Sale;
import br.com.company.dataanalysis.Entities.Salesman;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class ReaderService {

    ItemService itemService = new ItemService();
    List<String> validCods = Arrays.asList("001", "002", "003");

    public Boolean linesValidator(String line) {

        String[] brokenLine = line.split("ç");


        for(String l: brokenLine){
            if(codeValidator(brokenLine[0])){
                Object obj = getType(brokenLine[0]);
                if (Salesman.class.equals(obj)) {
                    return strToLong(brokenLine[1]) && strToDouble(brokenLine[3]);
                } else if (Client.class.equals(obj)) {
                    return strToLong(brokenLine[1]);
                } else if (Sale.class.equals(obj)) {
                    return itensValidator(line);
                }
            }
        }
        return false;

    }

    public Boolean codeValidator(String cod){
        return validCods.stream().anyMatch(c -> c.equals(cod));
    }
    public Object getType(String line){
        if(line.equals(validCods.get(0))){
            return Salesman.class;
        }else if(line.equals(validCods.get(1))){
            return Client.class;
        }else if(line.equals(validCods.get(2))){
            return Sale.class;
        }
        return null;
    }
    private Boolean strToLong(String cpf){
        try {
            Long.parseLong(cpf);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    private Boolean strToDouble(String cpf){
        try {
            Double.parseDouble(cpf);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    public Boolean itensValidator(String line){
        String itens = itemService.extractsItem(line);
        itens = itens.replace(".","");
        itens = itens.replace("-","");
        itens = itens.replace(",","");

        try {
            BigInteger big = new BigInteger(itens);
            return true;
        }catch (Exception ex){
            return false;
        }



    }
    public List<String> validCodes(){
        return this.validCods;
    }
    public String getCod(String line){
        return line.substring(0,3);
    }
}
