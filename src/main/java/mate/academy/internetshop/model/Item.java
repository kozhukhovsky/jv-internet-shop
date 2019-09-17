package mate.academy.internetshop.model;

import mate.academy.internetshop.lib.IdGenerator;

public class Item {
    private Long id;
    private String name;
    private Double price;

    public Item(String name, Double price) {
        id = IdGenerator.getNextItemId();
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
