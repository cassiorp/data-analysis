package br.com.company.dataanalysis.Entities;

public class Item {

    private Integer idItem;
    private Integer quant;
    private Double price;

    public Item(Integer idItem, Integer quant, Double price) {
        this.idItem = idItem;
        this.quant = quant;
        this.price = price;
    }

    public Item() {
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getQuant() {
        return quant;
    }

    public void setQuant(Integer quant) {
        this.quant = quant;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
