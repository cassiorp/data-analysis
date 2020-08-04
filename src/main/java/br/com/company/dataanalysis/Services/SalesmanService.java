package br.com.company.dataanalysis.Services;

import br.com.company.dataanalysis.Entities.Salesman;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SalesmanService {

    private final Logger logger = Logger.getLogger(Salesman.class.getName());
    SaleService saleService;
    Salesman salesman = new Salesman();

    public Salesman createSalesman(String line, List<Object> obj, Integer wichLine, File file){
        String[] separedLine = line.split("ç");
        Double salary = this.strToDouble(separedLine[3]);
        Salesman salesman = new Salesman(separedLine[1], separedLine[2], salary);
        if(ifExists(salesman, obj)){
            logger.info("Line: "+ wichLine+" Path: "+ file.getAbsolutePath() +" Salesman with cpf already registered");
            return null;
        }
        return salesman;
    }

    public List<Salesman> getAllSalesmans(List<Object> objs){
        List<Salesman> salesmens = new ArrayList<>();
        return salesmens = objs.stream().filter(s -> s instanceof Salesman).
                map(s -> (Salesman) s).collect(Collectors.toList());
    }

    public Boolean ifExists(Salesman salesman, List<Object> objs){
        List<Salesman> salesmens = this.getAllSalesmans(objs);
        return salesmens.stream().anyMatch(s -> s.getCpf().equals(salesman.getCpf()));
    }

    public Salesman worstSalesman(List<Salesman> salesmens){
        saleService = new SaleService();
        Salesman worst = salesmens.get(0);
        for(Salesman s: salesmens){
            if(saleService.totalAllSaleValue(s.getSales()) < saleService.totalAllSaleValue(worst.getSales())){
                worst = s;
            }
        }
        return worst;
    }

    public Salesman findByName(String name, List<Object> objs){
        List<Salesman> salesmens = getAllSalesmans(objs);
        for(Salesman salesman: salesmens){
            if(salesman.getName().equals(name)){
                return salesman;
            }
        }
        return null;
    }

    private double strToDouble(String salaryStr){
      return Double.parseDouble(salaryStr);
    }
}
