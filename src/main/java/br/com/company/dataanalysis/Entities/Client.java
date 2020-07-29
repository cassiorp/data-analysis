package br.com.company.dataanalysis.Entities;

public class Client {

    private final Integer ID = 002;
    private String cnpj;
    private String name;
    private String business;

    public Client(String cnpj, String name, String business) {
        this.cnpj = cnpj;
        this.name = name;
        this.business = business;
    }

    public Client() {
    }

    public Integer getID() {
        return ID;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }
}
