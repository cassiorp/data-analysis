package br.com.company.dataanalysis.Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SalesmanTest {

    @Test
    void getID(){
        Salesman salesman = new Salesman();
        Integer IDtest = salesman.getID();
        Integer ID = 001;

        Assertions.assertEquals(ID, IDtest);
    }

    @Test
    void getCpf(){
        Salesman salesman = new Salesman();
        salesman.setCpf("00132166689");
        String cpf = "00132166689";

        Assertions.assertEquals(cpf, salesman.getCpf());
    }

    @Test
    void getName(){
        Salesman salesman = new Salesman();
        salesman.setName("Cassio Pereira");
        String name = "Cassio Pereira";

        Assertions.assertEquals(name, salesman.getName());
    }

    @Test
    void getBusiness(){
        Salesman salesman = new Salesman();
        salesman.setSalary(100.90);
        double salario = 100.90;

        Assertions.assertEquals(salario, salesman.getSalary());
    }
}
