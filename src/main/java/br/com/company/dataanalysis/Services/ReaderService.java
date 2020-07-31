package br.com.company.dataanalysis.Services;

import br.com.company.dataanalysis.Entities.Client;
import br.com.company.dataanalysis.Entities.Sale;
import br.com.company.dataanalysis.Entities.Salesman;

public class ReaderService {

    ItemService itemService = new ItemService();
    String[] validCods = {"001", "002", "003"};

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
    private Boolean codeValidator(String cod){
        for(String s: validCods){
            if(s.equals(cod)){
                return true;
            }
        }
        return false;
    }
    public Object getType(String line){
        if(line.equals(validCods[0])){
            return Salesman.class;
        }else if(line.equals(validCods[1])){
            return Client.class;
        }else if(line.equals(validCods[2])){
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
            Long.parseLong(itens);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    public String[] validCodes(){
        return this.validCods;
    }
    public String getCod(String line){
        return line.substring(0,3);
    }
}
