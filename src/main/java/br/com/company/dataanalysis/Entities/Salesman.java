package br.com.company.dataanalysis.Entities;

import java.util.ArrayList;
import java.util.List;

public class Salesman {

    private final Integer ID = 001;
    private String cpf;
    private String name;
    private Double salary;
    private List<Sale> sales = new ArrayList<>();

    public Salesman(String cpf, String name, Double salary) {
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

    public Salesman(){
    }

    public Integer getID() {
        return ID;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(Sale sale) {
        this.sales.add(sale);
    }
}
