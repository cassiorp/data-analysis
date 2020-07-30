package br.com.company.dataanalysis.Services;


import br.com.company.dataanalysis.Entities.Salesman;
import br.com.company.dataanalysis.Utils.Filter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class SalesmanService {

    private final Logger logger = Logger.getLogger(Salesman.class.getName());
    SaleService saleService = new SaleService();

    public Salesman createSalesman(String line, List<Object> obj, Integer wichLine, File file){
        String[] separedLine = line.split("ç");
        Double salary = this.strToDouble(separedLine[3]);
        Salesman salesman = new Salesman(separedLine[1], separedLine[2], salary);
        if(ifExists(salesman, obj)){
            logger.info("Line: "+ wichLine+" Path: "+ file.getAbsolutePath() +" Salesman with cpf already registred");
            return null;
        }
        return salesman;
    }

    public List<Salesman> getAllSalesmans(List<Object> objects){
        List<Salesman> salesmens = new ArrayList<>();
        for(Object obj: objects){
            if(obj instanceof Salesman){
                salesmens.add((Salesman) obj);
            }
        }
        return salesmens;
    }

    public Boolean ifExists(Salesman salesman, List<Object> objs){
        List<Salesman> salesmens = this.getAllSalesmans(objs);
        for(Salesman salesman1 : salesmens){
            if(salesman.getCpf().equals(salesman1.getCpf())){
                return true;
            }
        }
        return false;
    }

    public Salesman findByName(String name, List<Object> objs){
        List<Salesman> salesmens = this.getAllSalesmans(objs);
        for(Salesman salesman: salesmens){
            if(salesman.getName().equals(name)){
                return salesman;
            }
        }
        return null;
    }

    public Salesman worstSalesman(List<Salesman> salesmens){
        Salesman worst = salesmens.get(0);
        Double totalSalesWorstSalesman = saleService.totalAllSaleValue(worst.getSales());
        for(Salesman salesman: salesmens){
            Double salesTotal;
            salesTotal = saleService.totalAllSaleValue(salesman.getSales());
            if(salesTotal < totalSalesWorstSalesman){
                worst = salesman;
            }
        }
        return worst;
    }

    private double strToDouble(String salaryStr){
      return Double.parseDouble(salaryStr);
    }
}
