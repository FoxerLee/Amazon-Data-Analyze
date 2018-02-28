package edu.tongji.sse.DataWarehouse.Model.MySQLModel;

public class Ranking {

    private int id;

    private String product;

    private double ranjing;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getRanjing() {
        return ranjing;
    }

    public void setRanjing(double ranjing) {
        this.ranjing = ranjing;
    }
}
