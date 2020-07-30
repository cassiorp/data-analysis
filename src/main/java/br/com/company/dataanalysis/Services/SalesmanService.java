package br.com.company.dataanalysis.Services;


import br.com.company.dataanalysis.Entities.Salesman;
import br.com.company.dataanalysis.Utils.Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SalesmanService {

    private final Logger logger = Logger.getLogger(Filter.class.getName());
    SaleService saleService = new SaleService();

    public Salesman createSalesman(String line, List<Object> obj){
        String[] separedLine = line.split("ç");
        Double salary = this.strToDouble(separedLine[3]);
        Salesman salesman = new Salesman(separedLine[1], separedLine[2], salary);
        if(ifExists(salesman, obj)){
            logger.info("Sales with cpf already registred");
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

    private Boolean ifExists(Salesman salesman, List<Object> objs){
        List<Salesman> salesmens = getAllSalesmans(objs);
        for(Salesman s: salesmens){
            if(salesman.getCpf().equals(s.getCpf())){
                return true;
            }
        }
        return true;
    }

    public Salesman findByName(String name, List<Salesman> salesmens){
        Salesman obj = new Salesman();
        for(Salesman salesman: salesmens){
            if(salesman.getName().equals(name)){
                obj = salesman;
            }
        }
        return obj;
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

    public Boolean ifExists( String name, List<Salesman> salesmens ){
        for(Salesman salesman: salesmens){
            if(salesman.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    private double strToDouble(String salaryStr){
      return Double.parseDouble(salaryStr);
    }
}
