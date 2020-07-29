package br.com.company.dataanalysis.Entities;

import java.util.List;

public class Sale {

    private final Integer ID = 003;

    private Integer idSale;
    private List<Item> itens;
    private Salesman salesman;

    public Sale(Integer idSale, List<Item> itens, Salesman salesman) {
        this.idSale = idSale;
        this.itens = itens;
        this.salesman = salesman;
    }

    public Sale() {
    }

    public Integer getID() {
        return ID;
    }

    public Integer getIdSale() {
        return idSale;
    }

    public void setIdSale(Integer idSale) {
        this.idSale = idSale;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }
}
