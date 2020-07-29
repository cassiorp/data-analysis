package br.com.company.dataanalysis.Services;


import br.com.company.dataanalysis.Entities.Salesman;

import java.util.List;

public class SalesmanService {

    SaleService saleService = new SaleService();

    public Boolean createSalesman(String line, List<Salesman> salesmans){
        String[] separedLine = line.split("ç");
        int size = salesmans.size();
        Double salary = this.strToDouble(separedLine[3]);
        Salesman salesman = new Salesman(separedLine[1], separedLine[2], salary);

        if(salesman.equals(null) || !salesmensValidator(salesman, salesmans)){
            return false;
        }
        salesmans.add(salesman);
        return size < salesmans.size() ? true : false;
    }

    private Boolean salesmensValidator(Salesman salesman, List<Salesman> salesmens){
        for(Salesman s: salesmens){
            if(salesman.getCpf().equals(s.getCpf())){
                return false;
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
