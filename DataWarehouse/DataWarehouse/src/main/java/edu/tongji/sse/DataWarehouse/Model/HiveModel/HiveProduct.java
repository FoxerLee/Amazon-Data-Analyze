package edu.tongji.sse.DataWarehouse.Model.HiveModel;

public class HiveProduct {

    private String id;

    private String sales_rank;

    private String price;

    private String languages;

    private String studios;

    private String binding;

    private String running_time;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSales_rank() {
        return sales_rank;
    }

    public void setSales_rank(String sales_rank) {
        this.sales_rank = sales_rank;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getRunning_time() {
        return running_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setRunning_time(String running_time) {
        this.running_time = running_time;
    }
}
