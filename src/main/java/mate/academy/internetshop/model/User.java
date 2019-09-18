package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.lib.IdGenerator;

public class User {
    private Long id;
    private String name;
    private String login;
    private String password;
    private List<Order> orders;

    public User() {
        id = IdGenerator.getNextUserId();
        orders = new ArrayList<>();
    }

    public User(String name) {
        this();
        this.name = name;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
