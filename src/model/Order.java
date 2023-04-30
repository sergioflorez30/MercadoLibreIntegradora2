package model;

import java.time.LocalTime;
import java.util.ArrayList;

public class Order {

    private String nameBuyer;
    private ArrayList<String> listProduct;
    private double price;
    private LocalTime dateBuy;

    public Order(String nameBuyer, ArrayList<String> listProduct, double price, LocalTime dateBuy) {
        this.nameBuyer = nameBuyer;
        this.listProduct = listProduct;
        this.price = price;
        this.dateBuy = dateBuy;
    }

    public String getNameBuyer() {
        return nameBuyer;
    }

    public void setNameBuyer(String nameBuyer) {
        this.nameBuyer = nameBuyer;
    }

    public ArrayList<String> getListProduct() {
        return listProduct;
    }

    public void setListProduct(ArrayList<String> listProduct) {
        this.listProduct = listProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalTime getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(LocalTime dateBuy) {
        this.dateBuy = dateBuy;
    }
}