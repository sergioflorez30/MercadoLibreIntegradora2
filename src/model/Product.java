package model;

public class Product {
    private String name;
    private String description;
    private double price;
    private int amount;
    private Category category;

    private int number_bought;

    public Product(String name, String description, double price, int amount, Category category, int number_bought) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.category = category;
        this.number_bought = number_bought;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getNumber_bought() {
        return number_bought;
    }

    public void setNumber_bought(int number_bought) {
        this.number_bought = number_bought;
    }

    

    

}