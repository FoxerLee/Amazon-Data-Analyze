package edu.tongji.sse.DataWarehouse.Model.MySQLModel;

public class Product {

    private String id;

    private int sales_rank;

    private double price;

    private String languages;

    private String studios;

    private String binding;

    private int running_time;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSales_rank() {
        return sales_rank;
    }

    public void setSales_rank(Integer sales_rank) {
        this.sales_rank = sales_rank;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public Integer getRunning_time() {
        return running_time;
    }

    public void setRunning_time(Integer running_time) {
        this.running_time = running_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSales_rank(int sales_rank) {
        this.sales_rank = sales_rank;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getStudios() {
        return studios;
    }

    public void setStudios(String studios) {
        this.studios = studios;
    }

    public void setRunning_time(int running_time) {
        this.running_time = running_time;
    }
}
