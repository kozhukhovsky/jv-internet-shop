package mate.academy.internetshop.model;

import mate.academy.internetshop.lib.IdGenerator;

public class Item {
    private final Long id;
    private final String name;
    private final Double price;

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

    public Double getPrice() {
        return price;
    }

}
